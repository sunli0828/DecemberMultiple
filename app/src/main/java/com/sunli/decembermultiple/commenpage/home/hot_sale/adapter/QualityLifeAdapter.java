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
public class QualityLifeAdapter extends RecyclerView.Adapter<QualityLifeAdapter.QualityLifeViewHolder> {

    private Context pzshContext;
    private List<HotSaleBean.ResultBean.PzshBean.CommodityListBeanX> pzshBeanList;

    public QualityLifeAdapter(Context pzshContext) {
        this.pzshContext = pzshContext;
        pzshBeanList = new ArrayList<>();
    }

    public void setPzshBeanList(List<HotSaleBean.ResultBean.PzshBean.CommodityListBeanX> pzshBeanList) {
        this.pzshBeanList = pzshBeanList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public QualityLifeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(pzshContext, R.layout.adapter_qualitylife_item, null);
        QualityLifeViewHolder qualityLifeViewHolder = new QualityLifeViewHolder(view);
        return qualityLifeViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull QualityLifeViewHolder qualityLifeViewHolder, int i) {
        Glide.with(pzshContext).load(pzshBeanList.get(i).getMasterPic()).into(qualityLifeViewHolder.icon);
        qualityLifeViewHolder.text_name.setText(pzshBeanList.get(i).getCommodityName());
        qualityLifeViewHolder.text_price.setText("¥" + pzshBeanList.get(i).getPrice());
    }

    @Override
    public int getItemCount() {
        return pzshBeanList.size();
    }

    public class QualityLifeViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView text_name, text_price;
        public QualityLifeViewHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.adapter_qualitylife_item_icon);
            text_name = itemView.findViewById(R.id.adapter_qualitylife_item_text_name);
            text_price = itemView.findViewById(R.id.adapter_qualitylife_item_text_price);
        }
    }
}
