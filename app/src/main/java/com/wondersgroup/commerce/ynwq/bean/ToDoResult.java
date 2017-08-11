package com.wondersgroup.commerce.ynwq.bean;

import java.util.List;

/**
 * Created by 薛定猫 on 2015/12/29.
 */
public class ToDoResult {
    private String message;
    private String resultCode;
    private String totalRecord;
    private List<ToDoItem> result;

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

    public List<ToDoItem> getResult() {
        return result;
    }

    public String getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(String totalRecord) {
        this.totalRecord = totalRecord;
    }

    public void setResult(List<ToDoItem> result) {
        this.result = result;
    }
}
