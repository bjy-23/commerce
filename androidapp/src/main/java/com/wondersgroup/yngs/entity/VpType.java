package com.wondersgroup.yngs.entity;

/**
 * Created by 薛定猫 on 2015/12/23.
 */
public class VpType {
    private String title;
    private String type;

    public VpType() {
    }

    public VpType(String title, String type) {
        this.title = title;
        this.type = type;
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
}
