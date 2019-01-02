package com.sunli.decembermultiple.commenpage.home.classify_menu.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sunli.decembermultiple.R;
import com.sunli.decembermultiple.commenpage.home.classify_menu.bean.FirstCategoryBean;

import java.util.ArrayList;
import java.util.List;

public class SecondCategoryAdapter extends RecyclerView.Adapter<SecondCategoryAdapter.SecondViewHolder> {

    private List<FirstCategoryBean.ResultBean> resultBeanList;
    private Context resultContext;

    public SecondCategoryAdapter(Context resultContext) {
        resultBeanList = new ArrayList<>();
        this.resultContext = resultContext;
    }

    public void setResultBeanList(List<FirstCategoryBean.ResultBean> resultBeanList) {
        this.resultBeanList = resultBeanList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SecondViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(resultContext, R.layout.adapter_secondcategory_item, null);
        SecondViewHolder secondViewHolder = new SecondViewHolder(view);
        return secondViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SecondViewHolder secondViewHolder, final int i) {
        secondViewHolder.textView.setText(resultBeanList.get(i).getName());
        secondViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (carCallBackListener != null) {
                    carCallBackListener.callBack(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return resultBeanList.size();
    }

    class SecondViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public SecondViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.adapter_item_second_text);
        }
    }
    private CarCallBackListener carCallBackListener;

    public void setListener(CarCallBackListener listener) {
        this.carCallBackListener = listener;
    }

    public interface CarCallBackListener {
        void callBack(int i);
    }
}

