
package MapQuestAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BoundingBox {

    @SerializedName("ul")
    @Expose
    private Ul ul;
    @SerializedName("lr")
    @Expose
    private Lr lr;

    public Ul getUl() {
        return ul;
    }

    public void setUl(Ul ul) {
        this.ul = ul;
    }

    public Lr getLr() {
        return lr;
    }

    public void setLr(Lr lr) {
        this.lr = lr;
    }

}
