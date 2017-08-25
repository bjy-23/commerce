package com.wondersgroup.commerce.teamwork.statistics.bean;

import com.google.gson.JsonElement;

/**
 * Created by root on 8/24/17.
 */

public class Reason {
    private int code;
    private String message;
    private JsonElement result;

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

    public JsonElement getResult() {
        return result;
    }

    public void setResult(JsonElement result) {
        this.result = result;
    }
}
