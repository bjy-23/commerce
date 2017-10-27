package com.wondersgroup.commerce.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wondersgroup.commerce.R;


/**
 * Created by kangrenhui on 2016/2/29.
 */
public class LoadingDialog extends Dialog{
    private Builder builder;

    private LoadingDialog(@NonNull Context context) {
        super(context);
    }

    private LoadingDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    public static final class Builder{
        private Context context;
        private String msg = "加载中...";
        private boolean cancelable;

        public Builder(Context context){
            this.context = context;
        }

        public Builder msg(String msg){
            this.msg = msg;
            return this;
        }

        public Builder cancelable(boolean cancelable){
            this.cancelable = cancelable;
            return this;
        }

        public LoadingDialog build(){
            LayoutInflater inflater = LayoutInflater.from(context);
            View v = inflater.inflate(R.layout.loading_dialog, null);// 得到加载view
            LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);// 加载布局
            // main.xml中的ImageView
            ImageView spaceshipImage = (ImageView) v.findViewById(R.id.img);
            // 加载动画
            Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(
                    context, R.anim.loading_animation);
            // 使用ImageView显示动画
            spaceshipImage.startAnimation(hyperspaceJumpAnimation);

            TextView tvMsg = (TextView) v.findViewById(R.id.tv_msg);
            tvMsg.setText(msg);

            LoadingDialog loadingDialog = new LoadingDialog(context, R.style.loading_dialog);// 创建自定义样式dialog

            loadingDialog.setCancelable(cancelable);
            loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局

            return loadingDialog;
        }
    }
}
