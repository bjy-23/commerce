package com.wondersgroup.commerce.utils;

import android.content.Context;
import android.util.TypedValue;

import com.wondersgroup.commerce.application.RootAppcation;

/**
 * Created by 薛定猫 on 2016/3/4.
 * 单位转换
 */
public class DWZH {
    public static float dp2pt(Context context,int dp){
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }

    public static int dp(int dp){
        return (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, RootAppcation.getInstance().getResources().getDisplayMetrics());
    }
}
