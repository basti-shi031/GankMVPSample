package com.basti.gankmvp;

import android.app.Application;

import com.github.moduth.blockcanary.BlockCanary;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by SHIBW-PC on 2016/2/1.
 */
public class BastiApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
        BlockCanary.install(this, new AppBlockCanaryContext()).start();
    }
}
