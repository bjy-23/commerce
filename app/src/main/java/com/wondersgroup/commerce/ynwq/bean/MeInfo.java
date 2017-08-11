package com.wondersgroup.commerce.ynwq.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 薛定猫 on 2016/1/25.
 */
public class MeInfo {
    @SerializedName("etpsName")
    private String entName;
    @SerializedName("leaderName")
    private String leRepName;
    @SerializedName("applierTel")
    private String phoneNum;

    private String runAddress;
    private String runSpaceType;
    private String runSpaceTypeOther;
    private String runArea;
    private String areaEqScale;
    private String hasEquipment;
    private String explorationOpnn;
    private String explorationPerson;
    private String explorationDate;
    private String explorationPerson2;
    private String memo;
    private String imgFileName1;
    private String imgFileName2;
    private String imgFileName3;
    private String imgFileName4;
    private String imgFileName5;
    private String baseUrl;

    public String getImgFileName1() {
        return imgFileName1;
    }

    public void setImgFileName1(String imgFileName1) {
        this.imgFileName1 = imgFileName1;
    }

    public String getImgFileName2() {
        return imgFileName2;
    }

    public void setImgFileName2(String imgFileName2) {
        this.imgFileName2 = imgFileName2;
    }

    public String getImgFileName3() {
        return imgFileName3;
    }

    public void setImgFileName3(String imgFileName3) {
        this.imgFileName3 = imgFileName3;
    }

    public String getImgFileName4() {
        return imgFileName4;
    }

    public void setImgFileName4(String imgFileName4) {
        this.imgFileName4 = imgFileName4;
    }

    public String getImgFileName5() {
        return imgFileName5;
    }

    public void setImgFileName5(String imgFileName5) {
        this.imgFileName5 = imgFileName5;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getRunAddress() {
        return runAddress;
    }

    public void setRunAddress(String runAddress) {
        this.runAddress = runAddress;
    }

    public String getRunSpaceType() {
        return runSpaceType;
    }

    public void setRunSpaceType(String runSpaceType) {
        this.runSpaceType = runSpaceType;
    }

    public String getRunSpaceTypeOther() {
        return runSpaceTypeOther;
    }

    public void setRunSpaceTypeOther(String runSpaceTypeOther) {
        this.runSpaceTypeOther = runSpaceTypeOther;
    }

    public String getRunArea() {
        return runArea;
    }

    public void setRunArea(String runArea) {
        this.runArea = runArea;
    }

    public String getAreaEqScale() {
        return areaEqScale;
    }

    public void setAreaEqScale(String areaEqScale) {
        this.areaEqScale = areaEqScale;
    }

    public String getHasEquipment() {
        return hasEquipment;
    }

    public void setHasEquipment(String hasEquipment) {
        this.hasEquipment = hasEquipment;
    }

    public String getExplorationOpnn() {
        return explorationOpnn;
    }

    public void setExplorationOpnn(String explorationOpnn) {
        this.explorationOpnn = explorationOpnn;
    }

    public String getExplorationPerson() {
        return explorationPerson;
    }

    public void setExplorationPerson(String explorationPerson) {
        this.explorationPerson = explorationPerson;
    }

    public String getExplorationDate() {
        return explorationDate;
    }

    public void setExplorationDate(String explorationDate) {
        this.explorationDate = explorationDate;
    }

    public String getExplorationPerson2() {
        return explorationPerson2;
    }

    public void setExplorationPerson2(String explorationPerson2) {
        this.explorationPerson2 = explorationPerson2;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getEntName() {
        return entName;
    }

    public void setEntName(String entName) {
        this.entName = entName;
    }

    public String getLeRepName() {
        return leRepName;
    }

    public void setLeRepName(String leRepName) {
        this.leRepName = leRepName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}
