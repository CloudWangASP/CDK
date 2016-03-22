package com.domain.main.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.LruCache;
import android.view.KeyEvent;
import android.view.View;
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
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.HashMap;
import java.util.Map;

@EActivity(R.layout.activity_home)
public class HomeActivity extends AppCompatActivity {
    @ViewById
    TextView tv;

    @ViewById
    Button btn;

    @ViewById
    EditText password;

    @ViewById
    EditText passwordtwo;

    @ViewById
    ImageView picasso_image_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @AfterViews
    public void initView() {
        tv.setText("Welcome to the new world!");
        password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });
        Picasso.with(this).load("http://avatar.csdn.net/6/6/D/1_lfdfhl.jpg").into(picasso_image_view);

    }

    private void attemptLogin() {
        password.setError(null);
        String mPassword = password.getText().toString();
        boolean cancel = false;
        View focusView = null;
        if (!isPasswordValid(mPassword)) {
            password.setError("请输入密码");
            focusView = password;
            cancel = true;
        }
        if (cancel) {
            focusView.requestFocus();
        }
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
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
                        updateUI();
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

    @UiThread
    public void updateUI() {
        //TODO:更新U...
        hideKeyboard();
    }

    @Override
    protected void onStop() {
        super.onStop();
        MyApplication.getInstance().cancelPendingRequests("请求名称");
    }

    //登陆时调用隐藏键盘
    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
