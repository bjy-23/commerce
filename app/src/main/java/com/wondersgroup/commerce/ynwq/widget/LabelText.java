package com.wondersgroup.commerce.ynwq.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wondersgroup.commerce.R;


/**
 * Created by 薛定猫 on 2016/1/19.
 */
public class LabelText extends RelativeLayout {
    TextView title;
    TextView content;
    public LabelText(Context context) {
        super(context);
        init(context);
    }

    public LabelText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LabelText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    private void init(Context context){
        View.inflate(context, R.layout.view_label_text,this);
        title=(TextView)findViewById(R.id.label_text_title);
        content=(TextView)findViewById(R.id.label_text_content);
    }
    public void setTitle(String title){
        this.title.setText(title);
    }
    public void setContent(String content){
        this.content.setText(content);
    }
}
