package com.wondersgroup.commerce.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by kangrenhui on 2016/2/23.
 */
public class TimeHelper {
    public static String createTimeString() {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy/MM/dd HH:mm:ss");
        String hehe = dateFormat.format(now);

        Calendar c = Calendar.getInstance();// 可以对每个时间域单独修改

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int date = c.get(Calendar.DATE);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);

        return year + "_" + month + "_" + date + "_" + hour + "_" + minute
                + "_" + second;
    }

    public static String getSystemTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日    HH:mm:ss     ");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);

        return str;
    }
}
