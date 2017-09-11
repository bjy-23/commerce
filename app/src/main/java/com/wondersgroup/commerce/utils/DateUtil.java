package com.wondersgroup.commerce.utils;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.wondersgroup.commerce.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by bjy on 2017/3/23.
 */

public class DateUtil {
    private static final String FORMATE_YMD = "yyyy-MM-dd";
    private static final String FORMATE_YMDHM = "yyyy年MM月dd日  HH时mm分";
    private static final String FORMATE_YMDHMS = "yyyy-MM-dd HH:mm:ss";

    public static String getYMD(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMATE_YMD);

        return simpleDateFormat.format(date);
    }

    public static String getYMDHM(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMATE_YMDHM);

        return simpleDateFormat.format(date);
    }

    public static String getYMDHM(String time){
        SimpleDateFormat format1 = new SimpleDateFormat(FORMATE_YMDHMS);
        Date date = null;
        try {
            date = format1.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat format2 = new SimpleDateFormat(FORMATE_YMDHM);

        return format2.format(date);
    }

    public static int getNowYear(){
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    public static int getNowMonth(){
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH)+1;
    }

    public static int getNowDay(){
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static void createDatePicker(Activity activity, final TextView textView){
        View view  = View.inflate(activity, R.layout.date_picker,null);
        final PopupWindow popupWindowDatePicker = new PopupWindow(view,ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,true);
        popupWindowDatePicker.showAtLocation(activity.getWindow().getDecorView(), Gravity.BOTTOM,0,0);
        popupWindowDatePicker.setAnimationStyle(R.style.popup_animation_1);
        final NumberPicker pickerYear = (NumberPicker) view.findViewById(R.id.picker_year);
        final NumberPicker pickerMonth = (NumberPicker) view.findViewById(R.id.picker_month);
        final NumberPicker pickerDay = (NumberPicker) view.findViewById(R.id.picker_day);
        TextView tvDateNo = (TextView) view.findViewById(R.id.tv_date_no);
        tvDateNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindowDatePicker.dismiss();
            }
        });
        TextView tvDateOk = (TextView) view.findViewById(R.id.tv_date_ok);
        tvDateOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindowDatePicker.dismiss();
                textView.setText(pickerYear.getValue()+"-"+changeTime(pickerMonth.getValue())+"-"+changeTime(pickerDay.getValue()));
            }
        });
        pickerYear.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
        pickerYear.setMaxValue(3017);
        pickerYear.setMinValue(0);
        pickerYear.setValue(getNowYear());
        pickerMonth.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
        pickerMonth.setMaxValue(12);
        pickerMonth.setMinValue(1);
        pickerMonth.setValue(getNowMonth());
        pickerDay.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
        pickerDay.setMaxValue(31);
        pickerDay.setMinValue(1);
        pickerDay.setValue(getNowDay());

        View viewExtra = view.findViewById(R.id.v_extra);
        viewExtra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindowDatePicker.dismiss();
            }
        });

    }

    private static String changeTime(int time){
        if (time < 10)
            return "0" + time;
        return time+"";
    }
}
