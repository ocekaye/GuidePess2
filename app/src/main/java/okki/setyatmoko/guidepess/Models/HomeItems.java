package okki.setyatmoko.guidepess.Models;

import java.util.List;

/**
 * Created by Hinata on 8/12/2017.
 */
public class HomeItems extends BaseModelContent{
    private String type = "";
    private List<DataItems> data;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<DataItems> getData() {
        return data;
    }

    public void setData(List<DataItems> data) {
        this.data = data;
    }

    public class DataItems extends BaseModelContent{
        private String title = "";
        private String img = "";
        private String color = "";
        private ContenData childContent;

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

        public ContenData getChildContent() {
            return childContent;
        }

        public void setChildContent(ContenData childContent) {
            this.childContent = childContent;
        }
    }

}

