package com.wondersgroup.commerce.ynwq.bean;

import com.wondersgroup.commerce.model.ccjc.DicItem;

import java.util.List;

/**
 * Created by 薛定猫 on 2016/1/26.
 */
public class ChildOrganResult {
    private String message;
    private String resultCode;
    private List<DicItem> result;

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

    public List<DicItem> getResult() {
        return result;
    }

    public void setResult(List<DicItem> result) {
        this.result = result;
    }
}
