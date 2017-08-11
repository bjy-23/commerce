package com.wondersgroup.commerce.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RadioButton;

/**
 * Created by bjy on 2017/4/27.
 */

public class ClearRadioButton extends android.support.v7.widget.AppCompatRadioButton {

    public ClearRadioButton(Context context) {
        this(context,null);
    }

    public ClearRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setChecked(boolean checked) {
        if (isChecked())
            checked = false;
        super.setChecked(checked);
    }

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean b = isChecked();
        return super.onTouchEvent(event);
    }
}
