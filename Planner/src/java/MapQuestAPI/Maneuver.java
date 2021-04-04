
package MapQuestAPI;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Maneuver {

    @SerializedName("signs")
    @Expose
    private List<Sign> signs = null;
    @SerializedName("index")
    @Expose
    private Integer index;
    @SerializedName("maneuverNotes")
    @Expose
    private List<Object> maneuverNotes = null;
    @SerializedName("direction")
    @Expose
    private Integer direction;
    @SerializedName("narrative")
    @Expose
    private String narrative;
    @SerializedName("iconUrl")
    @Expose
    private String iconUrl;
    @SerializedName("distance")
    @Expose
    private Double distance;
    @SerializedName("time")
    @Expose
    private Integer time;
    @SerializedName("linkIds")
    @Expose
    private List<Object> linkIds = null;
    @SerializedName("streets")
    @Expose
    private List<String> streets = null;
    @SerializedName("attributes")
    @Expose
    private Integer attributes;
    @SerializedName("transportMode")
    @Expose
    private String transportMode;
    @SerializedName("formattedTime")
    @Expose
    private String formattedTime;
    @SerializedName("directionName")
    @Expose
    private String directionName;
    @SerializedName("mapUrl")
    @Expose
    private String mapUrl;
    @SerializedName("startPoint")
    @Expose
    private StartPoint startPoint;
    @SerializedName("turnType")
    @Expose
    private Integer turnType;

    public List<Sign> getSigns() {
        return signs;
    }

    public void setSigns(List<Sign> signs) {
        this.signs = signs;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public List<Object> getManeuverNotes() {
        return maneuverNotes;
    }

    public void setManeuverNotes(List<Object> maneuverNotes) {
        this.maneuverNotes = maneuverNotes;
    }

    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    public String getNarrative() {
        return narrative;
    }

    public void setNarrative(String narrative) {
        this.narrative = narrative;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
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

    public List<Object> getLinkIds() {
        return linkIds;
    }

    public void setLinkIds(List<Object> linkIds) {
        this.linkIds = linkIds;
    }

    public List<String> getStreets() {
        return streets;
    }

    public void setStreets(List<String> streets) {
        this.streets = streets;
    }

    public Integer getAttributes() {
        return attributes;
    }

    public void setAttributes(Integer attributes) {
        this.attributes = attributes;
    }

    public String getTransportMode() {
        return transportMode;
    }

    public void setTransportMode(String transportMode) {
        this.transportMode = transportMode;
    }

    public String getFormattedTime() {
        return formattedTime;
    }

    public void setFormattedTime(String formattedTime) {
        this.formattedTime = formattedTime;
    }

    public String getDirectionName() {
        return directionName;
    }

    public void setDirectionName(String directionName) {
        this.directionName = directionName;
    }

    public String getMapUrl() {
        return mapUrl;
    }

    public void setMapUrl(String mapUrl) {
        this.mapUrl = mapUrl;
    }

    public StartPoint getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(StartPoint startPoint) {
        this.startPoint = startPoint;
    }

    public Integer getTurnType() {
        return turnType;
    }

    public void setTurnType(Integer turnType) {
        this.turnType = turnType;
    }

}
