package com.wondersgroup.commerce.ynwq.bean;

import java.util.List;

/**
 * Created by 薛定猫 on 2016/2/1.
 */
public class StatResult {
    private String message;
    private String resultCode;
    private String totalRecord;
    private List<StatItem> result;

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

    public List<StatItem> getResult() {
        return result;
    }

    public String getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(String totalRecord) {
        this.totalRecord = totalRecord;
    }

    public void setResult(List<StatItem> result) {
        this.result = result;
    }
}
