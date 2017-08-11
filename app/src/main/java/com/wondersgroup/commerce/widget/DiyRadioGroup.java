package com.wondersgroup.commerce.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bjy on 2017/5/3.
 */

public class DiyRadioGroup  extends RadioGroup {
    private int columnNumber = 3;

    /**
     * 横向间距
     */
    private int childMarginHorizontal = 20;
    /**
     * 纵向间距
     */
    private int childMarginVertical = 20;

    private int radioButtonWidth;
    private int radioButtonHeight;


    public DiyRadioGroup(Context context) {
        super(context);
    }

    public DiyRadioGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (getChildCount()>0){
            //计算每个radiobutton宽度
            radioButtonWidth = (getMeasuredWidth()-getPaddingRight()-getPaddingLeft()-(columnNumber-1)*childMarginHorizontal)/columnNumber;
            radioButtonHeight = getChildAt(0).getMeasuredHeight();
            for (int i=0;i<getChildCount();i++){
                RadioButton radioButton = (RadioButton) getChildAt(i);
                radioButton.measure(radioButtonWidth,radioButtonHeight);
                radioButton.setWidth(radioButtonWidth);
            }

            int x = (getChildCount() % columnNumber) == 0 ? (getChildCount() / columnNumber) : (getChildCount() / columnNumber) +1;

            setMeasuredDimension(getMeasuredWidth(),x*radioButtonHeight+(x-1)*childMarginVertical);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        for (int i=0;i<getChildCount();i++){
            int num1 = i / columnNumber;
            int num2 = i % columnNumber;
            RadioButton radioButton = (RadioButton) getChildAt(i);
            radioButton.layout(num2*(radioButtonWidth+childMarginHorizontal),num1*(radioButtonHeight+childMarginVertical)
                    ,num2*(radioButtonWidth+childMarginHorizontal)+radioButtonWidth,num1*(radioButtonHeight+childMarginVertical)+radioButtonHeight);
        }
    }

    public int getColumnNumber() {
        return columnNumber;
    }

    public void setColumnNumber(int columnNumber) {
        this.columnNumber = columnNumber;
    }
}
