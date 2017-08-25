package com.wondersgroup.commerce.fgdj.bean;

import com.google.gson.annotations.SerializedName;

import java.util.LinkedHashMap;

/**
 * Created by bjy on 2017/5/8.
 */

public class DicBean {
    @SerializedName("sex")
    private LinkedHashMap<String,String> gender;

    @SerializedName("dicPoliticalStatus")//政治面貌
    private LinkedHashMap<String,String> politicalStatus;

    @SerializedName("CB16")//证件类型
    private LinkedHashMap<String,String> certType;

    @SerializedName("WD07")//党组织级别
    private LinkedHashMap<String,String> partyLevel;

    @SerializedName("WD08")//团组织级别
    private LinkedHashMap<String,String> leagueLevel;

    @SerializedName("WD11")//党员类型
    private LinkedHashMap<String,String> partyMemType;

    @SerializedName("WD12")//团员类型
    private LinkedHashMap<String,String> leagueMemType;

    @SerializedName("dicNation")
    private LinkedHashMap<String,String> nation;

    @SerializedName("WD04")//党内职务
    private LinkedHashMap<String,String> partyPosition;

    @SerializedName("WD05")
    private LinkedHashMap<String,String> leaguePosition;

    @SerializedName("WD06")
    private LinkedHashMap<String,String> education;

    @SerializedName("fgdjEntType")
    private LinkedHashMap<String,String> entType;

    @SerializedName("WD02")
    private LinkedHashMap<String,String> mapEsWay;

    @SerializedName("WD10")
    private LinkedHashMap<String,String> leagueBuildWay;

    @SerializedName("dicSocialDuty")//社会职务
    private LinkedHashMap<String,String> mapSocialDuty;

    @SerializedName("dicHasOrNot")
    private LinkedHashMap<String,String> mapHasOrNot;

    @SerializedName("dicYesOrNo")
    private LinkedHashMap<String,String> mapYesOrNo;

    public LinkedHashMap<String, String> getGender() {
        return gender;
    }

    public void setGender(LinkedHashMap<String, String> gender) {
        this.gender = gender;
    }

    public LinkedHashMap<String, String> getPoliticalStatus() {
        return politicalStatus;
    }

    public void setPoliticalStatus(LinkedHashMap<String, String> politicalStatus) {
        this.politicalStatus = politicalStatus;
    }

    public LinkedHashMap<String, String> getCertType() {
        return certType;
    }

    public void setCertType(LinkedHashMap<String, String> certType) {
        this.certType = certType;
    }

    public LinkedHashMap<String, String> getPartyLevel() {
        return partyLevel;
    }

    public void setPartyLevel(LinkedHashMap<String, String> partyLevel) {
        this.partyLevel = partyLevel;
    }

    public LinkedHashMap<String, String> getLeagueLevel() {
        return leagueLevel;
    }

    public void setLeagueLevel(LinkedHashMap<String, String> leagueLevel) {
        this.leagueLevel = leagueLevel;
    }

    public LinkedHashMap<String, String> getPartyMemType() {
        return partyMemType;
    }

    public void setPartyMemType(LinkedHashMap<String, String> partyMemType) {
        this.partyMemType = partyMemType;
    }

    public LinkedHashMap<String, String> getLeagueMemType() {
        return leagueMemType;
    }

    public void setLeagueMemType(LinkedHashMap<String, String> leagueMemType) {
        this.leagueMemType = leagueMemType;
    }

    public LinkedHashMap<String, String> getNation() {
        return nation;
    }

    public void setNation(LinkedHashMap<String, String> nation) {
        this.nation = nation;
    }

    public LinkedHashMap<String, String> getPartyPosition() {
        return partyPosition;
    }

    public void setPartyPosition(LinkedHashMap<String, String> partyPosition) {
        this.partyPosition = partyPosition;
    }

    public LinkedHashMap<String, String> getLeaguePosition() {
        return leaguePosition;
    }

    public void setLeaguePosition(LinkedHashMap<String, String> leaguePosition) {
        this.leaguePosition = leaguePosition;
    }

    public LinkedHashMap<String, String> getEducation() {
        return education;
    }

    public void setEducation(LinkedHashMap<String, String> education) {
        this.education = education;
    }

    public LinkedHashMap<String, String> getEntType() {
        return entType;
    }

    public void setEntType(LinkedHashMap<String, String> entType) {
        this.entType = entType;
    }

    public LinkedHashMap<String, String> getMapEsWay() {
        return mapEsWay;
    }

    public void setMapEsWay(LinkedHashMap<String, String> mapEsWay) {
        this.mapEsWay = mapEsWay;
    }

    public LinkedHashMap<String, String> getLeagueBuildWay() {
        return leagueBuildWay;
    }

    public void setLeagueBuildWay(LinkedHashMap<String, String> leagueBuildWay) {
        this.leagueBuildWay = leagueBuildWay;
    }

    public LinkedHashMap<String, String> getMapSocialDuty() {
        return mapSocialDuty;
    }

    public void setMapSocialDuty(LinkedHashMap<String, String> mapSocialDuty) {
        this.mapSocialDuty = mapSocialDuty;
    }

    public LinkedHashMap<String, String> getMapHasOrNot() {
        return mapHasOrNot;
    }

    public void setMapHasOrNot(LinkedHashMap<String, String> mapHasOrNot) {
        this.mapHasOrNot = mapHasOrNot;
    }

    public LinkedHashMap<String, String> getMapYesOrNo() {
        return mapYesOrNo;
    }

    public void setMapYesOrNo(LinkedHashMap<String, String> mapYesOrNo) {
        this.mapYesOrNo = mapYesOrNo;
    }
}
