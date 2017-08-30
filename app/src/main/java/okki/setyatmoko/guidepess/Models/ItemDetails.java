package okki.setyatmoko.guidepess.Models;

/**
 * Created by Hinata on 8/12/2017.
 */
public class ItemDetails extends BaseModelContent{
    private String type = "";
    private String imageCover = "";
    private ContenData content;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImageCover() {
        return imageCover;
    }

    public void setImageCover(String imageCover) {
        this.imageCover = imageCover;
    }

    public ContenData getContent() {
        return content;
    }

    public void setContent(ContenData content) {
        this.content = content;
    }
}
