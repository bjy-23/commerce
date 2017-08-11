package com.wondersgroup.commerce.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

/**
 * Created by bjy on 2017/5/25.
 *
 * 弹性ScrollView(上下滑动)
 */

public class ElasticScrollView extends ScrollView{
    private View childView;

    private float oldY;
    private float newY;

    public ElasticScrollView(Context context) {
        this(context,null);
    }

    public ElasticScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    private void init(){

    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
    }

    /*
    * 布局加载完成
    * */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount()>0){
            childView = getChildAt(0);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                oldY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (scrollToTopBottom()){
                    newY = ev.getY();
                    float deltaY = newY - oldY;
                    setTranslationY(deltaY);
                }
                break;
            case MotionEvent.ACTION_UP:
                float transY = getTranslationY();
                setTranslationY(-transY);
                break;
        }
        return super.onTouchEvent(ev);
    }

    /*
    * 是否滑动到了顶部或者底部
    * */
    private boolean scrollToTopBottom(){
        if(childView.getHeight() - getHeight() == getScrollY() || getScrollY() == 0){
            return true;
        }

        return false;
    }
}
