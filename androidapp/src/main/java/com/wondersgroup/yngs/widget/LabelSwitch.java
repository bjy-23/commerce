package com.wondersgroup.yngs.widget;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wondersgroup.yngs.R;

/**
 * Created by 薛定猫 on 2016/1/19.
 */
public class LabelSwitch extends RelativeLayout {
    TextView title;
    RadioGroup radioGroup;
    private boolean isRequired=false;
    private SpannableString sb;
    private String titleText;
    public LabelSwitch(Context context) {
        super(context);
        init(context);
    }

    public LabelSwitch(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LabelSwitch(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    public void init(Context context){
        View.inflate(context, R.layout.view_label_switch,this);
        title=(TextView)findViewById(R.id.label_switch_title);
        radioGroup=(RadioGroup)findViewById(R.id.label_switch_radioGroup);
    }
    public void isRequired(boolean isRequired){
        this.isRequired=isRequired;
        sb=new SpannableString(title.getText().toString()+"*");
        sb.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getContext(), R.color.red)),sb.length()-1,sb.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        title.setText(sb);
    }
    public void setTitle(String title){
        this.titleText=title;
        if(!isRequired)this.title.setText(title);
    }
    public void disable(){
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            radioGroup.getChildAt(i).setEnabled(false);
        }
    }
    public void reset(){
        radioGroup.clearCheck();
    }
    public void check(boolean which){
        if(which){
            radioGroup.check(R.id.label_switch_yes);
        }else {
            radioGroup.check(R.id.label_switch_no);
        }
    }
    public int getSelection(){
        if(radioGroup.getCheckedRadioButtonId()==R.id.label_switch_yes){
            return 1;
        }else if(radioGroup.getCheckedRadioButtonId()==R.id.label_switch_no){
            return 0;
        }else {
            return -1;
        }
    }
}
