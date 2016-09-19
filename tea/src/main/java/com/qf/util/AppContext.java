package com.qf.util;

import android.app.Application;

/**
 * Created by ${WU} on 2016/9/13.
 */
public class AppContext extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SharedUtil.init(this);
    }
}
