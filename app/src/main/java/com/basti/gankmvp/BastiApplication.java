package com.basti.gankmvp;

import android.app.Application;
import android.content.Context;

import com.github.moduth.blockcanary.BlockCanary;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by SHIBW-PC on 2016/2/1.
 */
public class BastiApplication extends Application {
    public static RefWatcher getRefWatcher(Context context) {
        BastiApplication application = (BastiApplication) context.getApplicationContext();
        return application.refWatcher;
    }

    private RefWatcher refWatcher;
    @Override
    public void onCreate() {
        super.onCreate();
        refWatcher = LeakCanary.install(this);
        BlockCanary.install(this, new AppBlockCanaryContext()).start();
    }
}
