package com.sunli.decembermultiple.commenpage.home.hot_sale.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.sunli.decembermultiple.R;
import com.sunli.decembermultiple.commenpage.home.hot_sale.bean.HotSaleBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author sunli
 * @Data 2019/1/2
 */
public class HotSaleAdapter extends RecyclerView.Adapter<HotSaleAdapter.HotSaleViewHolder> {
    private List<HotSaleBean.ResultBean.RxxpBean> rxxpBeanList;
    private Context rxxpContext;
    private HotSaleViewHolder hotSaleViewHolder;

    public HotSaleAdapter(Context rxxpContext) {
        this.rxxpContext = rxxpContext;
        rxxpBeanList = new ArrayList<>();
    }

    @NonNull
    @Override
    public HotSaleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(rxxpContext, R.layout.adapter_hotsale_item, null);
        hotSaleViewHolder = new HotSaleViewHolder(view);
        return hotSaleViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HotSaleViewHolder hotSaleViewHolder, int i) {
        Uri uri = Uri.parse(rxxpBeanList.get(i).getCommodityList().get(i).getMasterPic());
        AbstractDraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setAutoPlayAnimations(true)
                .build();
       // hotSaleViewHolder.icon.setcon(controller);
    }

    @Override
    public int getItemCount() {
        return rxxpBeanList.size();
    }

    public class HotSaleViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView icon;
        TextView text_name, text_price;
        public HotSaleViewHolder(@NonNull View itemView) {
            super(itemView);

            icon = itemView.findViewById(R.id.adapter_hotsale_item_icon);
            text_name = itemView.findViewById(R.id.adapter_hotsale_item_text_name);
            text_price = itemView.findViewById(R.id.adapter_hotsale_item_text_price);
        }
    }
}
