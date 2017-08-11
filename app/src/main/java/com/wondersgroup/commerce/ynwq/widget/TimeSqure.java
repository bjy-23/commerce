package com.wondersgroup.commerce.ynwq.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wondersgroup.commerce.R;

/**
 * Created by 薛定猫 on 2015/12/17.
 */
public class TimeSqure extends LinearLayout{
    private Context context;
    private TextView squreText;
    private ImageView squreIcon;
    public TimeSqure(Context context) {
        super(context);
        init(context);
    }

    public TimeSqure(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TimeSqure(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    public void init(Context context){
        this.context=context;
        View.inflate(context, R.layout.view_time_squre,this);
        squreText= (TextView) findViewById(R.id.time_squre_text);
        squreIcon=(ImageView)findViewById(R.id.time_squre_icon);
    }
    public void setText(String text){
        squreText.setText(text);
    }
    public String getText(){
        return squreText.getText().toString();
    }
}
