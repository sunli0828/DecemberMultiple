package com.sunli.sunli0103.mvp.presenter;

import com.sunli.sunli0103.mvp.callback.ICallback;
import com.sunli.sunli0103.mvp.model.IModelImpl;
import com.sunli.sunli0103.mvp.view.IView;

import java.util.Map;

/**
 * @Author sunli
 * @Data 2019/1/3
 */
public class IPresenterImpl implements IPresenter {

    private IView iView;
    private IModelImpl iModel;

    public IPresenterImpl(IView iView) {
        this.iView = iView;
        iModel = new IModelImpl();
    }

    @Override
    public void startRequestGet(String urlStr, String params, Class clazz) {
        iModel.getResponseDataGet(urlStr, params, clazz, new ICallback() {
            @Override
            public void setDataSuccess(Object data) {
                iView.showResponseDataSuccess(data);
            }

            @Override
            public void setDataFail(String e) {
                iView.showResponseDataFail(e);
            }
        });
    }

    @Override
    public void startRequestPost(String urlStr, Map<String, String> params, Class clazz) {
        iModel.getResponseDataPost(urlStr, params, clazz, new ICallback() {
            @Override
            public void setDataSuccess(Object data) {
                iView.showResponseDataSuccess(data);
            }

            @Override
            public void setDataFail(String e) {
                iView.showResponseDataFail(e);
            }
        });
    }

    public void onDetach() {
        if (iModel != null) {
            iModel = null;
        }
        if (iView != null) {
            iView = null;
        }
    }
}
