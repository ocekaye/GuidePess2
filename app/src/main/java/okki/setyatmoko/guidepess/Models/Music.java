package okki.setyatmoko.guidepess.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Hinata on 8/13/2017.
 */
public class Music  extends BaseModelContent implements Parcelable {
    String title = "";
    String sub1 = "";
    String sub2 = "";
    String img = "";
    String url = "";
    String liric = "";
    ContenData childContent;

    protected Music(Parcel in) {
        title = in.readString();
        sub1 = in.readString();
        sub2 = in.readString();
        img = in.readString();
        url = in.readString();
        liric = in.readString();
        childContent = in.readParcelable(ContenData.class.getClassLoader());
    }

    public static final Creator<Music> CREATOR = new Creator<Music>() {
        @Override
        public Music createFromParcel(Parcel in) {
            return new Music(in);
        }

        @Override
        public Music[] newArray(int size) {
            return new Music[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSub1() {
        return sub1;
    }

    public void setSub1(String sub1) {
        this.sub1 = sub1;
    }

    public String getSub2() {
        return sub2;
    }

    public void setSub2(String sub2) {
        this.sub2 = sub2;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLiric() {
        return liric;
    }

    public void setLiric(String liric) {
        this.liric = liric;
    }

    public ContenData getChildContent() {
        return childContent;
    }

    public void setChildContent(ContenData childContent) {
        this.childContent = childContent;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(sub1);
        parcel.writeString(sub2);
        parcel.writeString(img);
        parcel.writeString(url);
        parcel.writeString(liric);
        parcel.writeParcelable(childContent, i);
    }
}
