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
    public static final String FORMATE_YMD = "yyyy-MM-dd";
    public static final String FORMATE_YMDHM = "yyyy年MM月dd日  HH时mm分";
    public static final String FORMATE_YMDHMS = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMATE_YY_MM_DD = "yyyy年MM月dd日";
    public static final String FORMAT_YY_MM_DD_HH_MM_SS = "yy-MM-dd HH:mm:ss";
    public static final String FORMAT_HH_MM = "HH:mm";
    public static final String FORMAT_MM_DD = "MM:dd";
    private static DateListener dateListener;

    public static Date getDate(String dateFormat, String time){
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Date date = null;
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }finally {
            if (date == null)
                date = new Date();
            return date;
        }
    }

    public static String getTime(String dateFormat, Date date){
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(date);
    }

    public static boolean isToday(Date date){
        Date dateNow = new Date();
        if (date.getYear() == dateNow.getYear()
                && date.getMonth() == dateNow.getMonth()
                && date.getDate() == dateNow.getDate())
            return true;

        return false;
    }

    public static boolean isYestoday(Date date){
        Date dateNow = new Date();
        if (date.getYear() == dateNow.getYear()
                && date.getMonth() == dateNow.getMonth()
                && (date.getDate() - dateNow.getDate() ==1))
            return true;
        else if (date.getYear() == dateNow.getYear()
                && dateNow.getMonth() - date.getMonth() == 1
                && isFirstDayOfMonth(dateNow)
                && isLastDayOfMonth(date))
            return true;
        else if (dateNow.getYear() - date.getYear() == 1
                && isFirstMonthOfYear(dateNow)
                && isFirstDayOfMonth(dateNow)
                && isLastMonthOfYear(date)
                && isLastDayOfMonth(date))
            return true;

        return false;
    }

    public static boolean isFirstDayOfMonth(Date date){
        if (date.getDate() == 1)
            return true;
        return false;
    }

    public static boolean isLastDayOfMonth(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int max = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        if (max == date.getDate())
            return true;
        return false;
    }

    public static boolean isFirstMonthOfYear(Date date){
        if (date.getMonth() == 0)
            return true;
        return false;
    }

    public static boolean isLastMonthOfYear(Date date){
        if (date.getMonth() == 11)
            return true;
        return false;
    }

    public static boolean isThisYear(Date date){
        if (date.getYear() == getNowYear())
            return true;
        return false;
    }

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

    public static void createDatePicker(Activity activity, final DateListener dateListener){
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
                if (dateListener != null){
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.YEAR, pickerYear.getValue());
                    calendar.set(Calendar.MONTH, pickerMonth.getValue() - 1);
                    calendar.set(Calendar.DAY_OF_MONTH, pickerDay.getValue());
                    dateListener.back(calendar.getTime());
                }
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

    public interface DateListener{
        void back(Date date);
    }

    public void setDateListener(DateListener dateListener) {
        this.dateListener = dateListener;
    }
}
