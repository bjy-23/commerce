package com.wondersgroup.commerce.widget;

import android.app.Application;
import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.wondersgroup.commerce.application.RootAppcation;

/**
 * Created by bjy on 2017/9/14.
 */

public class MaxHeightView extends FrameLayout{
    private int maxHeight;
    private int widthMeasureSpec;
    private int heightMeasureSpec;

    public MaxHeightView(@NonNull Context context) {
        this(context, null);
    }

    public MaxHeightView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public void init(){
        maxHeight = RootAppcation.getInstance().getHeightPixels();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        this.widthMeasureSpec = widthMeasureSpec;
        this.heightMeasureSpec = heightMeasureSpec;
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        if (height < maxHeight){
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }else {
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(maxHeight, MeasureSpec.EXACTLY);
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int h = getMeasuredHeight();
        int w = getMaxHeight();
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
        int w = getMeasuredWidth();
        int h = getMeasuredHeight();
//        measure(getMeasuredWidth(), getMeasuredHeight());
    }
}
