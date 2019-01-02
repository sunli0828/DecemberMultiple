package com.sunli.decembermultiple.shell_frame.mvp.presenter;

import com.sunli.decembermultiple.shell_frame.mvp.callback.ICallBack;
import com.sunli.decembermultiple.shell_frame.mvp.model.IModelImpl;
import com.sunli.decembermultiple.shell_frame.mvp.view.IView;

import java.util.Map;

public class IPresenterImpl implements IPresenter {
    private IView iView;
    private IModelImpl iModel;

    public IPresenterImpl(IView iView) {
        this.iView = iView;
        iModel = new IModelImpl();
    }

    public void onDetach() {
        if (iModel != null) {
            iModel = null;
        }
        if (iView != null) {
            iView = null;
        }
    }

    @Override
    public void startRequestPost(String urlStr, Map<String, String> params, Class clazz) {
        iModel.getResponseDataPost(urlStr, params, clazz, new ICallBack() {
            @Override
            public void setDataSuccess(Object data) {
                iView.showResponseData(data);
            }

            @Override
            public void setDataFail(String e) {
                iView.showResponseFail(e);
            }
        });
    }

    @Override
    public void startRequestGet(String urlStr, String params, Class clazz) {
        iModel.getResponseDataGet(urlStr, params, clazz, new ICallBack() {
            @Override
            public void setDataSuccess(Object data) {
                iView.showResponseData(data);
            }

            @Override
            public void setDataFail(String e) {
                iView.showResponseFail(e);
            }
        });
    }
}
