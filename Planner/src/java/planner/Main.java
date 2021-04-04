/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planner;

import MapQuestAPI.MapQuestJSON;
import MapQuestAPI.MapQuestProvider;
import java.util.LinkedList;
import java.util.List;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.*;

/**
 *
 * @author Filip
 */
public class Main {

    private static List<Obligation> Obligations = new LinkedList<>();
    private static String Home = "Ruzveltova,Beograd,Srbija";
    private static String CurrentLocation = Home;
    
    @Resource(lookup = "ProjekatConnectionFactory")
    private static ConnectionFactory connectionFactory;
    
    @Resource(lookup = "ReminderAlarmQueue2")
    private static Queue reminderAlarmQueue;
    
    @Resource(lookup = "PlannerQueue")
    private static Queue plannerQueue;

    
    
    public static String getHome() {
        return Home;
    }

    public static void setHome(String Home) {
        Main.Home = Home;
    }
    
    public static int GetTravelTime(String from, String to) {
        MapQuestProvider provider = new MapQuestProvider();
        MapQuestJSON msg = provider.GetTravelTime(from, to);
        return msg.getRoute().getTime();
    }
    
    public static boolean CheckToAddObligation(Obligation obligation) {
        //Obligation in the past
        if (obligation.getStartTime().isBefore(LocalDateTime.now()))
            return false;
        
        //Obligations at the same time
        for (Obligation o : Obligations) {
            if (obligation.SameTime(o)) {
                return false;
            }
        }
        
        //Last obligation before
        Obligation lastBefore = FindLastObligationBeforeCurrent(obligation);
        if (lastBefore != null) {
            LocalDateTime newTime = lastBefore.getEndTime().plusSeconds(GetTravelTime(lastBefore.getLocation(), obligation.getLocation()));
            if (obligation.getStartTime().isBefore(newTime))
                return false;
        }
        
        //First obligation after
        Obligation firstAfter = FindFirstObligationBAfterCurrent(obligation);
        if (firstAfter != null) {
            LocalDateTime newTime = obligation.getEndTime().plusSeconds(GetTravelTime(obligation.getLocation(), firstAfter.getLocation()));
            if (firstAfter.getStartTime().isBefore(newTime))
                return false;
        }
        
        return true;
    }
    
    public static Obligation FindLastObligationBeforeCurrent(Obligation obligation) {
        long min = Long.MAX_VALUE;
        Obligation ret = null;
        for (Obligation o : Obligations) {
            long possibleMin = obligation.TimeDifference(o);
            if (possibleMin >= 0 && possibleMin < min) {
                ret = o;
                min = possibleMin;
            }
        }
        return ret;
    }
    
    public static Obligation FindFirstObligationBAfterCurrent(Obligation obligation) {
        long min = Long.MAX_VALUE;
        Obligation ret = null;
        for (Obligation o : Obligations) {
            long possibleMin = o.TimeDifference(obligation);
            if (possibleMin >= 0 && possibleMin < min) {
                ret = o;
                min = possibleMin;
            }
        }
        return ret;
    }
    
    public static boolean AddObligation(String name, String startDate, String startTime, String endDate, String endTime, String location) {
        // date: 2021-04-28
        // time: 17:15
        Obligation o = new Obligation(name, LocalDateTime.parse(startDate + "T" + startTime), LocalDateTime.parse(endDate + "T" + endTime), location);
        if (o.getEndTime().isAfter(o.getStartTime()) && CheckToAddObligation(o)) {
            Obligations.add(o);
            return true;
        } else {
            return false;
        }
    }
    
    public static int FindObligation(String name) {
        int ret = 0;
        for (Obligation o : Obligations) {
            if (o.getName().equals(name)) {
                return ret;
            }
            ret++;
        }
        return -1;
    }
    
    public static boolean DeleteObligation(String name) {
        int index = FindObligation(name);
        if (index != -1) {
            Obligations.remove(index);
            return true;
        } else {
            return false;
        }
    }
    
    public static boolean AddObligation(String name, String startDate, String startTime, String endDate, String endTime) {
        // date: 2021-04-28
        // time: 17:15
        Obligation o = new Obligation(name, LocalDateTime.parse(startDate + "T" + startTime), LocalDateTime.parse(endDate + "T" + endTime));
        if (o.getEndTime().isAfter(o.getStartTime()) && CheckToAddObligation(o)) {
            Obligations.add(o);
            return true;
        } else {
            return false;
        }
    }
    
