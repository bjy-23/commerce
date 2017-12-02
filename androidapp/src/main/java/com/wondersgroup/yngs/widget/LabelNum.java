package com.wondersgroup.yngs.widget;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wondersgroup.yngs.R;

/**
 * Created by 薛定猫 on 2016/1/19.
 */
public class LabelNum extends RelativeLayout  implements View.OnFocusChangeListener{
    TextView title;
    EditText content;
    TextView unit;
    private boolean isRequired=false;
    private SpannableString sb;
    private String titleText;
    public LabelNum(Context context) {
        super(context);
        init(context);
    }

    public LabelNum(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LabelNum(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    public void init(Context context){
        View.inflate(context, R.layout.view_label_num,this);
        title=(TextView)findViewById(R.id.label_num_title);
        content=(EditText)findViewById(R.id.label_num_content);
        unit=(TextView)findViewById(R.id.label_num_unit);
        //content.setOnFocusChangeListener(this);
    }
    public void isRequired(boolean isRequired){
        this.isRequired=isRequired;
        sb=new SpannableString(title.getText().toString()+"*");
        sb.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getContext(), R.color.red)),sb.length()-1,sb.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        title.setText(sb);
    }
    public void setContent(String content){
        this.content.setText(content);
    }
    public void setHint(String hint){
        content.setHint(hint);
    }
    public String getText(){
        return content.getText().toString();
    }
    public void setTitle(String title){
        this.titleText=title;
        if(!isRequired)this.title.setText(title);
    }
    public void setUnit(String unit){
        this.unit.setText(unit);
    }

    public void setInputType(int inputType){
        this.content.setInputType(inputType);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if(v==content){
            if(!hasFocus&&isRequired&&content.getText().toString().isEmpty()){
                title.setText(sb);
            }else {
                title.setText(titleText);
            }
        }
    }
}
