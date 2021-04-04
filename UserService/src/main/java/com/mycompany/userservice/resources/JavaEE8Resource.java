package com.mycompany.userservice.resources;

import ResponsePackage.ClientResponse;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import entiteti.*;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import entiteti.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalTime;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("applications")
public class JavaEE8Resource {
    
    public static int currentUser;
    private static int taskCnt;
    
    @PersistenceContext(unitName = "my_persistence_unit")
    EntityManager em;
    
    @Resource(lookup = "ProjekatConnectionFactory")
    private ConnectionFactory connectionFactory;
    
    @Resource(lookup = "ReminderAlarmQueue2")
    private Queue reminderAlarmQueue;
    
    @Resource(lookup = "PlayerSongQueue")
    private Queue playerSongQueue;
    
    @Resource(lookup = "PlannerQueue")
    private Queue plannerQueue;
    
    
    @GET
    public Response ping(){
        return Response
                .ok("ping")
                .build();
    }
    
    @GET
    @Path("login")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response LogIn() {
        JMSContext context = connectionFactory.createContext();
        JMSProducer producer = context.createProducer();
        
        TypedQuery<Tasks> t = em.createQuery("SELECT t FROM Tasks t WHERE t.idUser = :currentUser", Tasks.class);
        t.setParameter("currentUser", currentUser);
        List<Tasks> tasks = t.getResultList();
        
        String firstTask = "";
        taskCnt = 0;
        for (Tasks task : tasks) {
            //ProbudiFilipa2_2022-02-26_20:55_2022-02-26_21:00
            String obligation = task.getName() + "_" + task.getStartDate() + "_" + task.getStartTime() + "_" + task.getEndDate() + "_" + task.getEndTime();
            if (!task.getLocation().equals("Home")) {
                obligation += "_" + task.getLocation();
            }
            if (taskCnt == 0)
                firstTask = obligation;
            taskCnt++;
            String obligationText = obligation + ";" + "addFromDatabase";
            TextMessage obligationBody = context.createTextMessage(obligationText);
            producer.send(plannerQueue, obligationBody);
        }
        
        taskCnt = 0;
        TypedQuery<Songs> s = em.createQuery("SELECT s FROM Songs s WHERE s.idUser = :currentUser", Songs.class);
        s.setParameter("currentUser", currentUser);
        List<Songs> songs = s.getResultList();
        
        StringBuilder sb = new StringBuilder();
        for (Songs song : songs) {
            sb.append(song.getName() + "_");
        }
        
        if (firstTask != "")
            sb.append(";" + firstTask);
        
        return Response.status(Response.Status.OK).entity(new ClientResponse(sb.toString())).build();
    }
    
    @POST
    @Path("youtube/{songName}")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response PlaySongOnYoutube(@PathParam("songName") String songName) {

        JMSContext context = connectionFactory.createContext();
        JMSProducer producer = context.createProducer();
        
        TextMessage songNameBody = context.createTextMessage(songName);
        producer.send(playerSongQueue, songNameBody);
        
        Songs song = new Songs();
        song.setIdUser(currentUser);
        song.setName(songName);
        
        em.persist(song);
        
        return Response.status(Response.Status.OK).entity(new ClientResponse(songName)).build();
    }
    
    @POST
    @Path("setalarm/{time}")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response SetAlarmTime(@PathParam("time") String time) {

        JMSContext context = connectionFactory.createContext();
        JMSProducer producer = context.createProducer();
        
        TextMessage timeBody = context.createTextMessage(time);
        producer.send(reminderAlarmQueue, timeBody);
        
        Alarm alarm = new Alarm();
        alarm.setTime(time);
        alarm.setIdUser(currentUser);
        alarm.setPeriod("null");
        
        em.persist(alarm);
        
        return Response.status(Response.Status.OK).entity(new ClientResponse(time)).build();
    }
    
