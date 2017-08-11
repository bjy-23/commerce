package com.wondersgroup.commerce.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.wondersgroup.commerce.R;

/**
 * Created by bjy on 2017/4/24.
 */

public class ClearEditText extends android.support.v7.widget.AppCompatEditText implements TextWatcher{
    private Drawable drawableRight;
    private boolean isShow;

    public ClearEditText(Context context) {
        this(context,null);
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    private void init(){
        drawableRight = getResources().getDrawable(R.mipmap.del);
        drawableRight.setBounds(0,0,drawableRight.getIntrinsicWidth(),drawableRight.getIntrinsicHeight());

        addTextChangedListener(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_UP){
            if (getCompoundDrawables()[2]!=null){
                boolean isClicked = (event.getX() > (getWidth() - getTotalPaddingRight()))
                        && (event.getX() < (getWidth() - getPaddingRight()));
                if (isClicked)
                    this.setText(null);
            }
        }

        return super.onTouchEvent(event);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (TextUtils.isEmpty(String.valueOf(s))){
            if (isShow){
                setCompoundDrawables(getCompoundDrawables()[0],getCompoundDrawables()[1]
                        ,null,getCompoundDrawables()[3]);

                isShow = false;
            }
        }else {
            if (!isShow){
                setCompoundDrawables(getCompoundDrawables()[0],getCompoundDrawables()[1]
                        ,drawableRight,getCompoundDrawables()[3]);

                isShow = true;
            }
        }
    }
}
