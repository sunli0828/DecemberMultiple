package mlb.bawei.com.synthesize.mvpdome.model;

import java.util.Map;

import mlb.bawei.com.synthesize.callback.MCallBack;

/**
 * @author
 * @date 2018/12/28
 */
public interface IModel {
    void startRequest(String url, Class cla, Map<String,String> params, MCallBack mCallBack);
}
