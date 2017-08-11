package com.wondersgroup.commerce.model;

/**
 * Created by admin on 2017/3/6.
 * 商标质押信息列表项
 */

public class TradeMarkPledgeVoList {

    private String pledgor;      //出质人
    private String pawnee;      //质权人
    private String reason;      //质押原因
    private String pledgeTime;       //质押期限
    private String notifyDate;    //质押公告日期

    public String getNotifyDate() {
        return notifyDate;
    }

    public void setNotifyDate(String notifyDate) {
        this.notifyDate = notifyDate;
    }

    public String getPawnee() {
        return pawnee;
    }

    public void setPawnee(String pawnee) {
        this.pawnee = pawnee;
    }

    public String getPledgeTime() {
        return pledgeTime;
    }

    public void setPledgeTime(String pledgeTime) {
        this.pledgeTime = pledgeTime;
    }

    public String getPledgor() {
        return pledgor;
    }

    public void setPledgor(String pledgor) {
        this.pledgor = pledgor;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
