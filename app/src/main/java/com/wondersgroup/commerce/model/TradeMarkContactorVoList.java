package com.wondersgroup.commerce.model;

/**
 * Created by admin on 2017/3/6.
 * 商标联络人信息列表项
 */

public class TradeMarkContactorVoList {

    private String contactorNumber;      //序号
    private String name;      //联络人名称
    private String telephone;      //联络人电话
    private String mobile;       //联络人手机
    private String address;    //联络人地址

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactorNumber() {
        return contactorNumber;
    }

    public void setContactorNumber(String contactorNumber) {
        this.contactorNumber = contactorNumber;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