    @POST
    @Path("setperiodicalarm/{time}/{period}")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response SetPeriodicAlarmTime(@PathParam("time") String time, @PathParam("period") String period) {

        JMSContext context = connectionFactory.createContext();
        JMSProducer producer = context.createProducer();
        
        String alarmTime = time + ";" + period + ";" + "periodic";
        TextMessage timeBody = context.createTextMessage(alarmTime);
        producer.send(reminderAlarmQueue, timeBody);
        
        Alarm alarm = new Alarm();
        alarm.setTime(time);
        alarm.setIdUser(currentUser);
        alarm.setPeriod(period);
        
        em.persist(alarm);
        
        return Response.status(Response.Status.OK).entity(new ClientResponse(time + " period: " + period)).build();
    }
    
    @POST
    @Path("setofferedalarm/{offeredtime}")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response SetOfferedAlarmTime(@PathParam("offeredtime") String offeredtime) {
        
        JMSContext context = connectionFactory.createContext();
        JMSProducer producer = context.createProducer();

        String[] times = offeredtime.split("-");
        int rand = (int)(Math.random() * times.length);
        String time = times[rand];
        
        String alarmTime = time + ";" + "offered";
        TextMessage timeBody = context.createTextMessage(alarmTime);
        producer.send(reminderAlarmQueue, timeBody);
        
        Alarm alarm = new Alarm();
        alarm.setTime(time);
        alarm.setIdUser(currentUser);
        alarm.setPeriod("null");
        
        em.persist(alarm);

        return Response.status(Response.Status.OK).entity(new ClientResponse(time)).build();
    }
    
    @GET
    @Path("setringtone/{songName}")
    public Response SetRingtone(@PathParam("songName") String songName) {

        JMSContext context = connectionFactory.createContext();
        JMSProducer producer = context.createProducer();
        
        String ringTone = songName + ";" + "ringtone";
        TextMessage timeBody = context.createTextMessage(ringTone);
        producer.send(reminderAlarmQueue, timeBody);
        
        return Response.status(Response.Status.OK).entity("Ringtone set to: " + songName).build();
    }
    
    @GET
    @Path("createobligation/{obligation}")
    public Response CreateObligation(@PathParam("obligation") String obligation) {
        TypedQuery<Users> tq = em.createQuery("SELECT u FROM Users u WHERE u.idUser = :currentUser", Users.class);
        tq.setParameter("currentUser", currentUser);
        Users u = tq.getSingleResult();
        String usernameColonPassword = u.getUsername() + ":" + u.getPassword();
        String encodedUserInfo = "Basic " + Base64.getEncoder().encodeToString(usernameColonPassword.getBytes());
        
        JMSContext context = connectionFactory.createContext();
        JMSProducer producer = context.createProducer();
        String obligationText = obligation + ";" + encodedUserInfo + ";" + "createObligation";
        TextMessage obligationBody = context.createTextMessage(obligationText);
        producer.send(plannerQueue, obligationBody);



        return Response.status(Response.Status.OK).entity("Request sent.").build();
            
    }
    
    @DELETE
    @Path("removeobligation/{obligation}")
    public Response RemoveObligation(@PathParam("obligation") String obligation) {
        TypedQuery<Tasks> tq = em.createQuery("SELECT t FROM Tasks t WHERE t.name = :taskName", Tasks.class);
        tq.setParameter("taskName", obligation);
        Tasks t = tq.getSingleResult();
        
        
        if (t != null) {
            em.remove(t);
            JMSContext context = connectionFactory.createContext();
            JMSProducer producer = context.createProducer();

            String obligationText = obligation + ";" + "removeObligation";
            TextMessage obligationBody = context.createTextMessage(obligationText);
            producer.send(plannerQueue, obligationBody);
        }

        return Response.status(Response.Status.OK).entity("Request sent").build();
    }
    
