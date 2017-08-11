package com.wondersgroup.commerce.model;

/**
 * Created by admin on 2017/3/6.
 * 注册商标列表项
 */

public class TradeMarkVoList {

    private String markName;      //商标名称
    private String markId;          //商标id
    private String registerNo;      //注册证号码
    private String markOwner;       //权利人名称
    private String ownerAddress;    //权利人地址
    private String dominationName;  //管辖机关名称
    private boolean isClaimed;      //是否认领

    public boolean isClaimed() {
        return isClaimed;
    }

    public void setClaimed(boolean claimed) {
        isClaimed = claimed;
    }

    public String getDominationName() {
        return dominationName;
    }

    public void setDominationName(String dominationName) {
        this.dominationName = dominationName;
    }

    public String getMarkId() {
        return markId;
    }

    public void setMarkId(String markId) {
        this.markId = markId;
    }

    public String getMarkName() {
        return markName;
    }

    public void setMarkName(String markName) {
        this.markName = markName;
    }

    public String getMarkOwner() {
        return markOwner;
    }

    public void setMarkOwner(String markOwner) {
        this.markOwner = markOwner;
    }

    public String getOwnerAddress() {
        return ownerAddress;
    }

    public void setOwnerAddress(String ownerAddress) {
        this.ownerAddress = ownerAddress;
    }

    public String getRegisterNo() {
        return registerNo;
    }

    public void setRegisterNo(String registerNo) {
        this.registerNo = registerNo;
    }
}
