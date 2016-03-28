package com.domain.main.net;

import com.squareup.okhttp.ResponseBody;

import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by cloud_wang on 16/3/28.
 */
public class ApiClint {
    public interface GetBaidu{
        @GET("http://www.baidu.com/")
        Call<ResponseBody> get();
    }
}
