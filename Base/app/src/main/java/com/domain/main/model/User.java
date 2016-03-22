package com.domain.main.model;

/**
 * Created by cloud_wang on 16/2/26.
 */
public class User {
    /**
     * 返回code
     */
    public String code;

    /**
     * 返回消息
     */
    public String msg;

    /**
     * 返回数据body
     */
    public GetVerifyCodeDataResponse data;

    @Override
    public String toString() {
        return "User{" +
                "code='" + code + '\'' +
                "msg='" + msg + '\'' +
                "data=" + data +
                '}';
    }
}
