package com.sunli.decembermultiple.shell_frame.mvp.model;

import com.sunli.decembermultiple.shell_frame.mvp.callback.ICallBack;

import java.util.Map;

public interface IModel {
    void getResponseDataPost(String urlStr, Map<String, String> params, Class clazz, ICallBack iCallBack);
    void getResponseDataGet(String urlStr, String params, Class clazz, ICallBack iCallBack);
}
