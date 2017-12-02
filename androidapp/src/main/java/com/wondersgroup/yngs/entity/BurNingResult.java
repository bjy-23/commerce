package com.wondersgroup.yngs.entity;

import java.util.List;

/**
 * Created by 薛定猫 on 2015/12/29.
 */
public class BurNingResult {
    private String message;
    private String resultCode;
    private List<BurNingItem> result;

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

    public List<BurNingItem> getResult() {
        return result;
    }

    public void setResult(List<BurNingItem> result) {
        this.result = result;
    }
}
