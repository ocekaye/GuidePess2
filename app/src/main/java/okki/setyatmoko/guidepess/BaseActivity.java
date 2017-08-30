package okki.setyatmoko.guidepess;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Hinata on 8/12/2017.
 */
public abstract class BaseActivity extends AppCompatActivity {
    public int getDrawableId(String name){
        return getResources().getIdentifier(name, "drawable", getPackageName());
    }
}
