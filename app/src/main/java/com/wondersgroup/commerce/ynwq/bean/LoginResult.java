package com.wondersgroup.commerce.ynwq.bean;

/**
 * Created by bjy on 2017/3/17.
 */

public class LoginResult {
    private String message;
    private String resultCode;
    private LoginRes result;

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

    public LoginRes getResult() {
        return result;
    }

    public void setResult(LoginRes result) {
        this.result = result;
    }
}
