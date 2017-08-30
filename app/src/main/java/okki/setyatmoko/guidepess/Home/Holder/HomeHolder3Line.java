package okki.setyatmoko.guidepess.Home.Holder;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import okki.setyatmoko.guidepess.R;

/**
 * Created by Hinata on 8/11/2017.
 */
public class HomeHolder3Line extends RecyclerView.ViewHolder {
    public ImageView img;
    public TextView txtTitle, txtSub1, txtSub2;
    public View item;
    public HomeHolder3Line(View itemView) {
        super(itemView);
        item = itemView;
        img = (ImageView) itemView.findViewById(R.id.img_icon);
        txtTitle = (TextView) itemView.findViewById(R.id.txt_title);
        txtSub1 = (TextView) itemView.findViewById(R.id.txt_sub_title);
        txtSub2 = (TextView) itemView.findViewById(R.id.txt_desc);

    }
    public HomeHolder3Line(ViewGroup parent){
        this(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_3_line, parent, false));
    }

}
