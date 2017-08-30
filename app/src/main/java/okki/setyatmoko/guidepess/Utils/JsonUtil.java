package okki.setyatmoko.guidepess.Utils;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import okki.setyatmoko.guidepess.Home.HomeModel;
import okki.setyatmoko.guidepess.Models.BaseModelContent;
import okki.setyatmoko.guidepess.Models.HomeItems;
import okki.setyatmoko.guidepess.Models.HomeStyle;

/**
 * Created by Hinata on 8/11/2017.
 */
public class JsonUtil {

    public static String dummySample = "homeStyleSampleDummy.json";

    public static HomeStyle getHomeStyle(Activity activity){
        HomeStyle homeStyle = new HomeStyle();
        try {
            InputStream is = activity.getAssets().open(dummySample);
            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            String json = new String(buffer);
            Gson gson = new Gson();

            JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
            Type listType = new TypeToken<HomeStyle>(){}.getType();
            homeStyle = gson.fromJson(jsonObject, listType);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("JSON", e.toString());
        }
        return homeStyle;
    }

    public static BaseModelContent getContent(Activity activity, String content){
        BaseModelContent baseModelContent = new BaseModelContent();
        try {
            InputStream is = activity.getAssets().open("contents/"+content+".json");
            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            String json = new String(buffer);
            Gson gson = new Gson();

            JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
            Type listType = null;
            switch (content){
                case ContentsName.HOME_ITEMS:
                    listType = new TypeToken<HomeItems>(){}.getType();
                    break;
                case ContentsName.ITEM_DETAILS:
                    break;
            }
            baseModelContent = gson.fromJson(jsonObject, listType);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("JSON", e.toString());
        }

        return baseModelContent;
    }

    public static List<HomeModel> getHomeData(Activity activity){
        List<HomeModel> homeModels = new ArrayList<>();
        try {
            InputStream is = activity.getAssets().open("data.json");
            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            String json = new String(buffer);
            Gson gson = new Gson();

            JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
            Type listType = new TypeToken<List<HomeModel>>(){}.getType();
            homeModels = (gson.fromJson(jsonObject.get("homeItems").toString(), listType));
            Log.e("JSON", homeModels.get(0).getTitle());
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("JSON", e.toString());
        }
        return homeModels;
    }

}
