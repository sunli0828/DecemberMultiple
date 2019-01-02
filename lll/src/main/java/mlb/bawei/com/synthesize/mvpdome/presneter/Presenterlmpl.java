package mlb.bawei.com.synthesize.mvpdome.presneter;

import java.util.Map;

import mlb.bawei.com.synthesize.callback.MCallBack;
import mlb.bawei.com.synthesize.mvpdome.model.Modellmpl;
import mlb.bawei.com.synthesize.mvpdome.view.IView;

/**
 * @author
 * @date 2018/12/28
 */
public class Presenterlmpl implements IPresenter {

    IView iView;
    Modellmpl modellmpl;

    public Presenterlmpl(IView iView) {
        this.iView = iView;
        modellmpl = new Modellmpl();
    }

    public void onDatech(){
        if(modellmpl!=null){
            modellmpl=null;
        }
        if(iView==null){
            iView=null;
        }
    }

    @Override
    public void goToRequest(String url, Class clas, Map<String, String> params) {

        modellmpl.startRequest(url, clas, params, new MCallBack() {
            @Override
            public void setData(Object data) {
                iView.setData(data);
            }

            @Override
            public void setFail(String msg) {
                iView.setFail(msg);
            }
        });
    }
}
