package com.wondersgroup.yngs.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wondersgroup.yngs.R;

/**
 * Created by 薛定猫 on 2016/1/22.
 */
public class StaticSquare extends RelativeLayout {
    public static final int COLOR_RED=R.drawable.oval_red;
    public static final int COLOR_GREEN=R.drawable.oval_green;
    public static final int COLOR_BLUE=R.drawable.oval_blue;

    TextView value;
    TextView label;
    public StaticSquare(Context context) {
        super(context);
        init(context);
    }

    public StaticSquare(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public StaticSquare(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    private void init(Context context){
        View.inflate(context, R.layout.view_static_square,this);
        value=(TextView)findViewById(R.id.static_square_value);
        label=(TextView)findViewById(R.id.static_square_label);
    }
    public void setColor(int color){
        value.setBackgroundResource(color);
    }
    public void setValue(String value){
        this.value.setText(value);
    }
    public void setLabel(String label){
        this.label.setText(label);
    }
    public String getValue(){
        return value.getText().toString();
    }
}
