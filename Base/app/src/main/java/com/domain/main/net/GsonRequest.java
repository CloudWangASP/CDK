package com.domain.main.net;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cloud_wang on 16/2/25.
 */
public class GsonRequest<T> extends Request<T> {

    private final Response.Listener<T> mListener;

    private Gson mGson;

    private Class<T> mClass;

    public String cookieFromResponse;

    private static Map<String, String> mHeader = new HashMap<>();

    /**
     * 设置访问服务器时必须传递的参数，密钥
     */
    static {
//        mHeader.put("APP-Key", "LBS-AAA");
//        mHeader.put("APP-Secret", "ad12msa234das232in");
//        mHeader.put("APP-Time", System.currentTimeMillis() + "");
    }

    /**
     * @param url
     * @param clazz
     * @param listener
     * @param appendHeader
     * @param errorListener
     */
    public GsonRequest(String url, Class<T> clazz, Response.Listener<T> listener, Map<String, String> appendHeader,
                       Response.ErrorListener errorListener) {
        super(Method.POST, url, errorListener);
        this.mClass = clazz;
        this.mListener = listener;
        mHeader.putAll(appendHeader);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        // 默认返回 return Collections.emptyMap();
        return mHeader;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));
//            //获取服务器返回的cookie
//            Pattern pattern=Pattern.compile("Set-Cookie.*?;");
//            Matcher m=pattern.matcher(response.headers.toString());
//            if(m.find()){
//                cookieFromResponse =m.group();
//            }
//            //去掉cookie末尾的分号
//            cookieFromResponse = cookieFromResponse.substring(11,cookieFromResponse.length()-1);
            return Response.success(mGson.fromJson(jsonString, mClass),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }
}
