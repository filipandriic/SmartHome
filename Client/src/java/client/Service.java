/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;


import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 *
 * @author Filip
 */
public interface Service {
    @GET("login")
    Call<ClientResponse> login(@Header("Authorization") Object authorization);
    
    @POST("youtube/{songName}")
    Call<ClientResponse> playSong(@Path("songName") String songName, @Header("Authorization") Object authorization);
    
    @POST("setalarm/{time}")
    Call<ClientResponse> SetAlarmTime(@Path("time") String time, @Header("Authorization") Object authorization);
    
    @POST("setperiodicalarm/{time}/{period}")
    Call<ClientResponse> SetPeriodicAlarmTime(@Path("time") String time, @Path("period") String period, @Header("Authorization") Object authorization);
    
    @POST("setofferedalarm/{offeredtime}")
    Call<ClientResponse> SetOfferedAlarmTime(@Path("offeredtime") String offeredtime, @Header("Authorization") Object authorization);
    
    @GET("setringtone/{songName}")
    Call<Void> SetRingtone(@Path("songName") String songName, @Header("Authorization") Object authorization);
    
    @GET("createobligation/{obligation}")
    Call<Void> CreateObligation(@Path("obligation") String obligation, @Header("Authorization") Object authorization);
    
    @DELETE("removeobligation/{obligation}")
    Call<Void> RemoveObligation(@Path("obligation") String obligation, @Header("Authorization") Object authorization);
    
    @PUT("updateobligation/{obligation}")
    Call<Void> UpdateObligation(@Path("obligation") String obligation, @Header("Authorization") Object authorization);
    
    @GET("activatereminder/{obligation}")
    Call<Void> ActivateReminder(@Path("obligation") String obligation, @Header("Authorization") Object authorization);
    
    @GET("nexttask")
    Call<ClientResponse> nextTask(@Header("Authorization") Object authorization);
    
    @GET("previoustask")
    Call<ClientResponse> previousTask(@Header("Authorization") Object authorization);
}
