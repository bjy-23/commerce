package com.wondersgroup.commerce.model;

import java.io.Serializable;

/**
 * Created by Lee on 2016/1/29.
 * 案件明细DTO
 */
public class CaseInvestigateDTO implements Serializable {

    private String name;        //案件名称
    private String number;      //案件编号
    private String date;        //立案日期

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
