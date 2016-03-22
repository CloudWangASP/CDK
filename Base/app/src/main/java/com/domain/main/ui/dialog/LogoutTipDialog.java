package com.domain.main.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.domain.main.R;


/**
 * 通用提示框
 */
public class LogoutTipDialog extends Dialog implements
        View.OnClickListener {

    private static Dialog mDialog;

    private TextView mCommit;

    private TextView mCancle;

    private CommonDialog.CommonDialogBtnListener btnListener;

    public LogoutTipDialog(Context context, CommonDialog.CommonDialogBtnListener paramListener) {
        super(context, R.style.CommonDialogTheme);
        // TODO Auto-generated constructor stub
        this.btnListener = paramListener;
        init();
    }

    private void init() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        View rootView = getLayoutInflater().inflate(
                R.layout.view_logout_tip_dialog, null);

        setContentView(rootView);

        this.mCancle = (TextView) rootView.findViewById(R.id.logout_tip_cancle);
        this.mCommit = (TextView) rootView.findViewById(R.id.logout_tip_commit);

        this.mCancle.setOnClickListener(this);
        this.mCommit.setOnClickListener(this);

        setOnShowListener(null);
        setOnDismissListener(null);

        Window dialogWindow = getWindow();
        dialogWindow.setGravity(Gravity.CENTER);

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

                mDialog = LogoutTipDialog.this;
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
            case R.id.logout_tip_cancle:
                if (btnListener != null) {
                    btnListener.onCancleClick();
                }
                break;
            case R.id.logout_tip_commit:
                if (btnListener != null) {
                    btnListener.onCommitClick();
                }
                break;
        }

        dismiss();
    }
}
