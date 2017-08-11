package com.wondersgroup.commerce.model;

/**
 * Created by yclli on 2016/3/5.
 */
public class TextWpicItem {
    private String title;
    private String rowTwoText;
    private int picId;
    private String picText;
    private String isUrgency;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRowTwoText() {
        return rowTwoText;
    }

    public void setRowTwoText(String rowTwoText) {
        this.rowTwoText = rowTwoText;
    }

    public int getPicId() {
        return picId;
    }

    public void setPicId(int picId) {
        this.picId = picId;
    }

    public String getPicText() {
        return picText;
    }

    public void setPicText(String picText) {
        this.picText = picText;
    }

    public String getIsUrgency() {
        return isUrgency;
    }

    public void setIsUrgency(String isUrgency) {
        this.isUrgency = isUrgency;
    }
}
