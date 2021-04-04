
package MapQuestAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sign {

    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("extraText")
    @Expose
    private String extraText;
    @SerializedName("direction")
    @Expose
    private Integer direction;
    @SerializedName("type")
    @Expose
    private Integer type;
    @SerializedName("url")
    @Expose
    private String url;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getExtraText() {
        return extraText;
    }

    public void setExtraText(String extraText) {
        this.extraText = extraText;
    }

    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
