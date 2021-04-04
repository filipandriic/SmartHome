package youtubeAPI;

import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import retrofit2.converter.gson.GsonConverterFactory;

public class YoutubeProvider {

    private static YoutubeService service = null;

    public YoutubeProvider() {
        if (service == null) {
            Retrofit retrofit = new Retrofit
                    .Builder()
                    .baseUrl("https://www.googleapis.com/youtube/v3/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            service = retrofit.create(YoutubeService.class);
        }
    }

    public JSONYoutube SearchForSongOnYoutube(String songName) {
        Response<JSONYoutube> SearchYoutubeResponse = null;
        try {
            SearchYoutubeResponse = service.search("AIzaSyDb7r0UOP_aKfyB3anPOhoODiEvyoOt1oc", songName, "snippet").execute();
        } catch (IOException ex) {
            Logger.getLogger(YoutubeProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return SearchYoutubeResponse.body();
    }
}
