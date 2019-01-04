package com.sunli.decembermultiple.commenpage.home.nav_search.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sunli.decembermultiple.R;
import com.sunli.decembermultiple.commenpage.home.nav_search.bean.NavSearchBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author sunli
 * @Data 2019/1/4
 */
public class NavSearchAdapter extends RecyclerView.Adapter<NavSearchAdapter.NavSearchViewHolder> {
    private List<NavSearchBean.ResultBean> resultBeanList;
    private Context resultContext;

    public NavSearchAdapter(Context resultContext) {
        this.resultContext = resultContext;
        resultBeanList = new ArrayList<>();
    }

    public void setResultBeanList(List<NavSearchBean.ResultBean> resultBeanList) {
        this.resultBeanList = resultBeanList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NavSearchViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(resultContext, R.layout.adapter_nav_search_item, null);
        NavSearchViewHolder navSearchViewHolder = new NavSearchViewHolder(view);
        return navSearchViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NavSearchViewHolder navSearchViewHolder, int i) {
        Glide.with(resultContext).load(resultBeanList.get(i).getMasterPic()).into(navSearchViewHolder.icon);
        navSearchViewHolder.text_name.setText(resultBeanList.get(i).getCommodityName());
        navSearchViewHolder.text_price.setText("¥" + resultBeanList.get(i).getPrice());
        navSearchViewHolder.text_saleNum.setText("" + resultBeanList.get(i).getSaleNum());
    }

    @Override
    public int getItemCount() {
        return resultBeanList.size();
    }

    public class NavSearchViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView text_name, text_price, text_saleNum;
        public NavSearchViewHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.adapter_nav_search_item_icon);
            text_name = itemView.findViewById(R.id.adapter_nav_search_item_text_name);
            text_price = itemView.findViewById(R.id.adapter_nav_search_item_text_price);
            text_saleNum = itemView.findViewById(R.id.adapter_nav_search_item_text_saleNum);
        }
    }
}
