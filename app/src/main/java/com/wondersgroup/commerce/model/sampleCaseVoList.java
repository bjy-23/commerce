package com.wondersgroup.commerce.model;

/**
 * Created by admin on 2017/3/6.
 * 简易程序案件列表项
 */

public class sampleCaseVoList {

    private String clueNo;      //线索号
    private String caseName;        //案件名称
    private String litigtName;  //当事人
    private String casefiDate; //处罚日期
    private String userIdMainName; //承办人
    private String status;          //0不能修改（当前登录人员不是办案人员 ） ,1可以修改

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getClueNo() {
        return clueNo;
    }

    public void setClueNo(String clueNo) {
        this.clueNo = clueNo;
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public String getLitigtName() {
        return litigtName;
    }

    public void setLitigtName(String litigtName) {
        this.litigtName = litigtName;
    }

    public String getCasefiDate() {
        return casefiDate;
    }

    public void setCasefiDate(String casefiDate) {
        this.casefiDate = casefiDate;
    }

    public String getUserIdMainName() {
        return userIdMainName;
    }

    public void setUserIdMainName(String userIdMainName) {
        this.userIdMainName = userIdMainName;
    }
}