    public static boolean UpdateObligation(String name, String startDate, String startTime, String endDate, String endTime, String location) {
        LocalDateTime start = LocalDateTime.parse(startDate + "T" + startTime);
        LocalDateTime end = LocalDateTime.parse(endDate + "T" + endTime);
        if (end.isAfter(start)) {
            for (Obligation o : Obligations) {
                if (o.getName().equals(name)) {
                    o.setStartTime(start);
                    o.setEndTime(end);
                    o.setLocation(location);
                    System.out.println(o.toString());
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public static boolean UpdateObligation(String name, String startDate, String startTime, String endDate, String endTime) {
        LocalDateTime start = LocalDateTime.parse(startDate + "T" + startTime);
        LocalDateTime end = LocalDateTime.parse(endDate + "T" + endTime);
        if (end.isAfter(start)) {
            for (Obligation o : Obligations) {
                if (o.getName().equals(name)) {
                    o.setStartTime(start);
                    o.setEndTime(end);
                    System.out.println(o.toString());
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public static boolean ActivateReminder(String name, JMSContext context, JMSProducer producer) {
        for (Obligation o : Obligations) {
            if (o.getName().equals(name)) {
                try {
                    if (o.isReminderSet())
                        return false;
                    
                    //Navij alarm za startTime - travelTime
                    int travelTime = GetTravelTime(CurrentLocation, o.getLocation());
                    int travelTimeHour = travelTime / 3600;
                    int travelTimeMinute = (travelTime - travelTimeHour * 3600) / 60;
                    
                    System.out.println("Travel time: " + travelTimeHour + ":" + travelTimeMinute);
                    LocalDateTime ringTime = o.getStartTime().minusHours(travelTimeHour).minusMinutes(travelTimeMinute);
                    
                    String ringTimeStringHour = "";
                    String ringTimeStringMinute = "";
                    if (ringTime.getHour() < 10) {
                        ringTimeStringHour = "0" + ringTime.getHour();
                    } else {
                        ringTimeStringHour = Integer.toString(ringTime.getHour());
                    }
                    if (ringTime.getMinute() < 10) {
                        ringTimeStringMinute = "0" + ringTime.getMinute();
                    } else {
                        ringTimeStringMinute = Integer.toString(ringTime.getMinute());
                    }
                    
                    String ringTimeString = ringTimeStringHour + ":" + ringTimeStringMinute;
                    TextMessage message = context.createTextMessage(ringTimeString);
                    System.out.println("Ring time for reminder: " + ringTimeString);
                    
                    producer.send(reminderAlarmQueue, message);
                    o.setReminderSet(true);
                    Thread.sleep(1000);
                    
                    return true;
                } catch (InterruptedException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        return false;
    }
    
    

    public static void main(String[] args) {
        JMSContext context = connectionFactory.createContext();
        JMSProducer producer = context.createProducer();
        JMSConsumer consumer = context.createConsumer(plannerQueue);
        System.out.println("Planner started.");
        
        consumer.setMessageListener((Message message) -> {
            try {
                TextMessage textMessage = (TextMessage)message;
                String obligationText = textMessage.getText();
                System.out.println("Message: " + obligationText);
                
                String[] result2 = obligationText.split(";");
                String[] result = result2[0].split("_");
                boolean ret = false;
                TextMessage msg = null;
                switch (result2[result2.length - 1]) {
                    case "addFromDatabase":
                        ret = false;
                        if (result.length == 5)
                            ret = AddObligation(result[0], result[1], result[2], result[3], result[4]);
                        else {
                            ret = AddObligation(result[0], result[1], result[2], result[3], result[4], result[5]);
                        }
                        System.out.println("Succes: " + ret);
                        break;
                    
                    case "createObligation":
                        ret = false;
                        if (result.length == 5)
                            ret = AddObligation(result[0], result[1], result[2], result[3], result[4]);
                        else {
                            ret = AddObligation(result[0], result[1], result[2], result[3], result[4], result[5]);
                        }
                        
                        System.out.println("Succes: " + ret);
                        if (ret) {
                            StringBuilder sb = new StringBuilder();
                            sb.append(result2[0]);
                            if (result.length == 5) {
                                sb.append("_");
                                sb.append("Home");
                            }
                            System.out.println("Insert in database: " + sb.toString());
                            new Provider().insertTask(sb.toString(), result2[1]);
                        }
                        
                        break;
                        
                    case "removeObligation":
                        ret = DeleteObligation(result[0]);
                        System.out.println("ret: " + ret);

                        break;
                        
                    case "updateObligation":
                        ret = false;
                        if (result.length == 5)
                            ret = UpdateObligation(result[0], result[1], result[2], result[3], result[4]);
                        else {
                            ret = UpdateObligation(result[0], result[1], result[2], result[3], result[4], result[5]);
                        }
                        System.out.println("ret: " + ret);

                        break;
                        
                    case "activateReminder":
                        ret = ActivateReminder(result[0], context, producer);
                        System.out.println("ret: " + ret);

                        break;
                }
                Thread.sleep(1000);
            } catch (JMSException | InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        while (true) {
            
        }
    }
    
    
}
