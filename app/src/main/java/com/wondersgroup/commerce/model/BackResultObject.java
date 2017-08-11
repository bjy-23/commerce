package com.wondersgroup.commerce.model;

/**
 * Created by Lee on 2016/2/18.
 * 从服务器返回不带参数的通用结果
 */
public class BackResultObject {
    private String code;        //状态码
    private String message;     //状态描述

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
