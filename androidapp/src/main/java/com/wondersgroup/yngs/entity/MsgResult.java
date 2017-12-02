package com.wondersgroup.yngs.entity;

/**
 * Created by 薛定猫 on 2016/1/11.
 */
public class MsgResult {
    private String message;
    private String resultCode;
    private TodoMsg result;

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

    public TodoMsg getResult() {
        return result;
    }

    public void setResult(TodoMsg result) {
        this.result = result;
    }
}
