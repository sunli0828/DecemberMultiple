package com.sunli.decembermultiple.shell_frame.mvp.callback;

public interface ICallBack<T> {
    void setDataSuccess(T data);
    void setDataFail(String e);
}
