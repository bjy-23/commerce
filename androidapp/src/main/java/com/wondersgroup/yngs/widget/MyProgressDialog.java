package com.wondersgroup.yngs.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.wondersgroup.yngs.R;

/**
 * Created by 1229 on 2015/12/29.
 */
public class MyProgressDialog {
    private static Dialog progressDialog;

    public static void show(Context context) {
        progressDialog = new Dialog(context, R.style.loading_dialog);
        progressDialog.setContentView(View.inflate(context,
                        R.layout.item_loading_dialog, null),
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT));
        ImageView spaceshipImage = (ImageView) progressDialog
                .findViewById(R.id.img);
        Animation runAnim = AnimationUtils.loadAnimation(context,
                R.anim.loading_dialog);
        spaceshipImage.startAnimation(runAnim);
        progressDialog.setCancelable(false);

        progressDialog.show();
    }

    public static void dismiss() {
        if (progressDialog == null) {
            return;
        } else {
            progressDialog.dismiss();
        }
    }
}

