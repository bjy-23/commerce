package com.wondersgroup.commerce.ynwq.widget;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wondersgroup.commerce.R;


/**
 * Created by 薛定猫 on 2015/10/23.
 */
public class InfoSelectBar extends RelativeLayout {
    private ImageView icon;
    private TextView editText;
    private Context context;
    public InfoSelectBar(Context context) {
        super(context);
        init(context);
    }

    public InfoSelectBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public InfoSelectBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    private void init(Context context){
        this.context=context;
        View.inflate(context, R.layout.view_info_select_bar, this);
        icon = (ImageView) findViewById(R.id.info_bar_icon);
        editText=(TextView)findViewById(R.id.info_bar_text);
    }
    public void setIcon(int iconRes){
        icon.setImageDrawable(ContextCompat.getDrawable(context, iconRes));
    }
    public void setIconVisibility(int visibility){
        if(visibility==View.GONE){
            LayoutParams params=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.CENTER_VERTICAL);
            params.setMargins((int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16, getResources().getDisplayMetrics()),0,0,0);
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
