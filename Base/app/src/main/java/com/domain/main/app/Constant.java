package com.domain.main.app;

/**
 * Created by cloud_wang on 16/2/24.
 * <p/>
 * 常量
 */
public class Constant {

    /**
     * 接口域名前缀标志
     */
    public static String HTTP_URL_BASE = "http://testopen.xiaoeguanfan.com/";

    /**
     * ******************************** 常量配置信息 ***************************************
     */

    /**
     * 手机端密钥
     */
    public static String SECRET_KEY = "3e17a32c3d0d6792";

    /**
     * 手机端信息标记
     */
    public static String PHONE_MARK = "android-jiachu";

    /*********************************** 接口开始 ****************************************/

    /**
     * 登录
     */
    public static String HTTP_URL_POST_LOGIN = HTTP_URL_BASE
            + "openUser/login";

    /**
     * 获取验证码
     */
    public static String HTTP_URL_POST_GETVERIFYCODE = HTTP_URL_BASE
            + "openUser/genVerifyCode";

}
