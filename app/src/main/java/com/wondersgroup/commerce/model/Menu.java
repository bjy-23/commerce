package com.wondersgroup.commerce.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by bjy on 2017/6/19.
 */

public class Menu {
    @SerializedName(value = "webMenuVo")
    private List<MenuBean> menus;

    public List<MenuBean> getMenus() {
        return menus;
    }

    public void setMenus(List<MenuBean> menus) {
        this.menus = menus;
    }
}
