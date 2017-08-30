package okki.setyatmoko.guidepess.Utils;

import android.app.Activity;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;

import okki.setyatmoko.guidepess.Models.BaseModelContent;
import okki.setyatmoko.guidepess.Models.HomeItems;
import okki.setyatmoko.guidepess.Models.ItemDetails;
import okki.setyatmoko.guidepess.Models.MusicModel;

/**
 * Created by Hinata on 8/12/2017.
 */
public class ParsingContentsModel <T>{

    String type;

    public ParsingContentsModel(String type){
        this.type = type;
    }



    public T parse(Activity activity){
        try {
            InputStream is = activity.getAssets().open("contents/"+type+".json");
            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            String json = new String(buffer);
            Gson gson = new Gson();

            JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
            Type listType = new TypeToken<T>(){}.getType();
            switch (type){
                case ContentsName.HOME_ITEMS:
                    listType = new TypeToken<HomeItems>(){}.getType();
                    break;
                case ContentsName.ITEM_DETAILS:
                    listType = new TypeToken<ItemDetails>(){}.getType();
                    break;
                case ContentsName.MUSIC_LIST:
                    listType = new TypeToken<MusicModel>(){}.getType();
                    break;
            }
            is.close();
            return gson.fromJson(jsonObject, listType);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("JSON", e.toString());
        }
        return null;
    }
}
