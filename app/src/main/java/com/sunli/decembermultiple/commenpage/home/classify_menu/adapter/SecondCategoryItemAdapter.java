package com.sunli.decembermultiple.commenpage.home.classify_menu.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sunli.decembermultiple.R;
import com.sunli.decembermultiple.commenpage.home.classify_menu.bean.SecondCategoryItemBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author sunli
 * @Data 2019/1/4
 */
public class SecondCategoryItemAdapter extends RecyclerView.Adapter<SecondCategoryItemAdapter.SecondCategoryItemViewHolder> {
    private List<SecondCategoryItemBean.ResultBean> resultBeanList;
    private Context resultContext;

    public SecondCategoryItemAdapter(Context resultContext) {
        this.resultContext = resultContext;
        resultBeanList = new ArrayList<>();
    }

    public void setResultBeanList(List<SecondCategoryItemBean.ResultBean> resultBeanList) {
        this.resultBeanList = resultBeanList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SecondCategoryItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(resultContext, R.layout.adapter_second_category_item_item, null);
        SecondCategoryItemViewHolder secondCategoryItemViewHolder = new SecondCategoryItemViewHolder(view);
        return secondCategoryItemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SecondCategoryItemViewHolder secondCategoryItemViewHolder, int i) {
        Glide.with(resultContext).load(resultBeanList.get(i).getMasterPic()).into(secondCategoryItemViewHolder.icon);
        secondCategoryItemViewHolder.text_name.setText(resultBeanList.get(i).getCommodityName());
        secondCategoryItemViewHolder.text_price.setText("¥" + resultBeanList.get(i).getPrice());
        secondCategoryItemViewHolder.text_saleNum.setText(resultBeanList.get(i).getSaleNum() + "");
    }

    @Override
    public int getItemCount() {
        return resultBeanList.size();
    }

    public class SecondCategoryItemViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView text_name, text_price, text_saleNum;
        public SecondCategoryItemViewHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.adapter_second_category_item_icon);
            text_name = itemView.findViewById(R.id.adapter_second_category_item_text_name);
            text_price = itemView.findViewById(R.id.adapter_second_category_item_text_price);
            text_saleNum = itemView.findViewById(R.id.adapter_second_category_item_text_saleNum);
        }
    }
}
