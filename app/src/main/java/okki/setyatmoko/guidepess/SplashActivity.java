package okki.setyatmoko.guidepess;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import okki.setyatmoko.guidepess.Home.HomeFragmentList;
import okki.setyatmoko.guidepess.Home.ViewPagerAdapter;
import okki.setyatmoko.guidepess.Models.HomeStyle;
import okki.setyatmoko.guidepess.Utils.JsonUtil;
import okki.setyatmoko.guidepess.Utils.TypeUtils;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        TextView btn1 = (TextView) findViewById(R.id.btn1);
        TextView btn2 = (TextView) findViewById(R.id.btn2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JsonUtil.dummySample = "homeStyle.json";
                loadData();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JsonUtil.dummySample = "homeStyleSampleDummy.json";
                loadData();
            }
        });

    }

    private void loadData(){
        HomeStyle homeStyle = JsonUtil.getHomeStyle(this);
        switch (homeStyle.getStyle()){
            case TypeUtils.STYLE_BUTTON:
                Intent i = new Intent(SplashActivity.this, CollapsingActivity.class);
                goTo(i);
                break;
            case TypeUtils.STYLE_TAB:
                Intent intent = new Intent(SplashActivity.this, ToolbarActivity.class);
                goTo(intent);
                break;
        }
    }

    void goTo(Intent i ){
        startActivity(i);
        finish();
    }
}
