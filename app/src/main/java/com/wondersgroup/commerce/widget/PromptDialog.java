package com.wondersgroup.commerce.widget;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.wondersgroup.commerce.R;

/**
 * Created by yclli on 2016/2/29.
 */
public class PromptDialog {

    protected MaterialDialog mDialog;
    protected View mainView;
    protected TextView mTitle;
    protected TextView mContent;
    protected Button comfirmBtn;
    protected Button cancelBtn;

    public PromptDialog(Context context){
        MaterialDialog.Builder builer = new MaterialDialog.Builder(context);
        builer.customView(R.layout.dialog_prompt, false);
        mDialog = builer.build();
        mainView = mDialog.getCustomView();
        mTitle = (TextView) mainView.findViewById(R.id.title);
        mContent = (TextView) mainView.findViewById(R.id.content);
        comfirmBtn = (Button) mainView.findViewById(R.id.comfirmBtn);
        cancelBtn = (Button) mainView.findViewById(R.id.cancelBtn);
    }

    public PromptDialog setTitle(String title){
        mTitle.setText(title);
        return this;
    }

    public PromptDialog setContent(String content){
        mContent.setText(content);
        return this;
    }

    public PromptDialog setPositiveBtn(String txt, View.OnClickListener listener){
        comfirmBtn.setText(txt);
        comfirmBtn.setOnClickListener(listener);
        return this;
    }

    public PromptDialog setNegativeBtn(String txt, View.OnClickListener listener){
        cancelBtn.setText(txt);
        cancelBtn.setOnClickListener(listener);
        return this;
    }

    public void show(){
        mDialog.show();
    }

    public void dismiss(){
        mDialog.dismiss();
    }

}
