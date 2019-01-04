package com.sunli.decembermultiple.commenpage.home.hot_sale.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sunli.decembermultiple.R;
import com.sunli.decembermultiple.commenpage.home.hot_sale.bean.HotSaleBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author sunli
 * @Data 2019/1/2
 */
public class HotSaleAdapter extends RecyclerView.Adapter<HotSaleAdapter.HotSaleViewHolder> {
    private List<HotSaleBean.ResultBean.RxxpBean.CommodityListBean> rxxpBeanList;
    private Context rxxpContext;
    private HotSaleViewHolder hotSaleViewHolder;

    public HotSaleAdapter(Context rxxpContext) {
        this.rxxpContext = rxxpContext;
        rxxpBeanList = new ArrayList<>();
    }

    public void setCommodityListBeanList(List<HotSaleBean.ResultBean.RxxpBean.CommodityListBean> rxxpBeanList) {
        this.rxxpBeanList = rxxpBeanList;
        notifyDataSetChanged();
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

        Glide.with(rxxpContext).load(rxxpBeanList.get(i).getMasterPic()).into(hotSaleViewHolder.icon);
        hotSaleViewHolder.text_name.setText(rxxpBeanList.get(i).getCommodityName());
        hotSaleViewHolder.text_price.setText("¥" + rxxpBeanList.get(i).getPrice());

    }

    @Override
    public int getItemCount() {
        return rxxpBeanList.size();
    }

    public class HotSaleViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView text_name, text_price;
        public HotSaleViewHolder(@NonNull View itemView) {
            super(itemView);

            icon = itemView.findViewById(R.id.adapter_hotsale_item_icon);
            text_name = itemView.findViewById(R.id.adapter_hotsale_item_text_name);
            text_price = itemView.findViewById(R.id.adapter_hotsale_item_text_price);
        }
    }
}
