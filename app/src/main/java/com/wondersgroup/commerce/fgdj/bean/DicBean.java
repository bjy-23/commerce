package com.wondersgroup.commerce.fgdj.bean;

import com.google.gson.annotations.SerializedName;

import java.util.LinkedHashMap;

/**
 * Created by bjy on 2017/5/8.
 */

public class DicBean {
    @SerializedName("sex")
    private LinkedHashMap gender;

    @SerializedName("dicPoliticalStatus")//政治面貌
    private LinkedHashMap politicalStatus;

    @SerializedName("CB16")//证件类型
    private LinkedHashMap certType;

    @SerializedName("WD07")//党组织级别
    private LinkedHashMap partyLevel;

    @SerializedName("WD08")//团组织级别
    private LinkedHashMap leagueLevel;

    @SerializedName("WD11")//党员类型
    private LinkedHashMap partyMemType;

    @SerializedName("WD12")//团员类型
    private LinkedHashMap leagueMemType;

    @SerializedName("dicNation")
    private LinkedHashMap nation;

    @SerializedName("WD04")//党内职务
    private LinkedHashMap partyPosition;

    @SerializedName("WD05")
    private LinkedHashMap leaguePosition;

    @SerializedName("WD06")
    private LinkedHashMap education;

    @SerializedName("fgdjEntType")
    private LinkedHashMap entType;

    @SerializedName("WD02")
    private LinkedHashMap mapEsWay;

    @SerializedName("WD10")
    private LinkedHashMap leagueBuildWay;

    @SerializedName("dicSocialDuty")//社会职务
    private LinkedHashMap mapSocialDuty;

    @SerializedName("dicHasOrNot")
    private LinkedHashMap mapHasOrNot;

    @SerializedName("dicYesOrNo")
    private LinkedHashMap mapYesOrNo;

    public LinkedHashMap getGender() {
        return gender;
    }

    public LinkedHashMap getPoliticalStatus() {
        return politicalStatus;
    }

    public LinkedHashMap getCertType() {
        return certType;
    }

    public LinkedHashMap getPartyLevel() {
        return partyLevel;
    }

    public LinkedHashMap getLeagueLevel() {
        return leagueLevel;
    }

    public LinkedHashMap getPartyMemType() {
        return partyMemType;
    }

    public LinkedHashMap getLeagueMemType() {
        return leagueMemType;
    }

    public LinkedHashMap getNation() {
        return nation;
    }

    public LinkedHashMap getPartyPosition() {
        return partyPosition;
    }

    public LinkedHashMap getLeaguePosition() {
        return leaguePosition;
    }

    public LinkedHashMap getEducation() {
        return education;
    }

    public LinkedHashMap getEntType() {
        return entType;
    }

    public LinkedHashMap getMapEsWay() {
        return mapEsWay;
    }

    public LinkedHashMap getLeagueBuildWay() {
        return leagueBuildWay;
    }

    public LinkedHashMap getMapSocialDuty() {
        return mapSocialDuty;
    }

    public LinkedHashMap getMapHasOrNot() {
        return mapHasOrNot;
    }

    public LinkedHashMap getMapYesOrNo() {
        return mapYesOrNo;
    }
}
