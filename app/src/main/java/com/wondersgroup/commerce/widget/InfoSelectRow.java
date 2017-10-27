package com.wondersgroup.commerce.widget;

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

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by 薛定猫 on 2015/10/23.
 */
public class InfoSelectRow extends RelativeLayout {
    @BindView(R.id.info_bar_title)  TextView titleText;
    @BindView(R.id.info_bar_text) TextView editText;
    private Context context;

    public InfoSelectRow(Context context) {
        super(context);
        init(context);
    }

    public InfoSelectRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public InfoSelectRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    private void init(Context context){
        this.context=context;
        View v = View.inflate(context, R.layout.view_info_select_row, this);
        ButterKnife.bind(v, this);
    }
    public void setTitle(String title){
        titleText.setText(title);
    }
    public void setTextHint(String hint){
        editText.setHint(hint);
    }
    public void setText(String txt){
        editText.setText(txt);
    }
    public String getText(){
        return editText.getText().toString();
    }
}
