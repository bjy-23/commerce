package com.wondersgroup.commerce.ynwq.bean;

import java.util.List;

/**
 * Created by 薛定猫 on 2015/12/30.
 */
public class JingBanResult {
    private String message;
    private String resultCode;
    private List<JingBanItem> result;

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

    public List<JingBanItem> getResult() {
        return result;
    }

    public void setResult(List<JingBanItem> result) {
        this.result = result;
    }
}
