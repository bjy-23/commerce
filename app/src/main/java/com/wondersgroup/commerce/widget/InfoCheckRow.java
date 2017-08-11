package com.wondersgroup.commerce.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wondersgroup.commerce.R;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by yclli on 2015/3/14.
 */
public class InfoCheckRow extends RelativeLayout {
    @Bind(R.id.info_bar_text) TextView editText;
    @Bind(R.id.info_bar_check) CheckBox checkBox;
    private Context context;
    private Boolean isChecked = true;

    public InfoCheckRow(Context context) {
        super(context);
        init(context);
    }

    public InfoCheckRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public InfoCheckRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    private void init(Context context){
        this.context=context;
        View v = View.inflate(context, R.layout.view_info_check_row, this);
        ButterKnife.bind(v, this);
    }

    public void setText(String hint){
        editText.setText(hint);
    }

    public String getText(){
        return editText.getText().toString();
    }

    public void setCheckBox(){
        checkBox.setChecked(isChecked);
        isChecked = !isChecked;
    }

    public boolean getCheckBox(){
        return checkBox.isChecked();
    }
}
