package com.wondersgroup.commerce.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by yclli on 2015/11/23.
 */
public class ForScrollListView extends ListView {
    public ForScrollListView(Context context) {
        super(context);
    }

    public ForScrollListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ForScrollListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
