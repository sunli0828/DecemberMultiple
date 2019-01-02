package mlb.bawei.com.synthesize.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author
 * @date 2018/12/28
 */
public class RetrofitManager {
    //01
    private static RetrofitManager retrofitManager;
    public static synchronized RetrofitManager getRetrofitManager(){

        if(retrofitManager==null){
            retrofitManager = new RetrofitManager();
        }
        return retrofitManager;
    }
    private String BaseUrl="http://172.17.8.100/small/";
    private BaseAPIs baseAPIs;
    //02
    public RetrofitManager(){
        //拦截器
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .writeTimeout(10,TimeUnit.SECONDS)
                .connectTimeout(10,TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .retryOnConnectionFailure(true);

        OkHttpClient mClient = builder.build();

        //
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BaseUrl)
                .client(mClient)
                .build();
        baseAPIs = retrofit.create(BaseAPIs.class);
    }

    //创建一个方法 返回值为string body
    public Map<String, RequestBody> generateRequestBody(Map<String, String> params){
        Map<String, RequestBody> requestBody = new HashMap<>();
        for (String key:params.keySet()){
            RequestBody body = RequestBody.create(MediaType.parse("multipart/form-data"),
                    params.get(key)==null? "":params.get(key));
            requestBody.put(key,body);
        }
        return requestBody;
    }
    private Observer observer = new Observer<ResponseBody>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(ResponseBody o) {
            try {
                String string = o.string();
                if(httpCallBack!=null){
                    httpCallBack.setData(string);
                }
            } catch (IOException e) {
                e.printStackTrace();
                httpCallBack.setFail(e.getMessage());
            }
        }
    };
    public RetrofitManager postFormBody(String url,Map<String,RequestBody> params){
        if(params==null){
            params = new HashMap<>();

        }
        baseAPIs.postFormBody(url,params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

        return retrofitManager;
    }

    public interface HttpCallBack{
        void setData(String data);
        void setFail(String msg);
    }

    private HttpCallBack httpCallBack;

    public void setHttpCallBack(HttpCallBack httpCallBack) {
        this.httpCallBack = httpCallBack;
    }
}
