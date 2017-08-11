package com.wondersgroup.commerce.fgdj.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.utils.DWZH;

/**
 * Created by bjy on 2017/7/18.
 */

public class TimeUnit extends RelativeLayout{
    private Context context;

    private TextView tvName;
    public TextView tvDate;
    private TextView tvMark;

    private String name;
    private boolean need;
    public TimeUnit(Context context) {
        this(context,null);
    }

    public TimeUnit(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.SelectUnit);
        name = array.getString(R.styleable.SelectUnit_textName);
        need = array.getBoolean(R.styleable.SelectUnit_need,false);

        array.recycle();

        init();
    }

    private void init(){
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
        lp2.setMargins(0,0, DWZH.dp(10),0);
        tvDate = new TextView(context);
        tvDate.setLayoutParams(lp2);
        tvDate.setText("请选择");
        tvDate.setMinWidth(DWZH.dp(100));
        tvDate.setPadding(DWZH.dp(10), DWZH.dp(10), DWZH.dp(10), DWZH.dp(10));
        addView(tvDate);

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

    public void setTime(String time){
        if (time != null)
            tvDate.setText(time);
    }

    public String getTime(){
        return tvDate.getText().toString();
    }

    public String getName(){
        return tvName.getText().toString();
    }

    public void hideMark(){
        if (tvMark != null)
            tvMark.setVisibility(GONE);
    }

    public void cancel(){
        tvDate.setClickable(false);
    }
}
