package com.wondersgroup.commerce.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016/2/1.
 */
public class TradeMarkItemListBean {

    private String resultCode;
    private String message;
    private String totalRecord;
    private List<TradeMarkVoList> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<TradeMarkVoList> getResult() {
        return result;
    }

    public void setResult(List<TradeMarkVoList> result) {
        this.result = result;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(String totalRecord) {
        this.totalRecord = totalRecord;
    }
}
