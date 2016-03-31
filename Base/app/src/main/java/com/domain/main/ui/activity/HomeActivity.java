package com.domain.main.ui.activity;

import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cloud.BaseActivity;
import com.domain.main.R;
import com.domain.main.app.Constant;
import com.domain.main.app.OkHttpManager;
import com.domain.main.view.MyAppTitle;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.TextChange;
import org.androidannotations.annotations.ViewById;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Request;

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

    @ViewById
    MyAppTitle app_title;


    @AfterViews
    public void initView() {
        setMyAppTitle();
        Glide.with(this).load("http://b394.photo.store.qq.com/psb?/V10IHpOG3mfnbo/uWrDO42KCowcfYDYXgtDThwwCaxDgJQT2FnQKV*ANrc!/b/dIoBAAAAAAAA&bo=IAPCASADwgEFByQ!&rf=viewer_4").into(picasso_image_view);
    }

    @Click
    void btn() {
        postRequest();
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

    private void postRequest() {
        OkHttpUtils.getInstance().getCookieStore();
        Map<String, String> map = new HashMap<>();
        map.put("page", "1");
        OkHttpManager.sendRequest(new MyStringCallback(), map, this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkHttpManager.cancelRequest(this);
    }

    public class MyStringCallback extends StringCallback {
        @Override
        public void onBefore(Request request) {
            super.onBefore(request);
        }

        @Override
        public void onAfter() {
            super.onAfter();
            getLoadingView().hideLoding();
            forwardActivity(SecondActivity_.class);
        }

        @Override
        public void onResponse(String response) {
            Toast.makeText(HomeActivity.this, response, Toast.LENGTH_LONG).show();
        }

        @Override
        public void inProgress(float progress) {
            getLoadingView().showLoading();
        }

        @Override
        public void onError(Call call, Exception e) {
        }
    }

    private MyAppTitle mMyAppTitle;

    private void setMyAppTitle() {
        app_title.initViewsVisible(true, false, true, false, true);
        app_title.setRightButtonTitleOrImage(true, Constant.APP_TITLE_BLACK, 0);
        String titleStr = "好好学车";
        app_title.setAppTitle(titleStr);
        app_title.setOnLeftButtonClickListener(new MyAppTitle.OnLeftButtonClickListener() {
            @Override
            public void onLeftButtonClick(View v) {
                onBackPressed();
            }
        });
    }
}
