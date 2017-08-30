package okki.setyatmoko.guidepess.Home.Adapter;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.gson.Gson;

import okki.setyatmoko.guidepess.Adapters.ListAdapter;
import okki.setyatmoko.guidepess.CollapsingActivity;
import okki.setyatmoko.guidepess.Home.Holder.HomeHolder;
import okki.setyatmoko.guidepess.Home.Holder.HomeHolder3Line;
import okki.setyatmoko.guidepess.Models.BaseModelContent;
import okki.setyatmoko.guidepess.Models.HomeItems;
import okki.setyatmoko.guidepess.Models.ItemDetails;
import okki.setyatmoko.guidepess.Models.Music;
import okki.setyatmoko.guidepess.Models.MusicModel;
import okki.setyatmoko.guidepess.Sub.lvl1.FragmentItemDetails;
import okki.setyatmoko.guidepess.Utils.ParsingContentsModel;
import okki.setyatmoko.guidepess.Utils.TypeUtils;

/**
 * Created by Hinata on 8/11/2017.
 */
public class HomeAdapter<T extends BaseModelContent> extends ListAdapter<T, RecyclerView.ViewHolder> {
    Activity activity;
    String type = TypeUtils.LIST_ONE_LINE_COVER;

    public HomeAdapter(Activity activity, String type){
        this.activity = activity;
        this.type = type;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (type){
            case TypeUtils.LIST_ONE_LINE_COVER:
                return  new HomeHolder(parent);
            case TypeUtils.LIST_3_LINE_COVER:
               return new HomeHolder3Line(parent);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (type){
            case TypeUtils.LIST_ONE_LINE_COVER:
                bindListOneLine((HomeHolder) holder, position);
                break;
            case TypeUtils.LIST_3_LINE_COVER:
                bindList3Line((HomeHolder3Line) holder, position);
                break;
        }
    }

    private void bindListOneLine(HomeHolder holder, int position){
        final HomeItems.DataItems item = (HomeItems.DataItems) get(position);

        holder.txtTitle.setText(item.getTitle());
       /* int d = activity.getResources().getIdentifier(item.getImg(), "drawable", activity.getPackageName());
        holder.img.setImageDrawable(ContextCompat.getDrawable(activity, d));
*/
        int d = activity.getResources().getIdentifier(item.getImg(), "drawable", activity.getPackageName());
        Glide.with(activity).load(d).into(holder.img);
        holder.img.setBackgroundColor(Color.parseColor(item.getColor()));
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item.onCardClick(activity, item.getChildContent(), item.getTitle());
            }
        });
    }

    private void bindList3Line(HomeHolder3Line holder, int position){
        final Music music = (Music) get(position);
        holder.txtTitle.setText(music.getTitle());
        holder.txtSub1.setText(music.getSub1());
        holder.txtSub2.setText(music.getSub2());
        Glide.with(holder.img.getContext()).load(music.getImg()).into(holder.img);

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                music.PlayMusic(activity, music.getChildContent(), music, music.getTitle());
            }
        });

    }
}
