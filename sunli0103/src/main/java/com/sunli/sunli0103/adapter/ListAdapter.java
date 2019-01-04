package com.sunli.sunli0103.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.sunli.sunli0103.R;
import com.sunli.sunli0103.bean.ListBean;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @Author sunli
 * @Data 2019/1/3
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {

    private Context mContext;
    private List<ListBean.DataBean> list;

    public ListAdapter(Context mContext) {
        this.mContext = mContext;
        list = new ArrayList<>();
    }

    public void setList(List<ListBean.DataBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(mContext, R.layout.adapter_list_item, null);
        ListViewHolder listViewHolder = new ListViewHolder(view);
        return listViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder listViewHolder, int i) {
        Pattern compile = Pattern.compile("\\|");
        String[] split = compile.split(list.get(i).getImages().replace("https", "http"));
        Uri uri = Uri.parse(split[0]);
        AbstractDraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setAutoPlayAnimations(true)
                .build();
        listViewHolder.icon.setController(controller);
        listViewHolder.title.setText(list.get(i).getTitle());
        listViewHolder.price.setText("原价" + list.get(i).getPrice());
        listViewHolder.barginprice.setText("优惠价" + list.get(i).getBargainPrice());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView icon;
        TextView title, price, barginprice;
        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.adapter_item_icon);
            title = itemView.findViewById(R.id.atapter_item_text_title);
            price = itemView.findViewById(R.id.atapter_item_text_price);
            barginprice = itemView.findViewById(R.id.atapter_item_text_barginprice);
        }
    }
}
