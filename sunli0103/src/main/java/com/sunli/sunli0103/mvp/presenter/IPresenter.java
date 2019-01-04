package com.sunli.sunli0103.mvp.presenter;

import java.util.Map;

/**
 * @Author sunli
 * @Data 2019/1/3
 */
public interface IPresenter {
    void startRequestGet(String urlStr, String params, Class clazz);
    void startRequestPost(String urlStr, Map<String, String> params, Class clazz);
}
