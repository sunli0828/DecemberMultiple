package com.sunli.sunli0103;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * @Author sunli
 * @Data 2019/1/3
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
