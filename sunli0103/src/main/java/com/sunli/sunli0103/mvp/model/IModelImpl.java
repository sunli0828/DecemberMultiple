package com.sunli.sunli0103.mvp.model;

import com.google.gson.Gson;
import com.sunli.sunli0103.mvp.callback.ICallback;
import com.sunli.sunli0103.network.RetrofitManager;

import java.util.Map;

import okhttp3.RequestBody;

/**
 * @Author sunli
 * @Data 2019/1/3
 */
public class IModelImpl implements IModel {
    @Override
    public void getResponseDataGet(String urlStr, String params, final Class clazz, final ICallback iCallback) {
        RetrofitManager.getInstance().get(urlStr, new RetrofitManager.HttpListener() {
            @Override
            public void onSuccess(String data) {
                try {
                    Object o = new Gson().fromJson(data, clazz);
                    if (iCallback != null) {
                        iCallback.setDataSuccess(o);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (iCallback != null) {
                        iCallback.setDataFail(e.getMessage());
                    }
                }
            }

            @Override
            public void onFail(String error) {
                if (iCallback != null) {
                    iCallback.setDataFail(error);
                }
            }
        });
    }

    @Override
    public void getResponseDataPost(String urlStr, Map<String, String> params, final Class clazz, final ICallback iCallback) {
        Map<String, RequestBody> map = RetrofitManager.getInstance().generateRequestBody(params);
        RetrofitManager.getInstance().postFormBody(urlStr, map, new RetrofitManager.HttpListener() {
            @Override
            public void onSuccess(String data) {
                try {
                    Object o = new Gson().fromJson(data, clazz);
                    if (iCallback != null) {
                        iCallback.setDataSuccess(o);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (iCallback != null) {
                        iCallback.setDataFail(e.getMessage());
                    }
                }

            }

            @Override
            public void onFail(String error) {
                if (iCallback != null) {
                    iCallback.setDataFail(error);
                }
            }
        });
    }
}
