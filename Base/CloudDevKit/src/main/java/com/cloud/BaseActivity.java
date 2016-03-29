package com.cloud;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.cloud.util.LoadingView;
import com.google.gson.Gson;
import com.umeng.analytics.MobclickAgent;
import com.ypy.eventbus.EventBus;

import org.androidannotations.annotations.EActivity;

import java.util.List;

@EActivity
public abstract class BaseActivity extends AppCompatActivity {
    public BaseApplication mApp;
    public Gson gson;
    private LoadingView mLoadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApp = (BaseApplication) getApplication();
        gson = mApp.getGson();
    }

    /**
     * 跳转指定Activity
     */
    public void forwardActivity(Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        startActivity(intent);
    }

    /**
     * 跳转指定Activity,带参
     */
    public void forwardActivity(Class<?> cls, String bundleName, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        intent.putExtra(bundleName, bundle);
        startActivity(intent);
    }

    /**
     * 关闭当前Activity
     */
    public void closeActivity() {
        this.finish();
    }

    @Override
    protected void onPause() {
        super.onPause();

        MobclickAgent.onPause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        mApp.OnAppInForeground();

        MobclickAgent.onResume(this);

    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();

        if (!isAppOnForeground()) {
            mApp.OnAppInBackground();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        try {
            EventBus.getDefault().unregister(this);
        } catch (Exception ex) {

        }
    }

    public LoadingView getLoadingView() {
        if (mLoadingView == null) {
            mLoadingView = new LoadingView(this);
        }

        return mLoadingView;
    }


    public boolean isAppOnForeground() {
        // Returns a list of application processes that are running on the
        // device

        ActivityManager activityManager = (ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        String packageName = getApplicationContext().getPackageName();

        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        if (appProcesses == null)
            return false;

        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            // The name of the process that this object is associated with.
            if (appProcess.processName.equals(packageName) && appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true;
            }
        }

        return false;
    }

}
