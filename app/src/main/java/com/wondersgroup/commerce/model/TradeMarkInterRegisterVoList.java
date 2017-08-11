package com.wondersgroup.commerce.model;

/**
 * Created by admin on 2017/3/6.
 * 国际注册信息列表项
 */

public class TradeMarkInterRegisterVoList {

    private String number;      //序号
    private String registerName;          //国际注册名称
    private String registerNo;      //国际注册证号码
    private String typeNo;       //国际分类号
    private String registerWay;    //注册途径
    private String registerCountry;  //注册国家
    private String registerDate;    //注册日期

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getRegisterCountry() {
        return registerCountry;
    }

    public void setRegisterCountry(String registerCountry) {
        this.registerCountry = registerCountry;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getRegisterName() {
        return registerName;
    }

    public void setRegisterName(String registerName) {
        this.registerName = registerName;
    }

    public String getRegisterNo() {
        return registerNo;
    }

    public void setRegisterNo(String registerNo) {
        this.registerNo = registerNo;
    }

    public String getRegisterWay() {
        return registerWay;
    }

    public void setRegisterWay(String registerWay) {
        this.registerWay = registerWay;
    }

    public String getTypeNo() {
        return typeNo;
    }

    public void setTypeNo(String typeNo) {
        this.typeNo = typeNo;
    }
}
