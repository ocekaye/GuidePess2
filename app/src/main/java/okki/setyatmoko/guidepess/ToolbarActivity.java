package okki.setyatmoko.guidepess;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import okki.setyatmoko.guidepess.Home.HomeFragmentList;
import okki.setyatmoko.guidepess.Home.ViewPagerAdapter;
import okki.setyatmoko.guidepess.Models.ContenData;
import okki.setyatmoko.guidepess.Models.HomeItems;
import okki.setyatmoko.guidepess.Models.HomeStyle;
import okki.setyatmoko.guidepess.Models.Music;
import okki.setyatmoko.guidepess.Models.MusicModel;
import okki.setyatmoko.guidepess.Utils.ContentsName;
import okki.setyatmoko.guidepess.Utils.JsonUtil;
import okki.setyatmoko.guidepess.Utils.ParsingContentsModel;
import okki.setyatmoko.guidepess.Utils.TypeUtils;

public class ToolbarActivity extends BaseActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private AppBarLayout appBarLayout;
    HomeStyle homeStyle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);
        homeStyle = JsonUtil.getHomeStyle(this);
        appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
//        appBarLayout.setPadding(0, getStatusBarHeight(), 0, 0);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#00ff00"));
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        for (HomeStyle.SlideData slideData : homeStyle.getSlideData()){
            switch (slideData.getContent().getType()){
                case TypeUtils.LIST_ONE_LINE_COVER:
                    HomeItems homeItems = new ParsingContentsModel<HomeItems>(slideData.getContent().getContentName()).parse(this);
                    adapter.addFragment(HomeFragmentList.newInstance(slideData.getTitle(), TypeUtils.STYLE_TAB, slideData.getContent()), slideData.getTitle());
                    break;
                case TypeUtils.LIST_3_LINE_COVER:
                    adapter.addFragment(HomeFragmentList.newInstance(slideData.getTitle(), TypeUtils.STYLE_TAB, slideData.getContent()), slideData.getTitle());
                    break;
            }
        }
//        adapter.addFragment(HomeFragmentList.newInstance("One", TypeUtils.STYLE_TAB), "ONE");
//        adapter.addFragment(HomeFragmentList.newInstance("One", TypeUtils.STYLE_TAB), "TWO");
        viewPager.setAdapter(adapter);
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void changeFragment(Fragment fragment, ContenData contenData, String title){
        switch (contenData.getType()){
            case TypeUtils.TYPE_DETAIL_HEADER_IMAGE:
                Intent intent = new Intent(ToolbarActivity.this, CollapsingActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("contentData", contenData);
                bundle.putString("title", title);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case TypeUtils.ACTION_PLAY_MUSIC:

                break;
        }
       /* getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, fragment).addToBackStack(null).commit();
        appBarLayout.setExpanded(true, true);*/
    }

    public void changeFragmentMusicAction(ContenData contenData, Music music, String title){
        Intent intent = new Intent(ToolbarActivity.this, CollapsingActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("contentData", contenData);
        bundle.putParcelable("music", music);
        bundle.putString("title", title);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}
