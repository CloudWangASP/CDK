package com.domain.main.app;

import android.app.Activity;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.Map;

/**
 * Created by cloud_wang on 16/3/31.
 */
public class OkHttpManager {
    public static Callback sendRequest(StringCallback callback, Map<String, String> map, Activity act) {
        OkHttpUtils.post().url(Constant.HTTP_URL_TEST)
                .addHeader("apikey", "ffac023cd51e5d0430d6ceaecf623c2e")
                .params(map)
                .tag(act)
                .build().execute(callback);
        return callback;
    }

    public static void cancelRequest(Activity act) {
        OkHttpUtils.getInstance().cancelTag(act);
    }
}
