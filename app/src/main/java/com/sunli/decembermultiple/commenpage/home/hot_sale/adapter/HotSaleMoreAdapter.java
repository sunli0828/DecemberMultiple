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
import com.sunli.decembermultiple.commenpage.home.hot_sale.bean.HotSaleMoreBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author sunli
 * @Data 2019/1/3
 */
public class HotSaleMoreAdapter extends RecyclerView.Adapter<HotSaleMoreAdapter.HotSaleMoreViewHolder> {

    private Context resultBeanListContext;
    private List<HotSaleMoreBean.ResultBean> resultBeanList;

    public HotSaleMoreAdapter(Context resultBeanListContext) {
        this.resultBeanListContext = resultBeanListContext;
        resultBeanList = new ArrayList<>();
    }

    public List<HotSaleMoreBean.ResultBean> getResultBeanList() {
        return resultBeanList;
    }

    public void setData(List<HotSaleMoreBean.ResultBean> list) {
        if (list != null) {
            resultBeanList = list;
        }
        notifyDataSetChanged();
    }

    public void addData(List<HotSaleMoreBean.ResultBean> list) {
        if (list != null) {
            list.addAll(list);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HotSaleMoreViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(resultBeanListContext, R.layout.adapter_hotsale_more_item, null);
        HotSaleMoreViewHolder hotSaleMoreViewHolder = new HotSaleMoreViewHolder(view);
        return hotSaleMoreViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HotSaleMoreViewHolder hotSaleMoreViewHolder, int i) {
        Glide.with(resultBeanListContext).load(resultBeanList.get(i).getMasterPic()).into(hotSaleMoreViewHolder.icon);
        hotSaleMoreViewHolder.text_name.setText(resultBeanList.get(i).getCommodityName());
        hotSaleMoreViewHolder.text_price.setText("¥" + resultBeanList.get(i).getPrice());
    }

    @Override
    public int getItemCount() {
        return resultBeanList.size();
    }

    public class HotSaleMoreViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView text_name, text_price;
        public HotSaleMoreViewHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.adapter_hotsale_more_item_icon);
            text_name = itemView.findViewById(R.id.adapter_hotsale_more_item_text_name);
            text_price = itemView.findViewById(R.id.adapter_hotsale_more_item_text_price);
        }
    }
}
