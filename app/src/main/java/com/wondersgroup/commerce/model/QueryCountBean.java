package com.wondersgroup.commerce.model;

/**
 * Created by bjy on 2017/6/28.
 */

public class QueryCountBean {
    private int resId;
    private String name;
    private String url;

    public QueryCountBean(int resId, String name, String url) {
        this.resId = resId;
        this.name = name;
        this.url = url;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