    @PUT
    @Path("updateobligation/{obligation}")
    public Response UpdateObligation(@PathParam("obligation") String obligation) {
        String[] result = obligation.split("_");
        TypedQuery<Tasks> tq = em.createQuery("SELECT t FROM Tasks t WHERE t.name = :taskName", Tasks.class);
        tq.setParameter("taskName", result[0]);
        Tasks task = tq.getSingleResult();

        
        
        if (task != null) {
            task.setName(result[0]);
            task.setStartDate(result[1]);
            task.setStartTime(result[2]);
            task.setEndDate(result[3]);
            task.setEndTime(result[4]);
            task.setIdUser(currentUser);

            if (result.length == 6)
                task.setLocation(result[5]);

            em.persist(task);
            
            JMSContext context = connectionFactory.createContext();
            JMSProducer producer = context.createProducer();

            String obligationText = obligation + ";" + "updateObligation";
            TextMessage obligationBody = context.createTextMessage(obligationText);
            producer.send(plannerQueue, obligationBody);
        }

        return Response.status(Response.Status.OK).entity("Request sent").build();
    }
    
    @GET
    @Path("activatereminder/{obligation}")
    public Response ActivateReminder(@PathParam("obligation") String obligation) {

        JMSContext context = connectionFactory.createContext();
        JMSProducer producer = context.createProducer();

        String obligationText = obligation + ";" + "activateReminder";
        TextMessage obligationBody = context.createTextMessage(obligationText);
        producer.send(plannerQueue, obligationBody);
            
        return Response.status(Response.Status.OK).entity("Request sent").build();
    }
    
    @POST
    @Path("insertTask/{obligation}")
    public Response InsertTask(@PathParam("obligation") String obligation) {

        Tasks task = new Tasks();
        String[] result = obligation.split("_");
        task.setName(result[0]);
        task.setStartDate(result[1]);
        task.setStartTime(result[2]);
        task.setEndDate(result[3]);
        task.setEndTime(result[4]);
        task.setIdUser(currentUser);
        
        if (result.length == 6)
            task.setLocation(result[5]);
        
        em.persist(task);
        
        return Response.status(Response.Status.OK).entity("Task inserted").build();
    }
    
    @GET
    @Path("nexttask")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response nextTask() {
        taskCnt++;
        TypedQuery<Tasks> t = em.createQuery("SELECT t FROM Tasks t WHERE t.idUser = :currentUser", Tasks.class);
        t.setParameter("currentUser", currentUser);
        List<Tasks> tasks = t.getResultList();
        
        String obligation = "";
        if (taskCnt < tasks.size()) {
            obligation = tasks.get(taskCnt).getName() + "_" + tasks.get(taskCnt).getStartDate() + "_" + tasks.get(taskCnt).getStartTime() + "_" + tasks.get(taskCnt).getEndDate() + "_" + tasks.get(taskCnt).getEndTime();
            if (!tasks.get(taskCnt).getLocation().equals("Home")) {
                obligation += "_" + tasks.get(taskCnt).getLocation();
            }
        }
        return Response.status(Response.Status.OK).entity(new ClientResponse(obligation)).build();
    }
    
    @GET
    @Path("previoustask")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response previousTask() {
        taskCnt--;
        TypedQuery<Tasks> t = em.createQuery("SELECT t FROM Tasks t WHERE t.idUser = :currentUser", Tasks.class);
        t.setParameter("currentUser", currentUser);
        List<Tasks> tasks = t.getResultList();
        
        String obligation = "";
        if (taskCnt >= 0) {
            obligation = tasks.get(taskCnt).getName() + "_" + tasks.get(taskCnt).getStartDate() + "_" + tasks.get(taskCnt).getStartTime() + "_" + tasks.get(taskCnt).getEndDate() + "_" + tasks.get(taskCnt).getEndTime();
            if (!tasks.get(taskCnt).getLocation().equals("Home")) {
                obligation += "_" + tasks.get(taskCnt).getLocation();
            }
        }
        return Response.status(Response.Status.OK).entity(new ClientResponse(obligation)).build();
    }
}
