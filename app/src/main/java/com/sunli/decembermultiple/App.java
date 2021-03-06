package com.sunli.decembermultiple;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

import java.io.File;

/**
 * @Author sunli
 * @Data 2019/1/2
 */
public class App extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        /*File sd = Environment.getExternalStorageDirectory();
        String mPath = sd.getPath() + "/image";
        File file = new File(mPath);
        if (!file.exists()) {
            file.mkdir();
        }
        Fresco.initialize(this,ImagePipelineConfig.newBuilder(App.this)
                .setMainDiskCacheConfig(
                        DiskCacheConfig.newBuilder(this)
                                .setBaseDirectoryPath(file)
                                .build()
                )
                .build()
        );*/
        Fresco.initialize(this);
        mContext = getApplicationContext();
    }

    public static Context getApplication() {
        return mContext;
    }
    private long exitTime = 0;
}