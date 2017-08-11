package com.wondersgroup.commerce.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wondersgroup.commerce.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yclli on 16/8/31.
 */
public class InfoSelectBar extends RelativeLayout {
    @Bind(R.id.info_bar_icon)
    ImageView icon;
    @Bind(R.id.info_bar_text)
    TextView editText;
    private Context context;
    private String content;

    public InfoSelectBar(Context context) {
        this(context,null);
        init(context);
    }

    public InfoSelectBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs,R.styleable.InfoSelectBar);
        content = array.getString(R.styleable.InfoSelectBar_content);
        init(context);
    }

    private void init(Context context){
        this.context=context;
        View v = View.inflate(context, R.layout.view_info_select_bar, this);
        TextView textView = (TextView) v.findViewById(R.id.info_bar_text);
        textView.setText(content);

        ButterKnife.bind(v, this);
    }
    public void setIcon(int iconRes){
        icon.setImageDrawable(ContextCompat.getDrawable(context, iconRes));
    }
    public void setIconVisibility(int visibility){
        if(visibility==View.GONE){
            RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.CENTER_VERTICAL);
            params.setMargins((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16, getResources().getDisplayMetrics()),
                    0,(int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, getResources().getDisplayMetrics()),0);
            editText.setLayoutParams(params);
        }
        icon.setVisibility(visibility);

    }
    public void setText(String hint){
        editText.setText(hint);
    }
    public String getText(){
        return editText.getText().toString();
    }
}
