package com.wondersgroup.yngs.entity;

/**
 * Created by 薛定猫 on 2016/1/26.
 */
public class TJResult {
    private String message;
    private String resultCode;
    private TJItem result;

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

    public TJItem getResult() {
        return result;
    }

    public void setResult(TJItem result) {
        this.result = result;
    }
}