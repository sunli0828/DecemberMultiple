package com.sunli.decembermultiple.shell_frame.network;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RetrofitManager<T> {
    private final String BASE_URL = "http://mobile.bwstudent.com/small/";

    private static RetrofitManager retrofitManager;
    private BaseApis baseApis;

    public static synchronized RetrofitManager getInstance(){
        if (retrofitManager == null) {
            retrofitManager = new RetrofitManager();
        }
        return retrofitManager;
    }

    public RetrofitManager() {
        /*HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY); */

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(15, TimeUnit.SECONDS);
        builder.readTimeout(15, TimeUnit.SECONDS);
        builder.writeTimeout(15, TimeUnit.SECONDS);
        builder.addInterceptor(new CustomIntercept());
        builder.retryOnConnectionFailure(true);
        OkHttpClient okClient = builder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .client(okClient)
                .build();
        baseApis = retrofit.create(BaseApis.class);
    }

    public Map<String, RequestBody> generateRequestBody(Map<String, String> requestDataMap) {
        HashMap<String, RequestBody> requestBodyMap = new HashMap<>();
        for (String key : requestDataMap.keySet()) {
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"),
                    requestDataMap.get(key) == null ? "" : requestDataMap.get(key));
            requestBodyMap.put(key, requestBody);
        }
        return requestBodyMap;
    }

    /**
     * get请求
     */
    public RetrofitManager get(String url, HttpListener listener) {
        baseApis.get(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver(listener));
        return retrofitManager;
    }

    private Observer getObserver(final HttpListener listener) {
         Observer observer = new Observer<ResponseBody>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                if (listener != null) {
                    listener.onFail(e.getMessage());
                }
            }

            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    String data = responseBody.string();
                    if (listener != null) {
                        listener.onSuccess(data);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    if (listener != null) {
                        listener.onFail(e.getMessage());
                    }
                }
            }
        };
        return observer;
    }

    /**
     * 表单post请求
     * @param url
     * @param map
     * @return
     */
    public RetrofitManager postFormBody(String url, Map<String, RequestBody> map,HttpListener listener) {
        if (map == null) {
            map = new HashMap<>();
        }

        baseApis.postFormBody(url, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver(listener));
        return retrofitManager;
    }

    /**
     * 普通post请求
     */
    public RetrofitManager post(String url, Map<String, String> map,HttpListener listener) {
        if (map == null) {
            map = new HashMap<>();
        }
        baseApis.post(url, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver(listener));

        return retrofitManager;
    }

    public interface HttpListener {
        void onSuccess(String data);

        void onFail(String error);
    }
}
