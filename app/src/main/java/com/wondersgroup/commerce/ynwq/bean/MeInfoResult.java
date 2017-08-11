package com.wondersgroup.commerce.ynwq.bean;

/**
 * Created by 薛定猫 on 2016/1/25.
 */
public class MeInfoResult {
    private String Message;
    private String resultCode;
    private MeInfo result;

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public MeInfo getResult() {
        return result;
    }

    public void setResult(MeInfo result) {
        this.result = result;
    }
}
