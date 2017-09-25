package com.wondersgroup.commerce.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bjy on 2017/9/18.
 */

public class AdvertisementBean {
    @SerializedName("")
    private String registerId;//广告发布登记编号

    @SerializedName("")
    private String unitName;//单位名称

    @SerializedName("")
    private String address;//单位地址

    @SerializedName("")
    private String person;//联系人

    @SerializedName("")
    private String tel;//联系电话

    public String getRegisterId() {
        return registerId;
    }

    public void setRegisterId(String registerId) {
        this.registerId = registerId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
