package com.wondersgroup.commerce.teamwork.memorandum;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.wondersgroup.commerce.R;


/**
 * Created by 1229 on 2015/12/1.
 */
public class AlarmActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //闹钟提示音
        final MediaPlayer mpMediaPlayer = MediaPlayer.create(AlarmActivity.this, R.raw.song2);
        mpMediaPlayer.start();

        //显示对话框
        new AlertDialog.Builder(AlarmActivity.this).
                setTitle("闹钟").//设置标题
                setMessage("您有备忘录需要查看！").//设置内容
                setPositiveButton("知道了", new DialogInterface.OnClickListener(){//设置按钮
            public void onClick(DialogInterface dialog, int which) {
                AlarmActivity.this.finish();//关闭Activity
                mpMediaPlayer.stop();
            }
        }).create().show();
    }
}
