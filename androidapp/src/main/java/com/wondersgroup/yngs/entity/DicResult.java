package com.wondersgroup.yngs.entity;

/**
 * Created by 薛定猫 on 2016/1/12.
 */
public class DicResult {
    private String message;
    private String resultCode;
    private DicT result;

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

    public DicT getResult() {
        return result;
    }

    public void setResult(DicT result) {
        this.result = result;
    }
}
