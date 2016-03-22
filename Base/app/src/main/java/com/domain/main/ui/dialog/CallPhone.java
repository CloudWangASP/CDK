package com.domain.main.ui.dialog;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.domain.main.R;


/**
 * Created by colin on 15/8/9.
 * <p/>
 * 拨打电话 统一窗口
 */
public class CallPhone {

    /**
     * 拨打电话
     *
     * @param context
     * @param phoneNum 电话号码 需要提前处理掉没用的字符
     */
    public static void doCallPhone(final Context context, final String phoneNum) {
        final CommonDialog dialog = new CommonDialog(context, false, new CommonDialog.CommonDialogBtnListener() {
            @Override
            public void onCommitClick() {
                Intent goIntent = new Intent(Intent.ACTION_CALL, Uri.parse(("tel:" + phoneNum)));
                context.startActivity(goIntent);
            }

            @Override
            public void onCancleClick() {

            }
        });
        
        dialog.showMessage(context.getString(R.string.call_phone_message) + phoneNum);

        dialog.show();
    }

}
