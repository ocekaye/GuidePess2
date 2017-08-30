package okki.setyatmoko.guidepess;

import android.support.v4.app.Fragment;

/**
 * Created by Hinata on 8/11/2017.
 */
public abstract class BaseFragment extends Fragment {

    public int getExpandedImage(){
        return 0;
    }

    public String getExpandedUrl(){
        return "";
    }

    public boolean showBackButton(){
        return false;
    }

    public abstract String getTitle();

    public boolean transparantTitle(){
        return true;
    }
}
