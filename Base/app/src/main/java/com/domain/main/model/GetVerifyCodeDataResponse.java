package com.domain.main.model;

/**
 * Created by cloud_wang on 16/3/2.
 */
public class GetVerifyCodeDataResponse {
    /**
     * 验证码有效期，按秒计算，可用于客户端数秒
     */
    public String seconds;


    @Override
    public String toString() {
        return "GetVerifyCodeDataResponse{" +
                "seconds='" + seconds + '\'' +
                '}';
    }
}
