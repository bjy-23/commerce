package com.wondersgroup.commerce.model;

import java.util.List;

/**
 * Created by 薛定猫 on 2015/11/26.
 */
public class MsgItem {

    public static List<MsgItem> msgItems = null;

    private String appName;
    private String flowName;
    private String title;
    private String user;
    private String date;
    private String uuId;

    public MsgItem() {
    }

    public MsgItem(String appName, String flowName, String title, String user, String date) {
        this.appName = appName;
        this.flowName = flowName;
        this.title = title;
        this.user = user;
        this.date = date;
    }

    public String getUuId() {
        return uuId;
    }

    public void setUuId(String uuId) {
        this.uuId = uuId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getFlowName() {
        return flowName;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
