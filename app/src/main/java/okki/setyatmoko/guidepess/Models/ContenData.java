package okki.setyatmoko.guidepess.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Hinata on 8/12/2017.
 */
public class ContenData implements Parcelable {
    String type = "";
    String contentName = "";

    public ContenData(Parcel in) {
        type = in.readString();
        contentName = in.readString();
    }

    public ContenData(){

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContentName() {
        return contentName;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(type);
        parcel.writeString(contentName);
    }

    public static final Parcelable.Creator<ContenData> CREATOR
            = new Parcelable.Creator<ContenData>() {
        public ContenData createFromParcel(Parcel in) {
            return new ContenData(in);
        }

        public ContenData[] newArray(int size) {
            return new ContenData[size];
        }
    };
}
