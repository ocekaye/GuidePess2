package okki.setyatmoko.guidepess.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Hinata on 8/13/2017.
 */
public class MusicModel extends BaseModelContent{
    String type = "";
    List<Music> data;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Music> getData() {
        return data;
    }

    public void setData(List<Music> data) {
        this.data = data;
    }


}
