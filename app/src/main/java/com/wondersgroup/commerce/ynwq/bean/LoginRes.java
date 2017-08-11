package com.wondersgroup.commerce.ynwq.bean;

/**
 * Created by bjy on 2017/3/17.
 */

public class LoginRes {
    private String token;
    private String userId;
    private String type;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
