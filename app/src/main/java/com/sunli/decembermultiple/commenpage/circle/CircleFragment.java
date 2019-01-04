package com.sunli.decembermultiple.commenpage.circle;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.sunli.decembermultiple.R;
import com.sunli.decembermultiple.shell_frame.mvp.view.IView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CircleFragment extends Fragment implements IView {

    @BindView(R.id.fragment_circle_xrecyclerview)
    XRecyclerView xRecyclerView;

    private Unbinder bind;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_circle, null);

        bind = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void showResponseData(Object data) {

    }

    @Override
    public void showResponseFail(String e) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
