package okki.setyatmoko.guidepess.Sub.lvl1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

import okki.setyatmoko.guidepess.BaseFragment;
import okki.setyatmoko.guidepess.Models.ItemDetails;
import okki.setyatmoko.guidepess.R;
import okki.setyatmoko.guidepess.Utils.HtmlParse;
import okki.setyatmoko.guidepess.Utils.TypeUtils;

/**
 * Created by Hinata on 8/11/2017.
 */

public class FragmentItemDetails extends BaseFragment {
    private String title = "";
    ItemDetails itemDetails;

//    TextView txtContent;
    WebView webView;

    public static FragmentItemDetails newInstance(String title, ItemDetails itemDetails) {
        FragmentItemDetails fragment = new FragmentItemDetails();
        fragment.title = title;
        fragment.itemDetails = itemDetails;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sub_type_1, container, false);
        webView = (WebView) v.findViewById(R.id.web);
        if (itemDetails.getContent().getType().equals(TypeUtils.HTML_FILE)){
            webView.loadUrl(HtmlParse.parse(itemDetails.getContent().getContentName()));
        }
        return v;
    }

    @Override
    public int getExpandedImage() {
        int d = getActivity().getResources().getIdentifier(itemDetails.getImageCover(), "drawable", getActivity().getPackageName());
        return d;
    }

    @Override
    public boolean showBackButton() {
        return true;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public boolean transparantTitle() {
        return false;
    }
}
