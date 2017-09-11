package com.wondersgroup.commerce.utils;

import android.app.Application;
import android.telephony.TelephonyManager;
import com.wondersgroup.commerce.widget.MyProgressDialog;

import java.io.File;

/**
 * Created by yclli on 2015/11/19.
 */
public class CustomApplication extends Application {

    private String cachePath;
    private String IMEICode;
    private FileUtils fileUtils;

    @Override
    public void onCreate() {
        super.onCreate();
        cachePath = getApplicationContext().getExternalCacheDir().toString();
        IMEICode = ((TelephonyManager) getSystemService(TELEPHONY_SERVICE)).getDeviceId();
        File dic = new File(cachePath);
        fileUtils = new FileUtils();

        // 对话框初始状态
        MyProgressDialog.showFlag = false;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        fileUtils.deleteFilesByDirectory(new File(cachePath));
    }

    public String getCachePath(){
        return cachePath;
    }

    public String getIMEICode() {
        return IMEICode;
    }
}