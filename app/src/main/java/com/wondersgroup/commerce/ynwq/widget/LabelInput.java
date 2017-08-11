package com.wondersgroup.commerce.ynwq.widget;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wondersgroup.commerce.R;


/**
 * Created by 薛定猫 on 2016/1/19.
 */
public class LabelInput extends RelativeLayout implements View.OnFocusChangeListener{
    TextView title;
    EditText content;
    private boolean isRequired=false;
    private SpannableString sb;
    private String titleText;
    public LabelInput(Context context) {
        super(context);
        init(context);
    }

    public LabelInput(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LabelInput(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    private void init(Context context){
        View.inflate(context, R.layout.view_label_input,this);
        title=(TextView)findViewById(R.id.label_input_title);
        content=(EditText)findViewById(R.id.label_input_content);
        //content.setOnFocusChangeListener(this);
    }
    public void isRequired(boolean isRequired){
        this.isRequired=isRequired;
        sb=new SpannableString(title.getText().toString()+"*");
        sb.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getContext(),R.color.red)),sb.length()-1,sb.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        title.setText(sb);
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
    public void setInputType(int type){
        content.setInputType(type);
    }
    public void setContent(String content){
        this.content.setText(content);
    }
    public void setContentClick(OnClickListener listener){
        content.setFocusable(false);
        content.setInputType(InputType.TYPE_NULL);
        content.setOnClickListener(listener);
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
