package com.sunli.decembermultiple.shell_frame.mvp.presenter;

import java.util.Map;

public interface IPresenter {
    void startRequestPost(String urlStr, Map<String, String> params, Class clazz);
    void startRequestGet(String urlStr, String params, Class clazz);
}
