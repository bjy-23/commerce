package com.wondersgroup.commerce.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.utils.DWZH;

/**
 * Created by bjy on 2017/7/5.
 */

public class InputUnit extends RelativeLayout {
    private Context context;
    public TextView tvName,tvMark;
    public ClearEditText etInput;

    private String name,hint;
    private boolean need;
    public InputUnit(Context context) {
        this(context,null);
    }

    public InputUnit(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.SelectUnit);
        name = array.getString(R.styleable.SelectUnit_textName);
        need = array.getBoolean(R.styleable.SelectUnit_need,false);
        hint = array.getString(R.styleable.SelectUnit_textHint)==null?"请输入":array.getString(R.styleable.SelectUnit_textHint);
        array.recycle();
        init();
    }

    public void init(){
        RelativeLayout.LayoutParams lp1 = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        lp1.setMargins(DWZH.dp(21),0,0,0);
        lp1.addRule(CENTER_VERTICAL);
        tvName = new TextView(context);
        tvName.setLayoutParams(lp1);
        tvName.setText(name);
        tvName.setTextSize(14);
        tvName.setTextColor(getResources().getColor(R.color.black));
        addView(tvName);

        RelativeLayout.LayoutParams lp2 = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        lp2.addRule(ALIGN_PARENT_RIGHT);
        lp2.addRule(CENTER_VERTICAL);
        lp2.setMargins(0,0,DWZH.dp(20),0);
        etInput = new ClearEditText(context);
        etInput.setLayoutParams(lp2);
        etInput.setCompoundDrawablePadding(20);
        etInput.setFocusable(false);
        etInput.setHint(hint);
        etInput.setTextSize(14);
        etInput.setBackgroundDrawable(null);
        etInput.setMaxWidth(300);
        etInput.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                etInput.setSelection(etInput.getText().length());
                etInput.setFocusable(true);
                etInput.setFocusableInTouchMode(true);
                etInput.requestFocus();
                etInput.requestFocusFromTouch();
                InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(etInput,0);
            }
        });
        addView(etInput);

        if (need){
            RelativeLayout.LayoutParams lp3 = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            lp3.addRule(CENTER_VERTICAL);
            lp3.setMargins(DWZH.dp(10),0,0,0);
            tvMark = new TextView(context);
            tvMark.setLayoutParams(lp3);
            tvMark.setText("*");
            tvMark.setTextColor(getResources().getColor(R.color.red));
            addView(tvMark);
        }
    }
}
