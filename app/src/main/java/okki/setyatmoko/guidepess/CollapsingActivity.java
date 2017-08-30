package okki.setyatmoko.guidepess;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import okki.setyatmoko.guidepess.Home.HomeFragmentList;
import okki.setyatmoko.guidepess.Home.HomeModel;
import okki.setyatmoko.guidepess.Models.BaseModelContent;
import okki.setyatmoko.guidepess.Models.ContenData;
import okki.setyatmoko.guidepess.Models.HomeItems;
import okki.setyatmoko.guidepess.Models.HomeStyle;
import okki.setyatmoko.guidepess.Models.ItemDetails;
import okki.setyatmoko.guidepess.Models.Music;
import okki.setyatmoko.guidepess.Sub.lvl1.FragmentItemDetails;
import okki.setyatmoko.guidepess.Sub.lvl1.FragmentItemMusic;
import okki.setyatmoko.guidepess.Utils.ContentsName;
import okki.setyatmoko.guidepess.Utils.JsonUtil;
import okki.setyatmoko.guidepess.Utils.ParsingContentsModel;
import okki.setyatmoko.guidepess.Utils.TypeUtils;

public class CollapsingActivity extends BaseActivity {
    private CollapsingToolbarLayout toolbarLayout;
    private ImageView expandedImage;
    private AppBarLayout appBarLayout;
    private BaseFragment fragment;
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsing);
        appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        setSupportActionBar(toolbar);

        expandedImage = (ImageView) findViewById(R.id.expandedImage);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbarLayout.setTransitionName("title");
        }


         fragmentManager = getSupportFragmentManager();


        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                appBarLayout.setExpanded(false, true);
                if (fragmentManager.getFragments().get(fragmentManager.getBackStackEntryCount()) instanceof  BaseFragment)
                    fragment = (BaseFragment) fragmentManager.getFragments().get(fragmentManager.getBackStackEntryCount());
                toolbarLayout.setTitle(fragment.getTitle());
                showBackButton(fragment.showBackButton());
                transparantTitle(fragment.transparantTitle());
                animateAppbar();

            }
        });

        Bundle bundle = this.getIntent().getExtras();
        if (bundle == null){
            HomeStyle homeStyle = JsonUtil.getHomeStyle(this);

            if (homeStyle.getStyle().equals(TypeUtils.STYLE_BUTTON)){
                switch (homeStyle.getButonData().getContent().getType()){
                    case TypeUtils.LIST_ONE_LINE_COVER:
                        HomeItems homeItems = new ParsingContentsModel<HomeItems>(homeStyle.getButonData().getContent().getContentName()).parse(this);
                        HomeFragmentList homeFragmentList = HomeFragmentList
                                .newInstance(getResources().getString(R.string.app_name), TypeUtils.STYLE_BUTTON, homeStyle.getButonData().getContent());
                        replaceFragment(homeFragmentList);

                        break;
                    case TypeUtils.TYPE_DETAIL_HEADER_IMAGE:
                        break;
                }
            }
        } else {
            ContenData contenData = bundle.getParcelable("contentData");
            switch (contenData.getType()){
                case TypeUtils.TYPE_DETAIL_HEADER_IMAGE:
                    ItemDetails itemDetails = new ParsingContentsModel<ItemDetails>(contenData.getContentName()).parse(this);
                    replaceFragment(FragmentItemDetails.newInstance(getIntent().getExtras().getString("title"), itemDetails));
                    break;
                case TypeUtils.ACTION_PLAY_MUSIC:
                    replaceFragment(FragmentItemMusic.newInstance(getIntent().getExtras().getString("title"), (Music) bundle.getParcelable("music")));
                    break;
            }
        }
    }

    private void animateAppbar(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (fragment.getExpandedImage() == 0)
                    channgeExpandedImage(fragment.getExpandedUrl());
                 else
                    channgeExpandedImage(fragment.getExpandedImage());
                appBarLayout.setExpanded(true, true);
            }
        }, 200);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Log.e("back", item.getItemId()+"");
        int id = item.getItemId();

        switch (id){
            case R.id.action_settings:
                return true;
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void changeFragment(Fragment f){
        fragment = (BaseFragment) f;
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, f).addToBackStack(null).commit();
        appBarLayout.setExpanded(true, true);
    }

    public void replaceFragment(Fragment f){
        fragmentManager.beginTransaction()
                .add(R.id.main_fragment,f)
                .commit();

        fragment = (BaseFragment) f;
//        appBarLayout.setExpanded(false, false);
        toolbarLayout.setTitle(fragment.getTitle());
        showBackButton(fragment.showBackButton());
        transparantTitle(fragment.transparantTitle());
        animateAppbar();
    }

    public void channgeExpandedImage(int img){
        Glide.with(this).load(img).into(expandedImage);
    }

    public void channgeExpandedImage(String img){
        Glide.with(this).load(img).into(expandedImage);
    }

    public void showBackButton(boolean show){
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(show);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    public void transparantTitle(boolean transparant){
        toolbarLayout.setExpandedTitleColor(transparant ? Color.TRANSPARENT : Color.WHITE);
    }
}
