package com.wondersgroup.commerce.ynwq.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wondersgroup.commerce.R;


/**
 * Created by 薛定猫 on 2016/1/20.
 */
public class LabelSelect extends RelativeLayout {
    TextView title;
    public LabelSelect(Context context) {
        super(context);
        init(context);
    }

    public LabelSelect(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LabelSelect(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    public void init(Context context){
        View.inflate(context, R.layout.view_label_select,this);
        title=(TextView)findViewById(R.id.label_select_title);
    }
    public void setTitle(String title){
        this.title.setText(title);
    }
}
