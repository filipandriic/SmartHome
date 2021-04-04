
package MapQuestAPI;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Options {

    @SerializedName("mustAvoidLinkIds")
    @Expose
    private List<Object> mustAvoidLinkIds = null;
    @SerializedName("drivingStyle")
    @Expose
    private Integer drivingStyle;
    @SerializedName("countryBoundaryDisplay")
    @Expose
    private Boolean countryBoundaryDisplay;
    @SerializedName("generalize")
    @Expose
    private Integer generalize;
    @SerializedName("narrativeType")
    @Expose
    private String narrativeType;
    @SerializedName("locale")
    @Expose
    private String locale;
    @SerializedName("avoidTimedConditions")
    @Expose
    private Boolean avoidTimedConditions;
    @SerializedName("destinationManeuverDisplay")
    @Expose
    private Boolean destinationManeuverDisplay;
    @SerializedName("enhancedNarrative")
    @Expose
    private Boolean enhancedNarrative;
    @SerializedName("filterZoneFactor")
    @Expose
    private Integer filterZoneFactor;
    @SerializedName("timeType")
    @Expose
    private Integer timeType;
    @SerializedName("maxWalkingDistance")
    @Expose
    private Integer maxWalkingDistance;
    @SerializedName("routeType")
    @Expose
    private String routeType;
    @SerializedName("transferPenalty")
    @Expose
    private Integer transferPenalty;
    @SerializedName("walkingSpeed")
    @Expose
    private Integer walkingSpeed;
    @SerializedName("stateBoundaryDisplay")
    @Expose
    private Boolean stateBoundaryDisplay;
    @SerializedName("maxLinkId")
    @Expose
    private Integer maxLinkId;
    @SerializedName("arteryWeights")
    @Expose
    private List<Object> arteryWeights = null;
    @SerializedName("tryAvoidLinkIds")
    @Expose
    private List<Object> tryAvoidLinkIds = null;
    @SerializedName("unit")
    @Expose
    private String unit;
    @SerializedName("routeNumber")
    @Expose
    private Integer routeNumber;
    @SerializedName("doReverseGeocode")
    @Expose
    private Boolean doReverseGeocode;
    @SerializedName("shapeFormat")
    @Expose
    private String shapeFormat;
    @SerializedName("maneuverPenalty")
    @Expose
    private Integer maneuverPenalty;
    @SerializedName("useTraffic")
    @Expose
    private Boolean useTraffic;
    @SerializedName("returnLinkDirections")
    @Expose
    private Boolean returnLinkDirections;
    @SerializedName("avoidTripIds")
    @Expose
    private List<Object> avoidTripIds = null;
    @SerializedName("manmaps")
    @Expose
    private String manmaps;
    @SerializedName("highwayEfficiency")
    @Expose
    private Integer highwayEfficiency;
    @SerializedName("sideOfStreetDisplay")
    @Expose
    private Boolean sideOfStreetDisplay;
    @SerializedName("cyclingRoadFactor")
    @Expose
    private Integer cyclingRoadFactor;
    @SerializedName("urbanAvoidFactor")
    @Expose
    private Integer urbanAvoidFactor;

    public List<Object> getMustAvoidLinkIds() {
        return mustAvoidLinkIds;
    }

    public void setMustAvoidLinkIds(List<Object> mustAvoidLinkIds) {
        this.mustAvoidLinkIds = mustAvoidLinkIds;
    }

    public Integer getDrivingStyle() {
        return drivingStyle;
    }

    public void setDrivingStyle(Integer drivingStyle) {
        this.drivingStyle = drivingStyle;
    }

    public Boolean getCountryBoundaryDisplay() {
        return countryBoundaryDisplay;
    }

    public void setCountryBoundaryDisplay(Boolean countryBoundaryDisplay) {
        this.countryBoundaryDisplay = countryBoundaryDisplay;
    }

    public Integer getGeneralize() {
        return generalize;
    }

    public void setGeneralize(Integer generalize) {
        this.generalize = generalize;
    }

    public String getNarrativeType() {
        return narrativeType;
    }

    public void setNarrativeType(String narrativeType) {
        this.narrativeType = narrativeType;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public Boolean getAvoidTimedConditions() {
        return avoidTimedConditions;
    }

    public void setAvoidTimedConditions(Boolean avoidTimedConditions) {
        this.avoidTimedConditions = avoidTimedConditions;
    }

    public Boolean getDestinationManeuverDisplay() {
        return destinationManeuverDisplay;
    }

    public void setDestinationManeuverDisplay(Boolean destinationManeuverDisplay) {
        this.destinationManeuverDisplay = destinationManeuverDisplay;
    }

    public Boolean getEnhancedNarrative() {
        return enhancedNarrative;
    }

    public void setEnhancedNarrative(Boolean enhancedNarrative) {
        this.enhancedNarrative = enhancedNarrative;
    }

    public Integer getFilterZoneFactor() {
        return filterZoneFactor;
    }

    public void setFilterZoneFactor(Integer filterZoneFactor) {
        this.filterZoneFactor = filterZoneFactor;
    }

    public Integer getTimeType() {
        return timeType;
    }

    public void setTimeType(Integer timeType) {
        this.timeType = timeType;
    }

    public Integer getMaxWalkingDistance() {
        return maxWalkingDistance;
    }

    public void setMaxWalkingDistance(Integer maxWalkingDistance) {
        this.maxWalkingDistance = maxWalkingDistance;
    }

    public String getRouteType() {
        return routeType;
    }

    public void setRouteType(String routeType) {
        this.routeType = routeType;
    }

    public Integer getTransferPenalty() {
        return transferPenalty;
    }

    public void setTransferPenalty(Integer transferPenalty) {
        this.transferPenalty = transferPenalty;
    }

    public Integer getWalkingSpeed() {
        return walkingSpeed;
    }

    public void setWalkingSpeed(Integer walkingSpeed) {
        this.walkingSpeed = walkingSpeed;
    }

    public Boolean getStateBoundaryDisplay() {
        return stateBoundaryDisplay;
    }

    public void setStateBoundaryDisplay(Boolean stateBoundaryDisplay) {
        this.stateBoundaryDisplay = stateBoundaryDisplay;
    }

    public Integer getMaxLinkId() {
        return maxLinkId;
    }

    public void setMaxLinkId(Integer maxLinkId) {
        this.maxLinkId = maxLinkId;
    }

    public List<Object> getArteryWeights() {
        return arteryWeights;
    }

    public void setArteryWeights(List<Object> arteryWeights) {
        this.arteryWeights = arteryWeights;
    }

    public List<Object> getTryAvoidLinkIds() {
        return tryAvoidLinkIds;
    }

    public void setTryAvoidLinkIds(List<Object> tryAvoidLinkIds) {
        this.tryAvoidLinkIds = tryAvoidLinkIds;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getRouteNumber() {
        return routeNumber;
    }

    public void setRouteNumber(Integer routeNumber) {
        this.routeNumber = routeNumber;
    }

    public Boolean getDoReverseGeocode() {
        return doReverseGeocode;
    }

    public void setDoReverseGeocode(Boolean doReverseGeocode) {
        this.doReverseGeocode = doReverseGeocode;
    }

    public String getShapeFormat() {
        return shapeFormat;
    }

    public void setShapeFormat(String shapeFormat) {
        this.shapeFormat = shapeFormat;
    }

    public Integer getManeuverPenalty() {
        return maneuverPenalty;
    }

    public void setManeuverPenalty(Integer maneuverPenalty) {
        this.maneuverPenalty = maneuverPenalty;
    }

    public Boolean getUseTraffic() {
        return useTraffic;
    }

    public void setUseTraffic(Boolean useTraffic) {
        this.useTraffic = useTraffic;
    }

    public Boolean getReturnLinkDirections() {
        return returnLinkDirections;
    }

    public void setReturnLinkDirections(Boolean returnLinkDirections) {
        this.returnLinkDirections = returnLinkDirections;
    }

    public List<Object> getAvoidTripIds() {
        return avoidTripIds;
    }

    public void setAvoidTripIds(List<Object> avoidTripIds) {
        this.avoidTripIds = avoidTripIds;
    }

    public String getManmaps() {
        return manmaps;
    }

    public void setManmaps(String manmaps) {
        this.manmaps = manmaps;
    }

    public Integer getHighwayEfficiency() {
        return highwayEfficiency;
    }

    public void setHighwayEfficiency(Integer highwayEfficiency) {
        this.highwayEfficiency = highwayEfficiency;
    }

    public Boolean getSideOfStreetDisplay() {
        return sideOfStreetDisplay;
    }

    public void setSideOfStreetDisplay(Boolean sideOfStreetDisplay) {
        this.sideOfStreetDisplay = sideOfStreetDisplay;
    }

    public Integer getCyclingRoadFactor() {
        return cyclingRoadFactor;
    }

    public void setCyclingRoadFactor(Integer cyclingRoadFactor) {
        this.cyclingRoadFactor = cyclingRoadFactor;
    }

    public Integer getUrbanAvoidFactor() {
        return urbanAvoidFactor;
    }

    public void setUrbanAvoidFactor(Integer urbanAvoidFactor) {
        this.urbanAvoidFactor = urbanAvoidFactor;
    }

}
