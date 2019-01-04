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
public class MagicFashionAdapter extends RecyclerView.Adapter<MagicFashionAdapter.MagicFashionViewHolder> {

    private Context mlssContext;
    private List<HotSaleBean.ResultBean.MlssBean.CommodityListBeanXX> mlssBeanXXList;

    public MagicFashionAdapter(Context mlssContext) {
        this.mlssContext = mlssContext;
        mlssBeanXXList = new ArrayList<>();
    }

    public void setMlssBeanXXList(List<HotSaleBean.ResultBean.MlssBean.CommodityListBeanXX> mlssBeanXXList) {
        this.mlssBeanXXList = mlssBeanXXList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MagicFashionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(mlssContext, R.layout.adapter_magicfashion_item, null);
        MagicFashionViewHolder magicFashionViewHolder = new MagicFashionViewHolder(view);
        return magicFashionViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MagicFashionViewHolder magicFashionViewHolder, int i) {
        Glide.with(mlssContext).load(mlssBeanXXList.get(i).getMasterPic()).into(magicFashionViewHolder.icon);
        magicFashionViewHolder.text_name.setText(mlssBeanXXList.get(i).getCommodityName());
        magicFashionViewHolder.text_price.setText("¥" + mlssBeanXXList.get(i).getPrice());
    }

    @Override
    public int getItemCount() {
        return mlssBeanXXList.size();
    }

    public class MagicFashionViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView text_name, text_price;
        public MagicFashionViewHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.adapter_magicfashion_item_icon);
            text_name = itemView.findViewById(R.id.adapter_magicfashion_item_text_name);
            text_price = itemView.findViewById(R.id.adapter_magicfashion_item_text_price);
        }
    }
}
