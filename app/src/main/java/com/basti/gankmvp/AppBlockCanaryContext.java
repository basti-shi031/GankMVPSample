package com.basti.gankmvp;

import com.github.moduth.blockcanary.BlockCanaryContext;

/**
 * Created by SHIBW-PC on 2016/2/1.
 */
public class AppBlockCanaryContext extends BlockCanaryContext {

    @Override
    public int getConfigBlockThreshold() {
        return 500;
    }

    // if set true, notification will be shown, else only write log file
    @Override
    public boolean isNeedDisplay() {
        return BuildConfig.DEBUG;
    }

    // path to save log file
    @Override
    public String getLogPath() {
        return "/blockcanary/performance";
    }

}
