package okki.setyatmoko.guidepess.Home;

import android.graphics.Color;

/**
 * Created by Hinata on 8/11/2017.
 */
public class HomeModel {
    private String title = "";
    private String img = "cs";
    private String color = "#000000";

    public HomeModel(String title, String img){
        this.title = title;
        this.img = img;
    }

    public HomeModel(String title, String img, String bg){
        this.title = title;
        this.img = img;
        this.color = bg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
