package com.wondersgroup.yngs.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 薛定猫 on 2015/12/9.
 */
public class BurNingItem {
    @SerializedName("flowStatusName")
    private String title;
    @SerializedName("count")
    private String num;

    private String flowStatus;

    public String getFlowStatus() {
        return flowStatus;
    }

    public void setFlowStatus(String flowStatus) {
        this.flowStatus = flowStatus;
    }

    public BurNingItem() {
    }

    public BurNingItem(String title, String num) {

        this.title = title;
        this.num = num;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
