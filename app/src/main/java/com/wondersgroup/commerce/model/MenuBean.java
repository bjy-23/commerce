package com.wondersgroup.commerce.model;

import com.google.gson.annotations.SerializedName;
import com.wondersgroup.commerce.interface_.Data;

/**
 * Created by bjy on 2017/6/19.
 */

public class MenuBean extends Data{
    @SerializedName(value = "menuName",alternate = {})
    private String menuName;

    @SerializedName(value = "authId")
    private String menuId;

    private int resId;
    private String number;
    private String[] menuIdList;

    public MenuBean(String menuName, String menuId, int resId,String number) {
        this.menuName = menuName;
        this.menuId = menuId;
        this.resId = resId;
        this.number = number;
        setType(0);//0:含一个authId
    }

    public MenuBean(String menuName, String[] menuIdList, int resId,String number) {
        this.menuName = menuName;
        this.menuIdList = menuIdList;
        this.resId = resId;
        this.number = number;
        setType(1);//1:含一组authId
    }

    public MenuBean(String menuName, String menuId, int resId) {
        this.menuName = menuName;
        this.menuId = menuId;
        this.resId = resId;
        setType(0);
    }

    public MenuBean(String menuName, String[] menuIdList, int resId) {
        this.menuName = menuName;
        this.menuIdList = menuIdList;
        this.resId = resId;
        setType(1);
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String[] getMenuIdList() {
        return menuIdList;
    }

    public void setMenuIdList(String[] menuiDList) {
        this.menuIdList = menuiDList;
    }
}
