package com.sunli.sunli0103.mvp.callback;

/**
 * @Author sunli
 * @Data 2019/1/3
 */
public interface ICallback<T> {
    void setDataSuccess(T data);
    void setDataFail(String e);
}
