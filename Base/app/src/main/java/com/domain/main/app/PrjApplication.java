package com.domain.main.app;

import android.content.Context;

import com.cloud.BaseApplication;

/**
 * Created by cloud_wang on 16/3/30.
 */
public class PrjApplication extends BaseApplication {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
    public static Context getContext() {
        return context;
    }
}
