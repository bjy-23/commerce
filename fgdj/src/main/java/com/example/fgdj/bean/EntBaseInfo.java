package com.example.fgdj.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by bjy on 2017/5/8.
 */

public class EntBaseInfo implements Parcelable{
    private String address;
    private String areaCode;
    private String areaCodeRank2;
    private String areaName;
    private String areaOrganId;
    private String enpNum;//从业人员
    private String entId;
    private String entName;
    private String entType;
    private String esDate;
    private String isBase;
    private String isBuildLabor;//是否组建工会
    private String isBuildLeague;//是否组建团组织
    private String isBuildParty;//是否组建党组织
    private String isBuildWf;//是否组建妇联
    private String isMarket;//是否经营场所
    private String dispatchInstructor;//是否派遣指导员
    private String isTemp;
    private String opState;
    private String operBy;
    private String operByName;
    private String operTime;
    private String primaryKey;
    private String regNo;
    private String regOrganId;
    private String swapStatus;
    private String uniscid;
    private String vendInc;//年营业收入

    public EntBaseInfo() {
    }

    protected EntBaseInfo(Parcel in) {
        address = in.readString();
        areaCode = in.readString();
        areaCodeRank2 = in.readString();
        areaName = in.readString();
        areaOrganId = in.readString();
        enpNum = in.readString();
        entId = in.readString();
        entName = in.readString();
        entType = in.readString();
        esDate = in.readString();
        isBase = in.readString();
        isBuildLabor = in.readString();
        isBuildLeague = in.readString();
        isBuildParty = in.readString();
        isBuildWf = in.readString();
        isMarket = in.readString();
        isTemp = in.readString();
        opState = in.readString();
        operBy = in.readString();
        operByName = in.readString();
        operTime = in.readString();
        primaryKey = in.readString();
        regNo = in.readString();
        regOrganId = in.readString();
        swapStatus = in.readString();
        uniscid = in.readString();
        vendInc = in.readString();
        dispatchInstructor = in.readString();
    }

    public static final Creator<EntBaseInfo> CREATOR = new Creator<EntBaseInfo>() {
        @Override
        public EntBaseInfo createFromParcel(Parcel in) {
            return new EntBaseInfo(in);
        }

        @Override
        public EntBaseInfo[] newArray(int size) {
            return new EntBaseInfo[size];
        }
    };

    public String getDispatchInstructor() {
        return dispatchInstructor;
    }

    public void setDispatchInstructor(String dispatchInstructor) {
        this.dispatchInstructor = dispatchInstructor;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaCodeRank2() {
        return areaCodeRank2;
    }

    public void setAreaCodeRank2(String areaCodeRank2) {
        this.areaCodeRank2 = areaCodeRank2;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaOrganId() {
        return areaOrganId;
    }

    public void setAreaOrganId(String areaOrganId) {
        this.areaOrganId = areaOrganId;
    }

    public String getEnpNum() {
        return enpNum;
    }

    public void setEnpNum(String enpNum) {
        this.enpNum = enpNum;
    }

    public String getEntId() {
        return entId;
    }

    public void setEntId(String entId) {
        this.entId = entId;
    }

    public String getEntName() {
        return entName;
    }

    public void setEntName(String entName) {
        this.entName = entName;
    }

    public String getEntType() {
        return entType;
    }

    public void setEntType(String entType) {
        this.entType = entType;
    }

    public String getEsDate() {
        return esDate;
    }

    public void setEsDate(String esDate) {
        this.esDate = esDate;
    }

    public String getIsBase() {
        return isBase;
    }

    public void setIsBase(String isBase) {
        this.isBase = isBase;
    }

    public String getIsBuildLabor() {
        return isBuildLabor;
    }

    public void setIsBuildLabor(String isBuildLabor) {
        this.isBuildLabor = isBuildLabor;
    }

    public String getIsBuildLeague() {
        return isBuildLeague;
    }

    public void setIsBuildLeague(String isBuildLeague) {
        this.isBuildLeague = isBuildLeague;
    }

    public String getIsBuildParty() {
        return isBuildParty;
    }

    public void setIsBuildParty(String isBuildParty) {
        this.isBuildParty = isBuildParty;
    }

    public String getIsBuildWf() {
        return isBuildWf;
    }

    public void setIsBuildWf(String isBuildWf) {
        this.isBuildWf = isBuildWf;
    }

    public String getIsMarket() {
        return isMarket;
    }

    public void setIsMarket(String isMarket) {
        this.isMarket = isMarket;
    }

    public String getIsTemp() {
        return isTemp;
    }

    public void setIsTemp(String isTemp) {
        this.isTemp = isTemp;
    }

    public String getOpState() {
        return opState;
    }

    public void setOpState(String opState) {
        this.opState = opState;
    }

    public String getOperBy() {
        return operBy;
    }

    public void setOperBy(String operBy) {
        this.operBy = operBy;
    }

    public String getOperByName() {
        return operByName;
    }

    public void setOperByName(String operByName) {
        this.operByName = operByName;
    }

    public String getOperTime() {
        return operTime;
    }

    public void setOperTime(String operTime) {
        this.operTime = operTime;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getRegOrganId() {
        return regOrganId;
    }

    public void setRegOrganId(String regOrganId) {
        this.regOrganId = regOrganId;
    }

    public String getSwapStatus() {
        return swapStatus;
    }

    public void setSwapStatus(String swapStatus) {
        this.swapStatus = swapStatus;
    }

    public String getUniscid() {
        return uniscid;
    }

    public void setUniscid(String uniscid) {
        this.uniscid = uniscid;
    }

    public String getVendInc() {
        return vendInc;
    }

    public void setVendInc(String vendInc) {
        this.vendInc = vendInc;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(address);
        dest.writeString(areaCode);
        dest.writeString(areaCodeRank2);
        dest.writeString(areaName);
        dest.writeString(areaOrganId);
        dest.writeString(enpNum);
        dest.writeString(entId);
        dest.writeString(entName);
        dest.writeString(entType);
        dest.writeString(esDate);
        dest.writeString(isBase);
        dest.writeString(isBuildLabor);
        dest.writeString(isBuildLeague);
        dest.writeString(isBuildParty);
        dest.writeString(isBuildWf);
        dest.writeString(isMarket);
        dest.writeString(isTemp);
        dest.writeString(opState);
        dest.writeString(operBy);
        dest.writeString(operByName);
        dest.writeString(operTime);
        dest.writeString(primaryKey);
        dest.writeString(regNo);
        dest.writeString(regOrganId);
        dest.writeString(swapStatus);
        dest.writeString(uniscid);
        dest.writeString(vendInc);
        dest.writeString(dispatchInstructor);
    }
}
