package com.domain.main.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cloud.BaseActivity;
import com.domain.main.R;
import com.domain.main.net.ApiClint;
import com.squareup.okhttp.ResponseBody;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.TextChange;
import org.androidannotations.annotations.ViewById;

import java.io.IOException;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

@EActivity(R.layout.activity_home)
public class HomeActivity extends BaseActivity {
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
        Glide.with(this).load("http://b394.photo.store.qq.com/psb?/V10IHpOG3mfnbo/uWrDO42KCowcfYDYXgtDThwwCaxDgJQT2FnQKV*ANrc!/b/dIoBAAAAAAAA&bo=IAPCASADwgEFByQ!&rf=viewer_4").into(picasso_image_view);
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
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.baidu.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiClint.GetBaidu getBaidu = retrofit.create(ApiClint.GetBaidu.class);
        Call<ResponseBody> call = getBaidu.get();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(retrofit.Response<ResponseBody> response, Retrofit retrofit) {
                try {
                    String body = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Throwable t) {
            }
        });
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
}
