package com.wondersgroup.commerce.ynwq.widget;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.wondersgroup.commerce.R;


/**
 * Created by 薛定猫 on 2015/10/23.
 */
public class InfoEditBar extends RelativeLayout {
    private ImageView icon;
    private EditText editText;
    private Context context;
    public InfoEditBar(Context context) {
        super(context);
        init(context);
    }

    public InfoEditBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public InfoEditBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    private void init(Context context){
        this.context=context;
        View.inflate(context, R.layout.view_info_edit_bar,this);
        icon = (ImageView) findViewById(R.id.info_bar_icon);
        editText=(EditText)findViewById(R.id.info_bar_text);
    }
    public void setIcon(int iconRes){
        icon.setImageDrawable(ContextCompat.getDrawable(context, iconRes));
    }
    public void setText(String text){
        editText.setText(text);
    }
    public void setHint(String hint){
        editText.setHint(hint);
    }
    public String getText(){
        return editText.getText().toString();
    }
}
