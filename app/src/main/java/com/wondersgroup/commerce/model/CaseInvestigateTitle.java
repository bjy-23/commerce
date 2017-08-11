package com.wondersgroup.commerce.model;

import java.io.Serializable;

/**
 * Created by Lee on 2016/2/1.
 */
public class CaseInvestigateTitle implements Serializable {

    private String caseNo;
    private String caseName;
    private String caseFidate;
    private String clueNo;
    private String appStatus;

    public String getAppStatus() {
        return appStatus;
    }

    public void setAppStatus(String appStatus) {
        this.appStatus = appStatus;
    }

    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public String getCaseFidate() {
        return caseFidate;
    }

    public void setCaseFidate(String caseFidate) {
        this.caseFidate = caseFidate;
    }

    public String getClueNo() {
        return clueNo;
    }

    public void setClueNo(String clueNo) {
        this.clueNo = clueNo;
    }
}
