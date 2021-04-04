package MapQuestAPI;

import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import retrofit2.converter.gson.GsonConverterFactory;

public class MapQuestProvider {

    private static MapQuestService service = null;

    public MapQuestProvider() {
        if (service == null) {
            Retrofit retrofit = new Retrofit
                    .Builder()
                    .baseUrl("http://www.mapquestapi.com/directions/v2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            service = retrofit.create(MapQuestService.class);
        }
    }

    public MapQuestJSON GetTravelTime(String locationFrom, String locationTo) {
        Response<MapQuestJSON> MapQuestResponse = null;
        try {
            String locations = "{\"locations\":[\"" + locationFrom + "\",\"" + locationTo + "\"]}";
            MapQuestResponse = service.search("RqHWAPqGGxE6CbzOEy1IkIaCRqggRr8a", locations).execute();
        } catch (IOException ex) {
            Logger.getLogger(MapQuestProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return MapQuestResponse.body();
    }
}
