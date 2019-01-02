package mlb.bawei.com.synthesize.mvpdome.model;

import com.google.gson.Gson;

import java.util.Map;

import mlb.bawei.com.synthesize.callback.MCallBack;
import mlb.bawei.com.synthesize.utils.RetrofitManager;
import okhttp3.RequestBody;

/**
 * @author
 * @date 2018/12/28
 */
public class Modellmpl implements IModel{

    @Override
    public void startRequest(String url, final Class cla, Map<String, String> params, final MCallBack mCallBack) {
        Map<String, RequestBody> requestBodyMap = RetrofitManager.getRetrofitManager().generateRequestBody(params);
        RetrofitManager.getRetrofitManager().postFormBody(url,requestBodyMap).setHttpCallBack(new RetrofitManager.HttpCallBack() {
            @Override
            public void setData(String data) {
                try {
                    Object o = new Gson().fromJson(data, cla);
                    mCallBack.setData(o);
                }catch (Exception e){
                    mCallBack.setFail(e.getMessage());
                }
            }

            @Override
            public void setFail(String msg) {
                mCallBack.setFail(msg);
            }
        });
    }
}
