package com.sunli.decembermultiple.shell_frame.mvp.model;

import android.util.Log;

import com.google.gson.Gson;
import com.sunli.decembermultiple.shell_frame.mvp.callback.ICallBack;
import com.sunli.decembermultiple.shell_frame.network.RetrofitManager;

import java.util.Map;

import okhttp3.RequestBody;

public class IModelImpl<T> implements IModel {
    @Override
    public void getResponseDataPost(String urlStr, Map<String, String> params, final Class clazz, final ICallBack iCallBack) {
        Map<String, RequestBody> map = RetrofitManager.getInstance().generateRequestBody(params);
        RetrofitManager.getInstance().postFormBody(urlStr, map, new RetrofitManager.HttpListener() {
            @Override
            public void onSuccess(String data) {
                try {
                    Object o = new Gson().fromJson(data, clazz);
                    if (iCallBack != null) {
                        iCallBack.setDataSuccess(o);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (iCallBack != null) {
                        iCallBack.setDataFail(e.getMessage());
                    }
                }

            }

            @Override
            public void onFail(String error) {
                if (iCallBack != null) {
                    iCallBack.setDataFail(error);
                }
            }
        });
    }

    @Override
    public void getResponseDataGet(String urlStr, String params, final Class clazz, final ICallBack iCallBack) {
        RetrofitManager.getInstance().get(urlStr, new RetrofitManager.HttpListener() {
            @Override
            public void onSuccess(String data) {
                try {
                    Object o = new Gson().fromJson(data, clazz);
                    if (iCallBack != null) {
                        iCallBack.setDataSuccess(o);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (iCallBack != null) {
                        iCallBack.setDataFail(e.getMessage());
                        Log.i("AS",e.getMessage());
                    }
                }
            }

            @Override
            public void onFail(String error) {
                if (iCallBack != null) {
                    iCallBack.setDataFail(error);
                }
            }
        });

    }
}
