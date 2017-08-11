package com.wondersgroup.commerce.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.model.TodoMessageBean;
import com.wondersgroup.commerce.utils.DataShared;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import retrofit.Callback;

/**
 * Created by kangrenhui on 2015/12/12.
 */
public class MessageService extends Service {
    private DataShared dataShared;
    private static final int NOTIFICATION_FLAG = 1;
    private Timer mTimer;
    private MyTimerTask mTimerTask;

    private String userId;
    private String deptId;
    private String organId;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        dataShared = new DataShared(this);

        mTimer = new Timer();
        mTimerTask = new MyTimerTask(); // 新建一个任务
        mTimer.scheduleAtFixedRate(mTimerTask, 1000, 10 * 60 * 1000);
        //mTimer.scheduleAtFixedRate(mTimerTask, 1000, 30 * 1000);


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);
    }

    public void getData() {
        Log.i("data", "data");

        //android.os.Debug.waitForDebugger();


        userId = (String) dataShared.get("userId", "");
        deptId = (String) dataShared.get("deptId", "");
        organId = (String) dataShared.get("organId", "");
        Map<String, String> map = new HashMap<String, String>();
        map.put("wsCodeReq", "00000004");
        map.put("userId", userId);
        map.put("deptId", deptId);
        map.put("organId", organId);

    }

    private void createNotication(String msg) {
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        PendingIntent contentIntent = PendingIntent.getActivities(
                this,
                0,
                makeIntentStack(this),
                PendingIntent.FLAG_CANCEL_CURRENT);
        // 通过Notification.Builder来创建通知，注意API Level
        // API11之后才支持
        Notification notify2 = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.icons_logo) // 设置状态栏中的小图片，尺寸一般建议在24×24，这个图片同样也是在下拉状态栏中所显示，如果在那里需要更换更大的图片，可以使用setLargeIcon(Bitmap
                        // icon)
                .setTicker("河北工商:" + "您有未读待办，请注意查收！")// 设置在status
                        // bar上显示的提示文字
                .setContentTitle("待办提醒")// 设置在下拉status
                        // bar后Activity，本例子中的NotififyMessage的TextView中显示的标题
                .setContentText(msg)// TextView中显示的详细内容
                .setContentIntent(contentIntent) // 关联PendingIntent
                .setNumber(1) // 在TextView的右方显示的数字，可放大图片看，在最右侧。这个number同时也起到一个序列号的左右，如果多个触发多个通知（同一ID），可以指定显示哪一个。
                .getNotification(); // 需要注意build()是在API level
        // 16及之后增加的，在API11中可以使用getNotificatin()来代替
        notify2.flags |= Notification.FLAG_AUTO_CANCEL;
        manager.notify(NOTIFICATION_FLAG, notify2);
    }


    Intent[] makeIntentStack(Context context) {
        Intent[] intents = new Intent[2];
        intents[0] = Intent.makeRestartActivityTask(new ComponentName(context, com.wondersgroup.commerce.activity.MainActivity.class));

        return intents;
    }

    class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            getData();
        }
    }
}
