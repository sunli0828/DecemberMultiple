package com.sunli.decembermultiple.commenpage.home.classify_menu.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sunli.decembermultiple.R;
import com.sunli.decembermultiple.commenpage.home.classify_menu.bean.FirstCategoryBean;

import java.util.ArrayList;
import java.util.List;

public class FirstCategoryAdapter extends RecyclerView.Adapter<FirstCategoryAdapter.ViewHolder> {
    private Context mContext;
    private List<FirstCategoryBean.ResultBean> list;
    private ViewHolder holder;

    public FirstCategoryAdapter(Context mContext) {
        this.mContext = mContext;
        list = new ArrayList<>();
    }

    public void setList(List<FirstCategoryBean.ResultBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(mContext, R.layout.adapter_firstcategory_item, null);
        holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        holder.textView.setText(list.get(i).getName());
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (parentCallbackListener != null) {
                    parentCallbackListener.parentCallbackListener(list.get(i).getId());
                    parentCallbackListener.parentCallbackListener(list.get(i).getName());
                }
                holder.textView.setTextColor(Color.RED);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.adapter_item_first_text);
        }
    }

    public interface ParentCallbackListener{
        void parentCallbackListener(String i);
    }

    ParentCallbackListener parentCallbackListener;

    public void setListener(ParentCallbackListener listener) {
        this.parentCallbackListener = listener;
    }
}
