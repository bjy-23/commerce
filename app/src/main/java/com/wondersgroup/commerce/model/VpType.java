package com.wondersgroup.commerce.model;

/**
 * Created by 薛定猫 on 2015/12/23.
 */
public class VpType {
    private String title;
    private String type;
    private String viewType;

    public VpType() {
    }

    public VpType(String title, String type) {
        this.title = title;
        this.type = type;
    }

    public VpType(String title, String type, String viewType) {
        this.title = title;
        this.type = type;
        this.viewType = viewType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getViewType() {
        return viewType;
    }

    public void setViewType(String viewType) {
        this.viewType = viewType;
    }
}
