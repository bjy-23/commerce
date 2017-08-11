package com.wondersgroup.commerce.activity;

import android.app.AlertDialog;
import android.app.Application;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;

import com.wondersgroup.commerce.application.RootAppcation;

/**
 * Created by kangrenhui on 2016/1/27.
 */
public class RootActivity extends AppCompatActivity {
    protected Context mContext = null;
    // 一个自己的application类
    protected RootAppcation myApplication;
    protected ProgressDialog pg = null;
    protected NotificationManager notificationManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        pg = new ProgressDialog(mContext);
        myApplication = (RootAppcation) getApplication();
    }

    /**
     * 得到一个application对象
     *
     * @return
     */
    private Application getMyApplication() {
        return myApplication;
    }

    ;

    /**
     * 开启服务
     */
    public void startService() {

    }

    ;

    /**
     * 停止服务
     */
    public void stopService() {

    }

    ;

    /**
     * 判断是否有网络，若没有，则弹出网络设置对话框，返回false
     */
    public boolean validateInternet() {
        return true;
    }

    ;

    /**
     * 判断是否有网络，若没有返回false
     */
    public boolean hasInternetConnected() {
        ConnectivityManager manager = (ConnectivityManager) mContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (manager != null) {
            NetworkInfo network = manager.getActiveNetworkInfo();
            if (network != null && network.isConnectedOrConnecting()) {
                return true;
            }
        }

        return false;
    }

    ;

    /**
     * 退出应用
     */
    public void isExit() {
        // 将所有开启的activity保存在application里面
        // 调用application.exit();
    }

    ;

    /**
     * 判断GPS是否开启
     */
    public boolean hasLocationGPS() {
        LocationManager manager = (LocationManager) mContext
                .getSystemService(Context.LOCATION_SERVICE);
        if (manager
                .isProviderEnabled(android.location.LocationManager.GPS_PROVIDER)) {
            return true;
        } else {
            return false;
        }
    }

    ;

    /**
     * 判断基站是否已经开启
     */
    public boolean hasLocationNetWork() {
        LocationManager manager = (LocationManager) mContext
                .getSystemService(Context.LOCATION_SERVICE);
        if (manager
                .isProviderEnabled(android.location.LocationManager.NETWORK_PROVIDER)) {
            return true;
        } else {
            return false;
        }
    }

    ;

    /**
     * 检查内存卡
     */
    public void checkMemoryCard() {
        if (!Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState())) {
            new AlertDialog.Builder(mContext)
                    .setTitle("检测内存卡")
                    .setMessage("请检查内存卡")
                    .setPositiveButton("设置",
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    dialog.cancel();
                                    Intent intent = new Intent(
                                            Settings.ACTION_SETTINGS);
                                    mContext.startActivity(intent);
                                }

                            }).setNegativeButton("退出",

                    new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog,

                                            int which) {
                            dialog.cancel();
                        }
                    }).create().show();
        }
    }

    /**
     * 打开网络连接设置
     */
    public void openWirelessSet() {
        Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
        mContext.startActivity(intent);
    }

    /**
     * 获取进度条
     */
    public ProgressDialog getProgressDialog() {
        return pg;
    }

    ;

    /**
     * 返回当前上下文
     */
    public Context getContext() {
        return mContext;
    }

    ;


    /**
     * 用户是否在线（网络连接是否成功，session是否有效）
     */
    public boolean getUserOnlineState() {
        return false;
    }

    ;

    /**
     * @param iconId
     * @param contentTitle
     * @param contextText
     * @param activity
     * @param from
     */
    public void PushNotification(int iconId, String contentTitle,
                                 String contextText, Class<?> activity, String from) {

    }

    ;
}
