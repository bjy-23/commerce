package com.wondersgroup.yngs.entity;

import java.util.List;

/**
 * Created by 薛定猫 on 2015/12/30.
 */
public class XwComResult {
    private String message;
    private String resultCode;
    private String totalRecord;
    private List<XwComItem> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(String totalRecord) {
        this.totalRecord = totalRecord;
    }

    public List<XwComItem> getResult() {
        return result;
    }

    public void setResult(List<XwComItem> result) {
        this.result = result;
    }
}
