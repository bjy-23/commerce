package com.wondersgroup.commerce.model;

import java.util.List;

/**
 * Created by admin on 2016/2/1.
 */
public class TradeMarkDetailBean {

    private String resultCode;
    private String message;
    private TradeMarkDetail result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public TradeMarkDetail getResult() {
        return result;
    }

    public void setResult(TradeMarkDetail result) {
        this.result = result;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }
}
