
package MapQuestAPI;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Route {

    @SerializedName("hasTollRoad")
    @Expose
    private Boolean hasTollRoad;
    @SerializedName("hasBridge")
    @Expose
    private Boolean hasBridge;
    @SerializedName("computedWaypoints")
    @Expose
    private List<Object> computedWaypoints = null;
    @SerializedName("fuelUsed")
    @Expose
    private Double fuelUsed;
    @SerializedName("hasTunnel")
    @Expose
    private Boolean hasTunnel;
    @SerializedName("hasUnpaved")
    @Expose
    private Boolean hasUnpaved;
    @SerializedName("hasHighway")
    @Expose
    private Boolean hasHighway;
    @SerializedName("realTime")
    @Expose
    private Integer realTime;
    @SerializedName("boundingBox")
    @Expose
    private BoundingBox boundingBox;
    @SerializedName("distance")
    @Expose
    private Double distance;
    @SerializedName("time")
    @Expose
    private Integer time;
    @SerializedName("locationSequence")
    @Expose
    private List<Integer> locationSequence = null;
    @SerializedName("hasSeasonalClosure")
    @Expose
    private Boolean hasSeasonalClosure;
    @SerializedName("sessionId")
    @Expose
    private String sessionId;
    @SerializedName("locations")
    @Expose
    private List<Location> locations = null;
    @SerializedName("hasCountryCross")
    @Expose
    private Boolean hasCountryCross;
    @SerializedName("legs")
    @Expose
    private List<Leg> legs = null;
    @SerializedName("formattedTime")
    @Expose
    private String formattedTime;
    @SerializedName("routeError")
    @Expose
    private RouteError routeError;
    @SerializedName("options")
    @Expose
    private Options options;
    @SerializedName("hasFerry")
    @Expose
    private Boolean hasFerry;

    public Boolean getHasTollRoad() {
        return hasTollRoad;
    }

    public void setHasTollRoad(Boolean hasTollRoad) {
        this.hasTollRoad = hasTollRoad;
    }

    public Boolean getHasBridge() {
        return hasBridge;
    }

    public void setHasBridge(Boolean hasBridge) {
        this.hasBridge = hasBridge;
    }

    public List<Object> getComputedWaypoints() {
        return computedWaypoints;
    }

    public void setComputedWaypoints(List<Object> computedWaypoints) {
        this.computedWaypoints = computedWaypoints;
    }

    public Double getFuelUsed() {
        return fuelUsed;
    }

    public void setFuelUsed(Double fuelUsed) {
        this.fuelUsed = fuelUsed;
    }

    public Boolean getHasTunnel() {
        return hasTunnel;
    }

    public void setHasTunnel(Boolean hasTunnel) {
        this.hasTunnel = hasTunnel;
    }

    public Boolean getHasUnpaved() {
        return hasUnpaved;
    }

    public void setHasUnpaved(Boolean hasUnpaved) {
        this.hasUnpaved = hasUnpaved;
    }

    public Boolean getHasHighway() {
        return hasHighway;
    }

    public void setHasHighway(Boolean hasHighway) {
        this.hasHighway = hasHighway;
    }

    public Integer getRealTime() {
        return realTime;
    }

    public void setRealTime(Integer realTime) {
        this.realTime = realTime;
    }

    public BoundingBox getBoundingBox() {
        return boundingBox;
    }

    public void setBoundingBox(BoundingBox boundingBox) {
        this.boundingBox = boundingBox;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public List<Integer> getLocationSequence() {
        return locationSequence;
    }

    public void setLocationSequence(List<Integer> locationSequence) {
        this.locationSequence = locationSequence;
    }

    public Boolean getHasSeasonalClosure() {
        return hasSeasonalClosure;
    }

    public void setHasSeasonalClosure(Boolean hasSeasonalClosure) {
        this.hasSeasonalClosure = hasSeasonalClosure;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public Boolean getHasCountryCross() {
        return hasCountryCross;
    }

    public void setHasCountryCross(Boolean hasCountryCross) {
        this.hasCountryCross = hasCountryCross;
    }

    public List<Leg> getLegs() {
        return legs;
    }

    public void setLegs(List<Leg> legs) {
        this.legs = legs;
    }

    public String getFormattedTime() {
        return formattedTime;
    }

    public void setFormattedTime(String formattedTime) {
        this.formattedTime = formattedTime;
    }

    public RouteError getRouteError() {
        return routeError;
    }

    public void setRouteError(RouteError routeError) {
        this.routeError = routeError;
    }

    public Options getOptions() {
        return options;
    }

    public void setOptions(Options options) {
        this.options = options;
    }

    public Boolean getHasFerry() {
        return hasFerry;
    }

    public void setHasFerry(Boolean hasFerry) {
        this.hasFerry = hasFerry;
    }

}
