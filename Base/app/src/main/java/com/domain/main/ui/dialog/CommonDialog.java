package com.domain.main.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.StringRes;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.domain.main.R;


/**
 * 通用提示框
 */
public class CommonDialog extends Dialog implements
        View.OnClickListener {

    private static Dialog mDialog;

    private LinearLayout mTitleLayout;

    private TextView mTitle;

    private View mTitleLine;

    private TextView mMessage;

    private TextView mCommit;

    private TextView mCancle;

    private CommonDialogBtnListener btnListener;

    private boolean isSingleBtn;

    public CommonDialog(Context context,
                        boolean paramIsSingleBtn, CommonDialogBtnListener paramListener) {
        super(context, R.style.CommonDialogTheme);
        // TODO Auto-generated constructor stub
        this.isSingleBtn = paramIsSingleBtn;
        this.btnListener = paramListener;
        init();
    }

    private void init() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        View rootView = getLayoutInflater().inflate(
                R.layout.view_common_dialog, null);

        setContentView(rootView);

        this.mTitleLayout = (LinearLayout) rootView.findViewById(R.id.tip_title_layout);
        this.mTitle = (TextView) rootView.findViewById(R.id.tip_title);
        this.mTitleLine = rootView.findViewById(R.id.tip_title_line);
        this.mMessage = (TextView) rootView.findViewById(R.id.tip_message);
        this.mCancle = (TextView) rootView.findViewById(R.id.tip_cancle);
        this.mCommit = (TextView) rootView.findViewById(R.id.tip_commit);


        if (isSingleBtn) {
            this.mCancle.setVisibility(View.GONE);
        }

        this.mCancle.setOnClickListener(this);
        this.mCommit.setOnClickListener(this);

        setOnShowListener(null);
        setOnDismissListener(null);

        this.mTitleLayout.setVisibility(View.GONE);

        Window dialogWindow = getWindow();
        dialogWindow.setGravity(Gravity.CENTER);
        
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            if (btnListener != null) {
                btnListener.onCancleClick();
                dismiss();
            }
            return false;
        }else {
            return super.onKeyDown(keyCode, event);
        }
    }

    @Override
    public void setOnShowListener(final OnShowListener listener) {
        // TODO Auto-generated method stub

        super.setOnShowListener(new OnShowListener() {

            @Override
            public void onShow(DialogInterface dialog) {
                // TODO Auto-generated method stub
                if (listener != null) {
                    listener.onShow(dialog);
                }

                if (mDialog != null && mDialog.isShowing()) {
                    mDialog.dismiss();
                    mDialog = null;
                }

                mDialog = CommonDialog.this;
            }
        });

    }

    @Override
    public void setOnDismissListener(final OnDismissListener listener) {
        // TODO Auto-generated method stub
        super.setOnDismissListener(new OnDismissListener() {

            @Override
            public void onDismiss(DialogInterface dialog) {
                // TODO Auto-generated method stub
                if (listener != null) {
                    listener.onDismiss(dialog);
                }

                mDialog = null;
            }
        });
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub

        switch (v.getId()) {
            case R.id.tip_cancle:
                if (btnListener != null) {
                    btnListener.onCancleClick();
                }
                break;
            case R.id.tip_commit:
                if (btnListener != null) {
                    btnListener.onCommitClick();
                }
                break;
        }

        dismiss();
    }

    /**
     * 设置message
     *
     * @param messageResID
     */
    public void showMessage(int messageResID) {
        this.mMessage.setText(messageResID);
    }

    /**
     * 设置message
     *
     * @param message
     */
    public void showMessage(String message) {
        this.mMessage.setText(message);
    }

    /**
     * 显示title
     */
    public void showTitle() {
        this.mTitleLayout.setVisibility(View.VISIBLE);
    }

    /**
     * 设置title
     *
     * @param titleResID
     */
    public void showTitle(int titleResID) {
        this.mTitle.setText(titleResID);
        this.mTitleLayout.setVisibility(View.VISIBLE);
    }

    /**
     * 设置title
     *
     * @param title
     */
    public void showTitle(String title) {
        this.mTitle.setText(title);
        this.mTitleLayout.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏title底部线条
     */
    public void setHideTitleLine() {
        this.mTitleLine.setVisibility(View.GONE);
    }

    /**
     * 隐藏title
     */
    public void setHideTitle() {
        this.mTitleLayout.setVisibility(View.GONE);
    }

    /**
     * 设置确定和取消文字
     *
     * @param commitResID
     * @param cancleResID
     */
    public void setCommitAndCancleTextResID(@StringRes int commitResID, @StringRes int cancleResID) {
        this.mCommit.setText(commitResID);
        this.mCancle.setText(cancleResID);
    }

    /**
     * 底部按钮点击事件
     */
    public interface CommonDialogBtnListener {
        void onCommitClick();

        void onCancleClick();
    }
}
