package com.sunli.sunli0103;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sunli.sunli0103.adapter.ListAdapter;
import com.sunli.sunli0103.bean.ListBean;
import com.sunli.sunli0103.mvp.presenter.IPresenterImpl;
import com.sunli.sunli0103.mvp.view.IView;
import com.sunli.sunli0103.network.ApiUtils;

import java.util.List;

public class MainActivity extends AppCompatActivity implements IView {

    private IPresenterImpl iPresenter;
    private RecyclerView recyclerView;
    private ListAdapter listAdapter;
    private List<ListBean.DataBean> listBeanData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iPresenter = new IPresenterImpl(this);
        initView();
    }

    private void initView() {
        recyclerView = findViewById(R.id.activity_main_recyclerView);

        listAdapter = new ListAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        iPresenter.startRequestGet(String.format(ApiUtils.TYPE_GET_LIST_URL, 1), null, ListBean.class);
        recyclerView.setAdapter(listAdapter);
    }

    @Override
    public void showResponseDataSuccess(Object data) {
        ListBean listBean = (ListBean) data;
        List<ListBean.DataBean> listBeanData = listBean.getData();
        if (listBeanData != null) {
            listAdapter.setList(listBeanData);
        }
    }

    @Override
    public void showResponseDataFail(String e) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iPresenter.onDetach();
    }
}
