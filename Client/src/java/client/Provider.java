/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.util.Base64;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.MediaType;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 *
 * @author Filip
 */
public class Provider {
    private static Service service = null;
    private static String encodedUserInfo;
    
    public Provider() {
        if (service == null) {
            Retrofit retrofit = new Retrofit
                    .Builder()
                    .baseUrl("http://localhost:8080/UserService/smarthome/applications/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            service = retrofit.create(Service.class);
        }
    }
    
    public String LogIn(String username, String password) {
        try {
            String usernameColonPassword = username + ":" + password;
            encodedUserInfo = "Basic " + Base64.getEncoder().encodeToString(usernameColonPassword.getBytes()); 
            
            Response<ClientResponse> loginInfo = service.login(encodedUserInfo).execute();
            
            if (loginInfo.isSuccessful()) {
                System.out.println("Succesfull login");
                return loginInfo.body().getResponse();
            }
            
            System.out.println("Wrong credentials");
            return "Wrong credentials";
            
        } catch (IOException ex) {
            Logger.getLogger(Provider.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Wrong credentials";
    }
    
    public void playSong(String songName) {
        try {
            Response<ClientResponse> sss = service.playSong(songName, encodedUserInfo).execute();
            Main.addSong(sss.body().getResponse());
        } catch (IOException ex) {
            Logger.getLogger(Provider.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setAlarm(String time) {
        try {
            Response<ClientResponse> sss = service.SetAlarmTime(time, encodedUserInfo).execute();
            Main.addAlarm(sss.body().getResponse());
        } catch (IOException ex) {
            Logger.getLogger(Provider.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setPeriodicAlarm(String time, String period) {
        try {
            Response<ClientResponse> sss = service.SetPeriodicAlarmTime(time, period, encodedUserInfo).execute();
            Main.addAlarm(sss.body().getResponse());
        } catch (IOException ex) {
            Logger.getLogger(Provider.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setOfferedAlarm(String offeredTimes) {
        try {
            Response<ClientResponse> sss = service.SetOfferedAlarmTime(offeredTimes, encodedUserInfo).execute();
            Main.addAlarm(sss.body().getResponse());
        } catch (IOException ex) {
            Logger.getLogger(Provider.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setRingtone(String ringtone) {
        try {
            Response<Void> sss = service.SetRingtone(ringtone, encodedUserInfo).execute();
        } catch (IOException ex) {
            Logger.getLogger(Provider.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void createObligation(String obligation) {
        try {
            Response<Void> sss = service.CreateObligation(obligation, encodedUserInfo).execute();
        } catch (IOException ex) {
            Logger.getLogger(Provider.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void removeObligation(String obligation) {
        try {
            Response<Void> sss = service.RemoveObligation(obligation, encodedUserInfo).execute();
        } catch (IOException ex) {
            Logger.getLogger(Provider.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateObligation(String obligation) {
        try {
            Response<Void> sss = service.UpdateObligation(obligation, encodedUserInfo).execute();
        } catch (IOException ex) {
            Logger.getLogger(Provider.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setReminder(String obligation) {
        try {
            Response<Void> sss = service.ActivateReminder(obligation, encodedUserInfo).execute();
        } catch (IOException ex) {
            Logger.getLogger(Provider.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String nextTask() {
        try {
            Response<ClientResponse> sss = service.nextTask(encodedUserInfo).execute();
            return sss.body().getResponse();
        } catch (IOException ex) {
            Logger.getLogger(Provider.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public String previousTask() {
        try {
            Response<ClientResponse> sss = service.previousTask(encodedUserInfo).execute();
            return sss.body().getResponse();
        } catch (IOException ex) {
            Logger.getLogger(Provider.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
