package com.wondersgroup.yngs.entity;

import java.util.List;

/**
 * Created by 薛定猫 on 2016/1/8.
 */
public class FuchiResult {
    private String message;
    private String resultCode;
    private String totalRecord;
    private List<FuchiItem> result;

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

    public List<FuchiItem> getResult() {
        return result;
    }

    public String getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(String totalRecord) {
        this.totalRecord = totalRecord;
    }

    public void setResult(List<FuchiItem> result) {
        this.result = result;
    }
}
