package mlb.bawei.com.synthesize.mvpdome.presneter;

import java.util.Map;

/**
 * @author
 * @date 2018/12/28
 */
public interface IPresenter {
    void goToRequest(String url, Class clas, Map<String,String> params);

}
