package com.wondersgroup.yngs.entity;

/**
 * Created by 薛定猫 on 2015/12/31.
 */
public class UpgradeResult {
    private String message;
    private String resultCode;
    private Upgrade result;

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

    public Upgrade getResult() {
        return result;
    }

    public void setResult(Upgrade result) {
        this.result = result;
    }
}
