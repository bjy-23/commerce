package com.wondersgroup.commerce.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.wondersgroup.commerce.R;

/**
 * Created by bjy on 2017/5/19.
 */

public class SearchView extends RelativeLayout implements View.OnClickListener{
    private Context mContext;
    private String textHint;
    private EditText editText;

    public SearchView(Context context) {
        this(context,null);
    }

    public SearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }


    private void init(){
        //设置点击事件
        setOnClickListener(this);
        //设置背景
        Drawable drawableBg = mContext.getResources().getDrawable(R.drawable.rect_corner_white);
        setBackgroundDrawable(drawableBg);

        editText = new EditText(mContext);
        textHint = "搜索";
        editText.setHint(textHint);
        editText.setGravity(Gravity.CENTER);
        editText.setFocusable(false);
    }

    @Override
    public void onClick(View v) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        editText.requestFocusFromTouch();
        editText.setGravity(Gravity.CENTER_VERTICAL|Gravity.LEFT);
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText,0);
    }
}
