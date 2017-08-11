package com.wondersgroup.commerce.ynwq.bean;

import java.util.List;

/**
 * Created by 薛定猫 on 2015/12/30.
 */
public class XiaoWeiResult {
    private String message;
    private String resultCode;
    private List<XiaoWeiItem> result;

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

    public List<XiaoWeiItem> getResult() {
        return result;
    }

    public void setResult(List<XiaoWeiItem> result) {
        this.result = result;
    }
}
