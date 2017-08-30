package okki.setyatmoko.guidepess.SettingBackendApp;

import android.graphics.Color;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import okki.setyatmoko.guidepess.Home.HomeModel;
import okki.setyatmoko.guidepess.R;

/**
 * Created by Hinata on 8/10/2017.
 */
public class Settings {
    public static String TYPE_HOME_TAB = "com.setyatmoko.withtab";
    public static String TYPE_HOME_MORE_RATE_BUTTON = "com.setyatmoko.morerate";

    /**
     * ganti TYPE_HOME_TAB dengan type:
     * 1. TYPE_HOME_TAB untuk home dengn tampilan tabs.
     * 2. TYPE_HOME_MORE_RATE_BUTTON untuk rampilan tombol More App dan Rate.
     */
    public static String setHomeTopType(){
      return TYPE_HOME_MORE_RATE_BUTTON; //pilih TYPE_HOME_TAB atau TYPE_HOME_MORE_RATE_BUTTON
    }

    /**
     * ganti judul tab jika type tampilan tab
     * @return judul tab
     */
    public static String[] setHomeTabTitle(){
        String[] titleTab = {"Music", "Favorite"}; //ganti judul tab

        return titleTab;
    }

    /**
     * data list item home
     * @return List<HomeModel>
     */
    public static List<HomeModel> setItemHome(){
        List<HomeModel> item = new ArrayList<>();

        //edit bagian ini
        // ex: "icon_home" adalah nama gambar di res/drawable
       /* item.add(new HomeModel("How To Play", "icon_home")); // tambah item tanpa warna background
        item.add(new HomeModel("How To Free Kick", "cs", Color.BLUE)); // tambah item dengan wrna
        item.add(new HomeModel("Tips and Trick",  "cs", Color.CYAN));
        item.add(new HomeModel("How To Attack",  "cs", Color.GREEN));
*/
        //sampai sini

        return item;
    }
}
