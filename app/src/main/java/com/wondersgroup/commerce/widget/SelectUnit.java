package com.wondersgroup.commerce.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.utils.DWZH;

/**
 * Created by bjy on 2017/7/4.
 */

public class SelectUnit extends RelativeLayout {
    private Context context;
    private TextView tvMark;
    public TextView tvName;
    public TextView tvSelected;

    private String name,select;
    private boolean need;
    private boolean selected;
    private int selectId;

    public SelectUnit(Context context) {
        this(context,null);
    }

    public SelectUnit(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.SelectUnit);
        name = array.getString(R.styleable.SelectUnit_textName);
        need = array.getBoolean(R.styleable.SelectUnit_need,false);
        selectId = array.getInt(R.styleable.SelectUnit_selectId,0);
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
        tvSelected = new TextView(context);
        tvSelected.setId(selectId);
        tvSelected.setLayoutParams(lp2);
        Drawable drawableRight = getResources().getDrawable(R.mipmap.app_right);
        drawableRight.setBounds(0,0,drawableRight.getIntrinsicWidth(),drawableRight.getIntrinsicHeight());
        tvSelected.setCompoundDrawables(null,null,drawableRight,null);
        tvSelected.setCompoundDrawablePadding(20);
        tvSelected.setTextSize(14);
        tvSelected.setGravity(Gravity.RIGHT);
        tvSelected.setMinWidth((int) DWZH.dp2pt(context,100));
        tvSelected.setPadding(DWZH.dp(10),DWZH.dp(10),DWZH.dp(10),DWZH.dp(10));
        addView(tvSelected);

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

    public void setTvName(String name) {
        tvName.setText(name);
    }

    public String getTvName(){
        return tvName.getText().toString();
    }

    @Override
    public boolean isSelected() {
        return selected;
    }

    @Override
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
