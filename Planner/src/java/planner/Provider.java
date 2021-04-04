/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planner;


import java.util.Base64;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 * @author Filip
 */
public class Provider {
    private static Service service = null;
    
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
    
    public void insertTask(String obligation, String encodedUserInfo) {
        try {
            Response<Void> sss = service.InsertTask(obligation, encodedUserInfo).execute();
        } catch (IOException ex) {
            Logger.getLogger(Provider.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
