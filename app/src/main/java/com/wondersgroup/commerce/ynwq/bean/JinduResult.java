package com.wondersgroup.commerce.ynwq.bean;

import java.util.List;

/**
 * Created by 薛定猫 on 2015/12/30.
 */
public class JinduResult {
    private String message;
    private String resultCode;
    private String totalRecord;
    private List<JinduItem> result;

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

    public List<JinduItem> getResult() {
        return result;
    }

    public String getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(String totalRecord) {
        this.totalRecord = totalRecord;
    }

    public void setResult(List<JinduItem> result) {
        this.result = result;
    }
}
