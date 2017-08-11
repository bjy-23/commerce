package com.wondersgroup.commerce.ynwq.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 薛定猫 on 2015/12/22.
 */
public class JingBanItem {
    @SerializedName("resultName")
    private String title;

    @SerializedName("operatorOpnn")
    private String opin;

    @SerializedName("userName")
    private String person;

    @SerializedName("deptAndOrganName")
    private String office;

    @SerializedName("operatorDate")
    private String date;

    public JingBanItem() {
    }

    public JingBanItem(String title, String opin, String person, String office, String date) {
        this.title = title;
        this.opin = opin;
        this.person = person;
        this.office = office;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOpin() {
        return opin;
    }

    public void setOpin(String opin) {
        this.opin = opin;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
