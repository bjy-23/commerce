package com.wondersgroup.yngs.entity;

import android.app.Application;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by 1229 on 2015/12/29.
 */
public class AppData extends Application {
    private UserBean userbean;
    private HashMap<String, String> dicEntTypeHashMap;
    private HashMap<String, ArrayList<CatalogFixBean>> dicLayoutConfigMap;



    public AppData() {

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

