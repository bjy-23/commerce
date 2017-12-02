package com.wondersgroup.yngs.utils;

import android.app.Application;
import android.content.SharedPreferences;

import com.orhanobut.hawk.Hawk;
import com.orhanobut.hawk.HawkBuilder;
import com.orhanobut.hawk.LogLevel;
import com.wondersgroup.yngs.entity.CatalogFixBean;
import com.wondersgroup.yngs.entity.UserBean;
import com.wondersgroup.yngs.service.ApiManager;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by 薛定猫 on 2015/12/9.
 */
public class App extends Application {
    private UserBean userbean;
    private HashMap<String, String> dicEntTypeHashMap;
    private HashMap<String, ArrayList<CatalogFixBean>> dicLayoutConfigMap;

    public App(){

    }
    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferences sp=getSharedPreferences("Default",MODE_PRIVATE);
        String token=sp.getString("token", "");
        boolean isReRoot=sp.getBoolean("isReRoot",false);
        ApiManager.getInstance().init(token,isReRoot);
        Hawk.init(this)
                .setEncryptionMethod(HawkBuilder.EncryptionMethod.MEDIUM)
                .setStorage(HawkBuilder.newSharedPrefStorage(this))
                .setLogLevel(LogLevel.FULL)
                .build();
    }

    public UserBean getUserbean() {
        return userbean;
    }

    public void setUserbean(UserBean userbean) {
        this.userbean = userbean;
    }

    public HashMap<String, String> getDicEntTypeHashMap() {
        return dicEntTypeHashMap;
    }

    public void setDicEntTypeHashMap(HashMap<String, String> dicEntTypeHashMap) {
        this.dicEntTypeHashMap = dicEntTypeHashMap;
    }

    public HashMap<String, ArrayList<CatalogFixBean>> getDicLayoutConfigMap() {
        return dicLayoutConfigMap;
    }

    public void setDicLayoutConfigMap(
            HashMap<String, ArrayList<CatalogFixBean>> dicLayoutConfigMap) {
        this.dicLayoutConfigMap = dicLayoutConfigMap;
    }
}
