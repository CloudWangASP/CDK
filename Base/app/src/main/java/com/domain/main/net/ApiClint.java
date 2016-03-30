package com.domain.main.net;

import com.squareup.okhttp.ResponseBody;

import retrofit.Call;
import retrofit.http.POST;

/**
 * Created by cloud_wang on 16/3/28.
 */
public class ApiClint {
    public interface GetBaidu {
        @POST("http://www.baidu.com/")
        Call<ResponseBody> get();
    }

//    //图片上传
//    @Multipart
//    @POST("/user/edit")
//    Call<User> upload(@Part("image\"; filename=\"文件名.jpg") RequestBody file);
}
