package mlb.bawei.com.synthesize.utils;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 * @author
 * @date 2018/12/28
 */
public interface BaseAPIs <T>{

    @GET
    Observable<ResponseBody> get(@Url String url);
    @Multipart
    @POST
    Observable<ResponseBody> postFormBody(@Url String url, @PartMap Map<String, RequestBody> map);
}
