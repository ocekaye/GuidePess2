package okki.setyatmoko.guidepess.Models;

import android.app.Activity;
import android.support.v4.app.Fragment;

import okki.setyatmoko.guidepess.CollapsingActivity;
import okki.setyatmoko.guidepess.Sub.lvl1.FragmentItemDetails;
import okki.setyatmoko.guidepess.ToolbarActivity;
import okki.setyatmoko.guidepess.Utils.ParsingContentsModel;
import okki.setyatmoko.guidepess.Utils.TypeUtils;

/**
 * Created by Hinata on 8/12/2017.
 */
public class BaseModelContent {
    public void onCardClick(Activity activity, ContenData contenData, String title){
        switch (contenData.getType()){
            case TypeUtils.TYPE_DETAIL_HEADER_IMAGE:
                ItemDetails itemDetails = new ParsingContentsModel<ItemDetails>(contenData.getContentName()).parse(activity);
               goTo(activity, FragmentItemDetails.newInstance(title, itemDetails), contenData, title);
                break;
        }
    }

    public void PlayMusic(Activity activity, ContenData contenData, Music music, String title){
        if (activity instanceof CollapsingActivity){
//            ((CollapsingActivity) activity).changeFragment(fragment);
        } else if(activity instanceof ToolbarActivity){
            ((ToolbarActivity) activity).changeFragmentMusicAction(contenData, music, title);
        }
    }

    private void goTo(Activity activity, Fragment fragment, ContenData contenData, String title){
        if (activity instanceof CollapsingActivity){
            ((CollapsingActivity) activity).changeFragment(fragment);
        } else if(activity instanceof ToolbarActivity){
            ((ToolbarActivity) activity).changeFragment(fragment, contenData, title);
        }
    }
}
