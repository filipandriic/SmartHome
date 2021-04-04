
package MapQuestAPI;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Leg {

    @SerializedName("hasTollRoad")
    @Expose
    private Boolean hasTollRoad;
    @SerializedName("index")
    @Expose
    private Integer index;
    @SerializedName("hasBridge")
    @Expose
    private Boolean hasBridge;
    @SerializedName("hasTunnel")
    @Expose
    private Boolean hasTunnel;
    @SerializedName("roadGradeStrategy")
    @Expose
    private List<List<Object>> roadGradeStrategy = null;
    @SerializedName("hasHighway")
    @Expose
    private Boolean hasHighway;
    @SerializedName("hasUnpaved")
    @Expose
    private Boolean hasUnpaved;
    @SerializedName("distance")
    @Expose
    private Double distance;
    @SerializedName("time")
    @Expose
    private Integer time;
    @SerializedName("origIndex")
    @Expose
    private Integer origIndex;
    @SerializedName("hasSeasonalClosure")
    @Expose
    private Boolean hasSeasonalClosure;
    @SerializedName("origNarrative")
    @Expose
    private String origNarrative;
    @SerializedName("hasCountryCross")
    @Expose
    private Boolean hasCountryCross;
    @SerializedName("formattedTime")
    @Expose
    private String formattedTime;
    @SerializedName("destNarrative")
    @Expose
    private String destNarrative;
    @SerializedName("destIndex")
    @Expose
    private Integer destIndex;
    @SerializedName("maneuvers")
    @Expose
    private List<Maneuver> maneuvers = null;
    @SerializedName("hasFerry")
    @Expose
    private Boolean hasFerry;

    public Boolean getHasTollRoad() {
        return hasTollRoad;
    }

    public void setHasTollRoad(Boolean hasTollRoad) {
        this.hasTollRoad = hasTollRoad;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Boolean getHasBridge() {
        return hasBridge;
    }

    public void setHasBridge(Boolean hasBridge) {
        this.hasBridge = hasBridge;
    }

    public Boolean getHasTunnel() {
        return hasTunnel;
    }

    public void setHasTunnel(Boolean hasTunnel) {
        this.hasTunnel = hasTunnel;
    }

    public List<List<Object>> getRoadGradeStrategy() {
        return roadGradeStrategy;
    }

    public void setRoadGradeStrategy(List<List<Object>> roadGradeStrategy) {
        this.roadGradeStrategy = roadGradeStrategy;
    }

    public Boolean getHasHighway() {
        return hasHighway;
    }

    public void setHasHighway(Boolean hasHighway) {
        this.hasHighway = hasHighway;
    }

    public Boolean getHasUnpaved() {
        return hasUnpaved;
    }

    public void setHasUnpaved(Boolean hasUnpaved) {
        this.hasUnpaved = hasUnpaved;
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

    public Integer getOrigIndex() {
        return origIndex;
    }

    public void setOrigIndex(Integer origIndex) {
        this.origIndex = origIndex;
    }

    public Boolean getHasSeasonalClosure() {
        return hasSeasonalClosure;
    }

    public void setHasSeasonalClosure(Boolean hasSeasonalClosure) {
        this.hasSeasonalClosure = hasSeasonalClosure;
    }

    public String getOrigNarrative() {
        return origNarrative;
    }

    public void setOrigNarrative(String origNarrative) {
        this.origNarrative = origNarrative;
    }

    public Boolean getHasCountryCross() {
        return hasCountryCross;
    }

    public void setHasCountryCross(Boolean hasCountryCross) {
        this.hasCountryCross = hasCountryCross;
    }

    public String getFormattedTime() {
        return formattedTime;
    }

    public void setFormattedTime(String formattedTime) {
        this.formattedTime = formattedTime;
    }

    public String getDestNarrative() {
        return destNarrative;
    }

    public void setDestNarrative(String destNarrative) {
        this.destNarrative = destNarrative;
    }

    public Integer getDestIndex() {
        return destIndex;
    }

    public void setDestIndex(Integer destIndex) {
        this.destIndex = destIndex;
    }

    public List<Maneuver> getManeuvers() {
        return maneuvers;
    }

    public void setManeuvers(List<Maneuver> maneuvers) {
        this.maneuvers = maneuvers;
    }

    public Boolean getHasFerry() {
        return hasFerry;
    }

    public void setHasFerry(Boolean hasFerry) {
        this.hasFerry = hasFerry;
    }

}
