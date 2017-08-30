package okki.setyatmoko.guidepess.Home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import okki.setyatmoko.guidepess.BaseActivity;
import okki.setyatmoko.guidepess.BaseFragment;
import okki.setyatmoko.guidepess.Home.Adapter.HomeAdapter;
import okki.setyatmoko.guidepess.Models.BaseModelContent;
import okki.setyatmoko.guidepess.Models.ContenData;
import okki.setyatmoko.guidepess.Models.HomeItems;
import okki.setyatmoko.guidepess.Models.HomeStyle;
import okki.setyatmoko.guidepess.Models.MusicModel;
import okki.setyatmoko.guidepess.R;
import okki.setyatmoko.guidepess.Utils.ContentsName;
import okki.setyatmoko.guidepess.Utils.JsonUtil;
import okki.setyatmoko.guidepess.Utils.ParsingContentsModel;
import okki.setyatmoko.guidepess.Utils.TypeUtils;
import okki.setyatmoko.guidepess.Utils.Utils;

/**
 * Created by Hinata on 8/10/2017.
 */
public class HomeFragmentList extends BaseFragment {
    private String title = "", type = TypeUtils.STYLE_BUTTON;
    private HomeStyle homeStyle;
    RecyclerView recyclerView;
    HomeAdapter homeAdapter;

    private ImageView imgIconButton1, imgIconButton2;
    private TextView txtButton1, txtButton2;

    ContenData contenData;

    public static HomeFragmentList newInstance(String title, String type, ContenData contenData) {
        HomeFragmentList fragment = new HomeFragmentList();
        fragment.title = title;
        fragment.type = type;
        fragment.contenData = contenData;
        Log.e("HomeFragment", "create");
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeStyle = JsonUtil.getHomeStyle(getActivity());
//        homeAdapter = new HomeAdapter(getActivity(), contenData.getType());
//        homeAdapter.add(Settings.setItemHome());
//        homeAdapter.add(homeItems.getData());
        getContent();

    }

    private void getContent(){
        switch (contenData.getType()){
            case TypeUtils.LIST_ONE_LINE_COVER:
                switch (contenData.getContentName()){
                    case ContentsName.HOME_ITEMS:
                        homeAdapter = new HomeAdapter<HomeItems.DataItems>(getActivity(), contenData.getType());
                        HomeItems homeItems = new ParsingContentsModel<HomeItems>(contenData.getContentName()).parse(getActivity());
                        homeAdapter.add(homeItems.getData());
                        break;
                }
                break;
            case TypeUtils.LIST_3_LINE_COVER:
                switch (contenData.getContentName()){
                    case ContentsName.MUSIC_LIST:
                        homeAdapter = new HomeAdapter<MusicModel>(getActivity(), contenData.getType());
                       MusicModel musicModel = new ParsingContentsModel<MusicModel>(contenData.getContentName()).parse(getActivity());
                        homeAdapter.add(musicModel.getData());

                        break;
                }
                break;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home_button, container, false);
        switch (type){
            case TypeUtils.STYLE_BUTTON:
                v = inflater.inflate(R.layout.fragment_home_button, container, false);
                imgIconButton1 = (ImageView) v.findViewById(R.id.img_icon_button_1);
                imgIconButton2 = (ImageView) v.findViewById(R.id.img_icon_button_2);
                txtButton1 = (TextView) v.findViewById(R.id.txt_button_1);
                txtButton2 = (TextView) v.findViewById(R.id.txt_button_2);

                HomeStyle.HomeButtonData b1 = homeStyle.getButonData().getButtons().get(0);
                HomeStyle.HomeButtonData b2 = homeStyle.getButonData().getButtons().get(1);
                txtButton1.setText(b1.getText());
                txtButton2.setText(b2.getText());
                int d = getActivity().getResources().getIdentifier(b1.getIcon(), "drawable", getActivity().getPackageName());
                int e = getActivity().getResources().getIdentifier(b2.getIcon(), "drawable", getActivity().getPackageName());
                imgIconButton1.setImageResource(d);
                imgIconButton2.setImageResource(e);

                // TODO: 8/12/2017  Cant use glide have no idea still cant be cast to base fragment on activity
//                Glide.with(getActivity()).load(((BaseActivity) getActivity()).getDrawableId(b1.getIcon())).into(imgIconButton1);
//                Glide.with(getActivity()).load(((BaseActivity) getActivity()).getDrawableId(b2.getIcon())).into(imgIconButton2);
                break;
            case TypeUtils.STYLE_TAB:
                v = inflater.inflate(R.layout.fragment_home, container, false);
                break;
        }
        recyclerView = (RecyclerView) v.findViewById(R.id.rv_content);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(homeAdapter);
        return v;
    }

    @Override
    public int getExpandedImage() {
        return R.drawable.bannerguide;
    }

    @Override
    public String getTitle() {
        return title;
    }
}
