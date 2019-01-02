package com.sunli.decembermultiple.commenpage.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.sunli.decembermultiple.R;
import com.sunli.decembermultiple.commenpage.home.classify_menu.adapter.FirstCategoryAdapter;
import com.sunli.decembermultiple.commenpage.home.classify_menu.adapter.SecondCategoryAdapter;
import com.sunli.decembermultiple.commenpage.home.classify_menu.bean.FirstCategoryBean;
import com.sunli.decembermultiple.shell_frame.mvp.presenter.IPresenterImpl;
import com.sunli.decembermultiple.shell_frame.mvp.view.IView;
import com.sunli.decembermultiple.shell_frame.network.ApiUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HomeFragment extends Fragment implements IView {

    private RecyclerView recyclerView, recyclerView_item;
    private FirstCategoryAdapter adapter;
    private Unbinder bind;
    private boolean flag;
    private IPresenterImpl iPresenter;
    private List<FirstCategoryBean.ResultBean> list;
    private String name;
    private String id;
    private boolean isFirst;
    private SecondCategoryAdapter secondCategoryAdapter;

    private LinearLayout fol_lnlay;
    private ViewPager fow_viewpager;
    int[] picArray;
    ImageView[] ivArray;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_home, null);
        recyclerView = view.findViewById(R.id.fragment_home_classify_recyclerview);
        recyclerView_item = view.findViewById(R.id.fragment_home_classify_recyclerview_item);

        bind = ButterKnife.bind(getActivity());
        iPresenter = new IPresenterImpl(this);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        list = new ArrayList<>();
        adapter = new FirstCategoryAdapter(getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter.setListener(new FirstCategoryAdapter.ParentCallbackListener() {
            @Override
            public void parentCallbackListener(final String i) {
                iPresenter.startRequestGet(String.format(ApiUtils.GET_URL_SECOND_CATEGORY,i),null,FirstCategoryBean.class);
                isFirst = false;
            }
        });

        iPresenter.startRequestGet(ApiUtils.GET_URL_FIRST_CATEGORY, null, FirstCategoryBean.class);
        isFirst = true;

        recyclerView.setAdapter(adapter);

        //第二层
        secondCategoryAdapter = new SecondCategoryAdapter(getContext());
        LinearLayoutManager LayoutManager = new LinearLayoutManager(getActivity());
        LayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView_item.setLayoutManager(LayoutManager);
        recyclerView_item.setAdapter(secondCategoryAdapter);


        flag = false;
        view.findViewById(R.id.fragment_home_common_btn_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (flag) {
                    recyclerView.setVisibility(View.GONE);
                    recyclerView_item.setVisibility(View.GONE);
                }else {
                    recyclerView.setVisibility(View.VISIBLE);
                    recyclerView_item.setVisibility(View.VISIBLE);
                }
                flag = !flag;
            }
        });

    }

    @Override
    public void showResponseData(Object data) {
        FirstCategoryBean bean = (FirstCategoryBean) data;
        List<FirstCategoryBean.ResultBean> result = ((FirstCategoryBean) data).getResult();
        if (result != null && isFirst) {
            adapter.setList(result);
        }
        if (result != null && !isFirst) {
            secondCategoryAdapter.setResultBeanList(result);
        }
    }

    @Override
    public void showResponseFail(String e) {
        Toast.makeText(getActivity(), "fail", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bind.unbind();
        iPresenter.onDetach();
    }
}