package com.wondersgroup.commerce.teamwork.memorandum;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.activity.BaseActivity;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.model.Memo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by 1229 on 2016/3/21.
 */
public class MemoEditActivity extends BaseActivity {
    private TextView tvDelete;
    private LinearLayout layoutAddClock;
    private EditText edittext;
    private TextView tvAddClock;
    private String type;
    private String userId;
    private String memo_content;
    private String memo_time;
    private String memo_time0;
    private String remind_time0;
    private String remind_time;
    private ImageView imgClock;
    private DbHelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addContentView(R.layout.activity_memo_edit);

        tvTitle.setText(Constants.NEW_MEMO);
        tvOption.setVisibility(View.VISIBLE);
        tvOption.setText(Constants.SAVE);
        tvOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Constants.ADD.equals(type)){
                    savememo();
                }else {
                    updatememo();
                }
            }
        });

        dbhelper = DbHelper.create();

        //获取控件
        edittext = (EditText) findViewById(R.id.edit);
        layoutAddClock = (LinearLayout) findViewById(R.id.layout_add_clock);
        tvAddClock = (TextView) findViewById(R.id.tv_add_clock);
        tvAddClock.setText(Constants.ADD_CLOCK);
        imgClock = (ImageView) findViewById(R.id.img_clock);
        tvDelete = (TextView) findViewById(R.id.tv_delete);

        userId =getIntent().getStringExtra(Constants.USER_ID);
        type = getIntent().getStringExtra(Constants.TYPE);
        if(Constants.EDIT.equals(type)){
            String str2 = getIntent().getStringExtra(Constants.TIME_CLOCK);

            edittext.setText(getIntent().getStringExtra(Constants.MEMO_CONTENT));
            if(!str2.equals("")){
                imgClock.setImageResource(R.drawable.clock1);
                layoutAddClock.setBackgroundResource(R.drawable.rect_corner_orange);
                tvDelete.setVisibility(View.VISIBLE);
                tvAddClock.setText(str2);
            }
        }

        //删除提醒时间
        tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remind_time0 = null;
                tvAddClock.setText(Constants.ADD_CLOCK);
                tvAddClock.setTextColor(getResources().getColor(R.color.black));
                layoutAddClock.setBackgroundResource(R.drawable.rect_corner_white);
                imgClock.setImageResource(R.drawable.clock2);
                tvDelete.setVisibility(View.GONE);
                Intent intent = new Intent(MemoEditActivity.this,AlarmReceiver.class);
                PendingIntent pi = PendingIntent.getBroadcast(MemoEditActivity.this, 0, intent, 0);
                AlarmManager am = (AlarmManager) getSystemService(Activity.ALARM_SERVICE);
                am.cancel(pi);
            }
        });

        //设置提醒时间
        layoutAddClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(MemoEditActivity.this);
                dialog.setContentView(R.layout.dialog_time);
                dialog.show();
                //点击取消按钮
                Button btnNo = (Button) dialog.findViewById(R.id.btn_no);
                btnNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                //
                Button btnYes = (Button) dialog.findViewById(R.id.btn_yes);
                btnYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatePicker dp = (DatePicker) dialog.findViewById(R.id.datepicker);
                        int year = dp.getYear();
                        int month = dp.getMonth()+1;
                        int day = dp.getDayOfMonth();

                        TimePicker tp = (TimePicker) dialog.findViewById(R.id.timepicker);
                        int hour = tp.getCurrentHour();
                        String minute = "";
                        if (tp.getCurrentMinute()<10){
                            minute = "0"+tp.getCurrentMinute();
                        }else{
                            minute =  tp.getCurrentMinute() + "";
                        }

                        remind_time0 = (year+"-"+month+"-"+day+"  "+hour+":"+minute);
                        tvAddClock.setText(remind_time0);
                        tvAddClock.setTextColor(getResources().getColor(R.color.white));
                        layoutAddClock.setBackgroundResource(R.drawable.rect_corner_orange);
                        imgClock.setImageResource(R.drawable.clock1);
                        tvDelete.setVisibility(View.VISIBLE);

                        //设置闹钟
                        Calendar c = Calendar.getInstance();
                        c.set(Calendar.YEAR,year);
                        c.set(Calendar.MONTH,dp.getMonth());
                        c.set(Calendar.DAY_OF_MONTH,day);
                        c.set(Calendar.HOUR_OF_DAY,hour);
                        c.set(Calendar.MINUTE, tp.getCurrentMinute());
                        c.set(Calendar.SECOND,0);
                        c.set(Calendar.MILLISECOND,0);

                        Intent intent = new Intent(MemoEditActivity.this,AlarmReceiver.class);
                        PendingIntent pi = PendingIntent.getBroadcast(MemoEditActivity.this, 0, intent, 0);
                        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                        am.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pi);
                        dialog.dismiss();
                    }
                });
            }
        });
    }


    public void savememo(){
        memo_content = String.valueOf(edittext.getText());
        //获取保存记录的时间
        Date date=new Date();
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        DateFormat format0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        memo_time = format.format(date);
        memo_time0 = format0.format(date);
        remind_time = remind_time0;
        Memo memo = new Memo();
        memo.setUser_id(userId);
        memo.setMemo_content(memo_content);
        memo.setMemo_time(memo_time);
        memo.setMemo_time0(memo_time0);
        memo.setRemind_time(remind_time);

        dbhelper.insertMemoTable(memo);

        finish();
    }

    public void updatememo(){
        memo_time0 = getIntent().getStringExtra("time0");
        Date date=new Date();
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        memo_time = format.format(date);
        memo_content = String.valueOf(edittext.getText());
        if(Constants.ADD_CLOCK.equals(tvAddClock.getText())){
            remind_time = null;
        }else{
            remind_time =(String) tvAddClock.getText();
        }

        Memo memo = new Memo();
        memo.setMemo_content(memo_content);
        memo.setMemo_time(memo_time);
        memo.setMemo_time0(memo_time0);
        memo.setRemind_time(remind_time);

        dbhelper.update_memo(memo);

        finish();
    }
}



