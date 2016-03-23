package com.domain.main.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.LruCache;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.domain.main.R;
import com.domain.main.app.Constant;
import com.domain.main.app.MyApplication;
import com.domain.main.model.User;
import com.domain.main.net.GsonRequest;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.TextChange;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.WindowFeature;

import java.util.HashMap;
import java.util.Map;

@EActivity(R.layout.activity_home)
public class HomeActivity extends AppCompatActivity {
    @ViewById
    Button btn;

    @ViewById
    EditText username;

    @ViewById
    EditText password;

    @ViewById
    ImageView picasso_image_view;

    @ViewById
    TextInputLayout text_input_password;

    @ViewById
    TextInputLayout text_input_username;


    @AfterViews
    public void initView() {
        Picasso.with(this).load("http://b394.photo.store.qq.com/psb?/V10IHpOG3mfnbo/uWrDO42KCowcfYDYXgtDThwwCaxDgJQT2FnQKV*ANrc!/b/dIoBAAAAAAAA&bo=IAPCASADwgEFByQ!&rf=viewer_4").into(picasso_image_view);
    }

    @Click
    void btn() {
//        sendRequest();
        Intent intent = new Intent();
        intent.setClass(this, SecondActivity_.class);
        startActivity(intent);
    }

    @Background
    public void sendRequest() {
        Map<String, String> appendHeader = new HashMap<>();
        appendHeader.put("phone", "18510970806");
        GsonRequest<User> userRequest = new GsonRequest<>(Constant.HTTP_URL_POST_GETVERIFYCODE, User.class,
                new Response.Listener<User>() {
                    @Override
                    public void onResponse(User response) {
                        Log.e("response", response.toString());
                    }
                }, appendHeader,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.e("error", volleyError.toString());
                    }
                });
        userRequest.setTag("请求名称");
        MyApplication.getInstance().getRequestQueue().add(userRequest);
    }

    @TextChange(R.id.username)
    void onUserChanges(CharSequence text, TextView tv, int before, int start, int count) {
        if (text.length() > 6) {
            text_input_username.setErrorEnabled(true);
            text_input_username.setError("亲，只能输入6位哦！");
            hideKeyboard();
        } else {
            text_input_username.setErrorEnabled(false);
        }
    }

    @TextChange(R.id.password)
    void onPsdChanges(CharSequence text, TextView tv, int before, int start, int count) {
        if (text.length() > 6) {
            text_input_password.setErrorEnabled(true);
            text_input_password.setError("亲，只能输入6位哦！");
            hideKeyboard();
        } else {
            text_input_password.setErrorEnabled(false);
        }
    }

    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        MyApplication.getInstance().cancelPendingRequests("请求名称");
    }
}
