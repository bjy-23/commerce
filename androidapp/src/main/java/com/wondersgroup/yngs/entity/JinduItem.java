package com.wondersgroup.yngs.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 薛定猫 on 2015/12/25.
 */
public class JinduItem {
    @SerializedName("etpsName")
    private String title;

    private String regNo;

    private String addr;

    private String addrType;

    @SerializedName("appTypeName")
    private String type;

    @SerializedName("appDate")
    private String date;

    @SerializedName("flowStatusName")
    private String status;

    @SerializedName("flowStatus")
    private String statusCode;

    public JinduItem() {
    }

    public JinduItem(String title, String regNo, String addr, String addrType, String type, String date, String status) {
        this.title = title;
        this.regNo = regNo;
        this.addr = addr;
        this.addrType = addrType;
        this.type = type;
        this.date = date;
        this.status = status;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getAddrType() {
        return addrType;
    }

    public void setAddrType(String addrType) {
        this.addrType = addrType;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
