package mlb.bawei.com.synthesize;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;


/**
 * @author
 * @date 2018/12/28
 */
public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
