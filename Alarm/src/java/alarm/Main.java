package alarm;

import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.*;
import java.time.LocalTime;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Filip
 */
public class Main {
    private static String AlarmSound = "Miki Milane";
    private static List<AlarmTime> Times = Collections.synchronizedList(new LinkedList<>());
    
    
    @Resource(lookup = "ProjekatConnectionFactory")
    private static ConnectionFactory connectionFactory;
    
    @Resource(lookup = "PlayerSongQueue")
    private static Queue playerSongQueue;
    
    @Resource(lookup = "ReminderAlarmQueue2")
    private static Queue reminderAlarmQueue;
    
    
    public static void main(String[] args) {
        JMSContext context = connectionFactory.createContext();
        JMSProducer producer = context.createProducer();
        JMSConsumer consumer = context.createConsumer(reminderAlarmQueue);
        System.out.println("Alarm started.");
        consumer.setMessageListener((Message message) -> {
            try {
                TextMessage textMessage = (TextMessage)message;
                String ringTime = textMessage.getText();
                System.out.println("Message: " + ringTime);
                
                //parsiraj string
                //StringTokenizer st = new StringTokenizer(ringTime, ";");
                String[] result = ringTime.split(";");
                
                AlarmTime alarmTime = null;
                if (result.length == 1 || result[result.length - 1].equals("offered")) {
                    
                    LocalTime Time = LocalTime.parse(result[0]);
                    alarmTime = new AlarmTime(Time, false, null);
                    setTime(alarmTime);
                    System.out.println("Time: " + alarmTime.getTime().getHour() + ":" + alarmTime.getTime().getMinute());
                } else if (result[result.length - 1].equals("periodic")) {
                    LocalTime Time = LocalTime.parse(result[0]);
                    LocalTime Period = LocalTime.parse(result[1]);
                    alarmTime = new AlarmTime(Time, true, Period);
                    setTime(alarmTime);
                    System.out.println("Time: " + alarmTime.getTime().getHour() + ":" + alarmTime.getTime().getMinute());
                } else {
                    SetAlarmSound(result[0]);
                }
                
                Thread.sleep(1000);     
            } catch (JMSException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        while (true) {
            try {              
                if (CheckToRing()) {
                    System.out.println("Ring.");
                    SendRequestToPlaySong(context, producer);

                    Thread.sleep(60000);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    
    public static boolean TimeAlreadySet(LocalTime Time) {
        synchronized (Times) {
            for (AlarmTime at : Times) {
                if (at.getTime().equals(Time)) {
                    return true;
                }
            }  
            return false;
        }
    }

    public static void setTime(AlarmTime time) {
        if (!TimeAlreadySet(time.getTime())) {
            Times.add(time);
        } 
    
    }
    
    
    
    public static boolean CheckToRing() {
        synchronized (Times) {
            for (AlarmTime Time : Times) {
            if (Time.isPeriodic()) {
                if (Time.getTime().isBefore(java.time.LocalTime.now())) {
                    System.out.println("PERIODIC TIME");
                    Time.setTime(Time.getTime().plusHours(Time.getPeriod().getHour()).plusMinutes(Time.getPeriod().getMinute()));
                    System.out.println("Ring time: " + Time.getTime().getHour() + ":" + Time.getTime().getMinute());
                }
                boolean toReturn = TimeToRingAlarm(Time.getTime());
                if (toReturn) {
                    return toReturn;
                }
            } else {
                boolean toReturn = TimeToRingAlarm(Time.getTime());
                if (toReturn) {
                    return toReturn;
                }
            }
        }
        return false;
        } 
    }
    
    public static void SendRequestToPlaySong(JMSContext context, JMSProducer producer) {
        TextMessage songName = context.createTextMessage(AlarmSound);
        producer.send(playerSongQueue, songName);
    }
    
    public static boolean TimeToRingAlarm(java.time.LocalTime time) {
        return (java.time.LocalTime.now().getHour() == time.getHour() && java.time.LocalTime.now().getMinute() == time.getMinute());
    }
    
    public static void SetAlarmSound(String alarmSound) {
        AlarmSound = alarmSound;
    }
}
