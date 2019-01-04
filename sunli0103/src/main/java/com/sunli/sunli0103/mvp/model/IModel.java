package com.sunli.sunli0103.mvp.model;

import com.sunli.sunli0103.mvp.callback.ICallback;

import java.util.Map;

/**
 * @Author sunli
 * @Data 2019/1/3
 */
public interface IModel {
    void getResponseDataGet(String urlStr, String params, Class clazz, ICallback iCallback);
    void getResponseDataPost(String urlStr, Map<String, String> params, Class clazz, ICallback iCallback);
}
