package com.cloud;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.cloud.util.Logger;

public class BaseApplication extends Application {

    private static BaseApplication mBaseApplication;

    protected boolean isAppActive = false;

    public static BaseApplication getApplication() {
        return mBaseApplication;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        mBaseApplication = this;

    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivity != null) {
            NetworkInfo[] infos = connectivity.getAllNetworkInfo();

            for (NetworkInfo info : infos) {
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        } else {
            Logger.d("isNetworkAvailable: connectivity is null");
            return false;
        }

        Logger.d("isNetworkAvailable: none network connected");
        return false;
    }

    public void OnAppInBackground() {
        isAppActive = false;
    }

    public void OnAppInForeground() {
        isAppActive = true;
    }
}
