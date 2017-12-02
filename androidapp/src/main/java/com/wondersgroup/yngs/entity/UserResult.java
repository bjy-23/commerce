package com.wondersgroup.yngs.entity;

/**
 * Created by 薛定猫 on 2015/12/29.
 */
public class UserResult {
    private String message;
    private String resultCode;
    private User result;

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

    public User getResult() {
        return result;
    }

    public void setResult(User result) {
        this.result = result;
    }
}
