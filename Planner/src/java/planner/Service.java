/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planner;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
/**
 *
 * @author Filip
 */
public interface Service {
    
    @POST("insertTask/{obligation}")
    Call<Void> InsertTask(@Path("obligation") String obligation, @Header("Authorization") Object authorization);
    
}
