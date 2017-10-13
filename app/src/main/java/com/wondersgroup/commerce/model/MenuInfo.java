package com.wondersgroup.commerce.model;

import com.wondersgroup.commerce.interface_.Data;

import java.util.List;

/**
 * Created by bjy on 2017/6/28.
 */

public class MenuInfo extends Data {
    private String title;
    private List<MenuBean> menus;
    private boolean isShow;

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<MenuBean> getMenus() {
        return menus;
    }

    public void setMenus(List<MenuBean> menus) {
        this.menus = menus;
    }
}
