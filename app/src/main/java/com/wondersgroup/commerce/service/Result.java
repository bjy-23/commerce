package com.wondersgroup.commerce.service;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bjy on 2017/5/2.
 */

public class Result<T> {
    @SerializedName(value = "code",alternate = {})
    private int code;
    @SerializedName(value = "message",alternate = {})
    private String message;
    @SerializedName(value = "object",alternate = {"busCatelogMap", "codeList", "punishList", "result", "list"})
    private T object;
    @SerializedName(value = "totalRecord")
    private int totalRecord;
    @SerializedName(value = "pageCount")
    private int pageCount;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
}
