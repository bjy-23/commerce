package com.wondersgroup.yngs.entity;

/**
 * Created by 薛定猫 on 2015/12/23.
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
