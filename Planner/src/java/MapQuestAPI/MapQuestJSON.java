
package MapQuestAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MapQuestJSON {

    @SerializedName("route")
    @Expose
    private Route route;
    @SerializedName("info")
    @Expose
    private Info info;

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

}
