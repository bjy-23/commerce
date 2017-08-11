package com.wondersgroup.commerce.fgdj.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.utils.DWZH;
import com.wondersgroup.commerce.widget.ClearEditText;

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
        LayoutParams lp1 = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        lp1.setMargins(DWZH.dp(21),0,0,0);
        lp1.addRule(CENTER_VERTICAL);
        tvName = new TextView(context);
        tvName.setLayoutParams(lp1);
        tvName.setText(name);
        tvName.setTextSize(14);
        tvName.setTextColor(getResources().getColor(R.color.black));
        addView(tvName);

        LayoutParams lp2 = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        lp2.addRule(ALIGN_PARENT_RIGHT);
        lp2.addRule(CENTER_VERTICAL);
        lp2.setMargins(0,0, DWZH.dp(20),0);
        etInput = new ClearEditText(context);
        etInput.setLayoutParams(lp2);
        etInput.setCompoundDrawablePadding(20);
        etInput.setFocusable(false);
        etInput.setHint(hint);
        etInput.setHintTextColor(getResources().getColor(R.color.gray));
        etInput.setTextSize(14);
        etInput.setTextColor(getResources().getColor(R.color.gray));
        etInput.setBackgroundDrawable(null);
        etInput.setMaxWidth(600);
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
            LayoutParams lp3 = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
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

    public String getInput(){
        return etInput.getText().toString() == null ? "" : etInput.getText().toString();
    }

    public void setInput(String input){
        etInput.setText(input);
    }

    public void setHint(String hint){
        etInput.setHint(hint);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName(){
        return tvName.getText().toString() == null ? "" : tvName.getText().toString();
    }

    public void hideMark(){
        if (tvMark != null)
            tvMark.setVisibility(GONE);
    }

    public void cancel(){
        etInput.setCompoundDrawables(null,null,null,null);
//        etInput.setFocusable(false);
        etInput.setEnabled(false);
//        etInput.setFocusableInTouchMode(false);
//        etInput.setClickable(false);

    }

    public interface inputListener{

    }
}
