package com.wondersgroup.yngs.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 薛定猫 on 2015/12/28.
 */
public class XwComItem {
    @SerializedName("etpsName")
    private String title;

    private String regNo;

    @SerializedName("cptlRmb")
    private String capital;

    @SerializedName("cptlType")
    private String capitalType;

    @SerializedName("etpsTypeName")
    private String type;

    @SerializedName("regOrganName")
    private String office;

    @SerializedName("entId")
    private String etpsId;

    @SerializedName("supportId")
    private String meId;

    public XwComItem(String title, String regNo, String capital, String capitalType, String type, String office) {
        this.title = title;
        this.regNo = regNo;
        this.capital = capital;
        this.capitalType = capitalType;
        this.type = type;
        this.office = office;
    }

    public XwComItem() {
    }

    public String getMeId() {
        return meId;
    }

    public void setMeId(String meId) {
        this.meId = meId;
    }

    public String getEtpsId() {
        return etpsId;
    }

    public void setEtpsId(String etpsId) {
        this.etpsId = etpsId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getCapitalType() {
        return capitalType;
    }

    public void setCapitalType(String capitalType) {
        this.capitalType = capitalType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }
}
