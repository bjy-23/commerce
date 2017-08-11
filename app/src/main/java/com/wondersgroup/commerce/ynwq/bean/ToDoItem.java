package com.wondersgroup.commerce.ynwq.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 薛定猫 on 2015/12/15.
 */
public class ToDoItem {
    @SerializedName("etpsName")
    private String title;

    private String regNo;

    @SerializedName("runAddress")
    private String addr;

    private String addrType;

    @SerializedName("appTypeName")
    private String type;

    @SerializedName("appDate")
    private String date;

    private String meId;
    private String appType;
    private String etpsId;


    public ToDoItem() {
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getEtpsId() {
        return etpsId;
    }

    public void setEtpsId(String etpsId) {
        this.etpsId = etpsId;
    }

    public String getMeId() {
        return meId;
    }

    public void setMeId(String meId) {
        this.meId = meId;
    }

    public ToDoItem(String title, String regNo, String addr, String type, String date) {
        this.title = title;
        this.regNo = regNo;
        this.addr = addr;
        this.type = type;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAddrType() {
        return addrType;
    }

    public void setAddrType(String addrType) {
        this.addrType = addrType;
    }
}
