package youtubeAPI;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YoutubeService {

    @GET("search")
    Call<JSONYoutube> search(@Query("key") String key, @Query("q") String q, @Query("part") String part);
}
