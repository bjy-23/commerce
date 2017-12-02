package com.wondersgroup.yngs.entity;

/**
 * Created by 薛定猫 on 2016/1/26.
 */
public class TJProcessResult {
    private String message;
    private String resultCode;
    private TJProcessItem result;

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

    public TJProcessItem getResult() {
        return result;
    }

    public void setResult(TJProcessItem result) {
        this.result = result;
    }
}
