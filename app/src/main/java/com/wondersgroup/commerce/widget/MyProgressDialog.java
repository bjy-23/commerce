package com.wondersgroup.commerce.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.wondersgroup.commerce.R;


public class MyProgressDialog {
    private static Dialog progressDialog;
    public static boolean showFlag;

    public static void show(Context context) {
        showFlag = true;


        progressDialog = new Dialog(context, R.style.loading_dialog);
        progressDialog.setContentView(View.inflate(context,
                        R.layout.dialog_loading, null),
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT));
        ImageView spaceshipImage = (ImageView) progressDialog
                .findViewById(R.id.img);
        Animation runAnim = AnimationUtils.loadAnimation(context,
                R.anim.loading_dialog);
        spaceshipImage.startAnimation(runAnim);

        progressDialog.setCancelable(false);

        progressDialog.setOnCancelListener(new OnCancelListener() {

            @Override
            public void onCancel(DialogInterface dialog) {
                showFlag = false;

            }
        });

        progressDialog.show();

    }

    public static void dismiss() {
        showFlag = false;

        if (progressDialog == null) {
            showFlag = false;

            return;
        } else {
            progressDialog.dismiss();
        }

    }

    public static boolean isShow() {

        return showFlag;
    }
}
