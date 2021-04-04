package MapQuestAPI;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MapQuestService {

    @GET("optimizedroute")
    Call<MapQuestJSON> search(@Query("key") String key, @Query("json") String locationsJSON);
}
