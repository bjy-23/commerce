package com.example.fgdj.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by bjy on 2017/5/9.
 */

public class LeagueInfo implements Parcelable{
    private String address;

    private String buildWay;

    private String entId;

    private String entInv;

    private String esDate;

    private String financeBudgetInv;

    private String ftLeagueAffNum;

    private String fullName;

    private String hasMemberNum;

    private String instructorFrom;

    private String instructorMobile;

    private String instructorName;

    private String leagueAffNum;

    private String leagueId;

    private String leagueLevel;

    private String liqMemberNum;

    private String memberDuesInv;

    private String name;

    private String othInv;

    private String overEntNum;

    private String primaryKey;

    private String secretaryEquipWay;

    private String secretaryMobile;

    private String secretaryName;

    private boolean selected;

    private String superName;

    private String swapStatus;

    private String tableName;

    private String tel;

    public LeagueInfo() {
    }

    protected LeagueInfo(Parcel in) {
        address = in.readString();
        buildWay = in.readString();
        entId = in.readString();
        entInv = in.readString();
        esDate = in.readString();
        financeBudgetInv = in.readString();
        ftLeagueAffNum = in.readString();
        fullName = in.readString();
        hasMemberNum = in.readString();
        instructorFrom = in.readString();
        instructorMobile = in.readString();
        instructorName = in.readString();
        leagueAffNum = in.readString();
        leagueId = in.readString();
        leagueLevel = in.readString();
        liqMemberNum = in.readString();
        memberDuesInv = in.readString();
        name = in.readString();
        othInv = in.readString();
        overEntNum = in.readString();
        primaryKey = in.readString();
        secretaryEquipWay = in.readString();
        secretaryMobile = in.readString();
        secretaryName = in.readString();
        selected = in.readByte() != 0;
        superName = in.readString();
        swapStatus = in.readString();
        tableName = in.readString();
        tel = in.readString();
    }

    public static final Creator<LeagueInfo> CREATOR = new Creator<LeagueInfo>() {
        @Override
        public LeagueInfo createFromParcel(Parcel in) {
            return new LeagueInfo(in);
        }

        @Override
        public LeagueInfo[] newArray(int size) {
            return new LeagueInfo[size];
        }
    };

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBuildWay() {
        return buildWay;
    }

    public void setBuildWay(String buildWay) {
        this.buildWay = buildWay;
    }

    public String getEntId() {
        return entId;
    }

    public void setEntId(String entId) {
        this.entId = entId;
    }

    public String getEntInv() {
        return entInv;
    }

    public void setEntInv(String entInv) {
        this.entInv = entInv;
    }

    public String getEsDate() {
        return esDate;
    }

    public void setEsDate(String esDate) {
        this.esDate = esDate;
    }

    public String getFinanceBudgetInv() {
        return financeBudgetInv;
    }

    public void setFinanceBudgetInv(String financeBudgetInv) {
        this.financeBudgetInv = financeBudgetInv;
    }

    public String getFtLeagueAffNum() {
        return ftLeagueAffNum;
    }

    public void setFtLeagueAffNum(String ftLeagueAffNum) {
        this.ftLeagueAffNum = ftLeagueAffNum;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getHasMemberNum() {
        return hasMemberNum;
    }

    public void setHasMemberNum(String hasMemberNum) {
        this.hasMemberNum = hasMemberNum;
    }

    public String getInstructorFrom() {
        return instructorFrom;
    }

    public void setInstructorFrom(String instructorFrom) {
        this.instructorFrom = instructorFrom;
    }

    public String getInstructorMobile() {
        return instructorMobile;
    }

    public void setInstructorMobile(String instructorMobile) {
        this.instructorMobile = instructorMobile;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getLeagueAffNum() {
        return leagueAffNum;
    }

    public void setLeagueAffNum(String leagueAffNum) {
        this.leagueAffNum = leagueAffNum;
    }

    public String getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(String leagueId) {
        this.leagueId = leagueId;
    }

    public String getLeagueLevel() {
        return leagueLevel;
    }

    public void setLeagueLevel(String leagueLevel) {
        this.leagueLevel = leagueLevel;
    }

    public String getLiqMemberNum() {
        return liqMemberNum;
    }

    public void setLiqMemberNum(String liqMemberNum) {
        this.liqMemberNum = liqMemberNum;
    }

    public String getMemberDuesInv() {
        return memberDuesInv;
    }

    public void setMemberDuesInv(String memberDuesInv) {
        this.memberDuesInv = memberDuesInv;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOthInv() {
        return othInv;
    }

    public void setOthInv(String othInv) {
        this.othInv = othInv;
    }

    public String getOverEntNum() {
        return overEntNum;
    }

    public void setOverEntNum(String overEntNum) {
        this.overEntNum = overEntNum;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getSecretaryEquipWay() {
        return secretaryEquipWay;
    }

    public void setSecretaryEquipWay(String secretaryEquipWay) {
        this.secretaryEquipWay = secretaryEquipWay;
    }

    public String getSecretaryMobile() {
        return secretaryMobile;
    }

    public void setSecretaryMobile(String secretaryMobile) {
        this.secretaryMobile = secretaryMobile;
    }

    public String getSecretaryName() {
        return secretaryName;
    }

    public void setSecretaryName(String secretaryName) {
        this.secretaryName = secretaryName;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getSuperName() {
        return superName;
    }

    public void setSuperName(String superName) {
        this.superName = superName;
    }

    public String getSwapStatus() {
        return swapStatus;
    }

    public void setSwapStatus(String swapStatus) {
        this.swapStatus = swapStatus;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(address);
        dest.writeString(buildWay);
        dest.writeString(entId);
        dest.writeString(entInv);
        dest.writeString(esDate);
        dest.writeString(financeBudgetInv);
        dest.writeString(ftLeagueAffNum);
        dest.writeString(fullName);
        dest.writeString(hasMemberNum);
        dest.writeString(instructorFrom);
        dest.writeString(instructorMobile);
        dest.writeString(instructorName);
        dest.writeString(leagueAffNum);
        dest.writeString(leagueId);
        dest.writeString(leagueLevel);
        dest.writeString(liqMemberNum);
        dest.writeString(memberDuesInv);
        dest.writeString(name);
        dest.writeString(othInv);
        dest.writeString(overEntNum);
        dest.writeString(primaryKey);
        dest.writeString(secretaryEquipWay);
        dest.writeString(secretaryMobile);
        dest.writeString(secretaryName);
        dest.writeByte((byte) (selected ? 1 : 0));
        dest.writeString(superName);
        dest.writeString(swapStatus);
        dest.writeString(tableName);
        dest.writeString(tel);
    }
}
