package com.wondersgroup.commerce.fgdj.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by bjy on 2017/5/9.
 */

public class PartyInfo implements Parcelable {

    private String address;

    private int applyMemberNum;

    private String areaCode;

    private String areaName;

    private String buildWay;//组建方式

    private String entId;

    private String entInv;

    private String esDate;//组建时间

    private String financeBudgetInv;

    private String firstSectyFrom;

    private String firstSectyMobile;

    private String firstSectyName;

    private int ftPartyAffNum;

    private String fullName;//党组织名称

    private String hasActivityFunds;//党建活动经费

    private String hasActivityPlace;//党组织活动场所

    private int hasMemberNum;

    private String instructorFrom;

    private String instructorName;

    private String instructorTel;

    private String isConformLiuyou;

    private int liqMemberNum;

    private String memberDuesInv;

    private String name;

    private String operBy;

    private String operByName;

    private String operDept;

    private String operDeptName;

    private String operOrgan;

    private String operOrganName;

    private String operTime;

    private String othInv;

    private int overEntNum;

    private String partyAffNum;;

    private String partyOgId;

    private String partyOgLevel;//党组织级别

    private String primaryKey;

    private String secretaryEquipWay;

    private String secretaryJobStation;

    private String secretaryMobile;

    private String secretaryName;

    private boolean selected;

    private String superName;//上级党组织名称

    private String swapStatus;

    private String tableName;

    private String tel;

    private String valid;

    private String generalPartyBranchNum;//党总支数量
    private String partyBranchNum;//党支部数量

    public PartyInfo() {
    }

    protected PartyInfo(Parcel in) {
        address = in.readString();
        applyMemberNum = in.readInt();
        areaCode = in.readString();
        areaName = in.readString();
        buildWay = in.readString();
        entId = in.readString();
        entInv = in.readString();
        esDate = in.readString();
        financeBudgetInv = in.readString();
        firstSectyFrom = in.readString();
        firstSectyMobile = in.readString();
        firstSectyName = in.readString();
        ftPartyAffNum = in.readInt();
        fullName = in.readString();
        generalPartyBranchNum = in.readString();
        hasActivityFunds = in.readString();
        hasActivityPlace = in.readString();
        hasMemberNum = in.readInt();
        instructorFrom = in.readString();
        instructorName = in.readString();
        instructorTel = in.readString();
        isConformLiuyou = in.readString();
        liqMemberNum = in.readInt();
        memberDuesInv = in.readString();
        name = in.readString();
        operBy = in.readString();
        operByName = in.readString();
        operDept = in.readString();
        operDeptName = in.readString();
        operOrgan = in.readString();
        operOrganName = in.readString();
        operTime = in.readString();
        othInv = in.readString();
        overEntNum = in.readInt();
        partyAffNum = in.readString();
        partyBranchNum = in.readString();
        partyOgId = in.readString();
        partyOgLevel = in.readString();
        primaryKey = in.readString();
        secretaryEquipWay = in.readString();
        secretaryJobStation = in.readString();
        secretaryMobile = in.readString();
        secretaryName = in.readString();
        selected = in.readByte() != 0;
        superName = in.readString();
        swapStatus = in.readString();
        tableName = in.readString();
        tel = in.readString();
        valid = in.readString();
    }

    public static final Creator<PartyInfo> CREATOR = new Creator<PartyInfo>() {
        @Override
        public PartyInfo createFromParcel(Parcel in) {
            return new PartyInfo(in);
        }

        @Override
        public PartyInfo[] newArray(int size) {
            return new PartyInfo[size];
        }
    };

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getApplyMemberNum() {
        return applyMemberNum;
    }

    public void setApplyMemberNum(int applyMemberNum) {
        this.applyMemberNum = applyMemberNum;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
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

    public String getFirstSectyFrom() {
        return firstSectyFrom;
    }

    public void setFirstSectyFrom(String firstSectyFrom) {
        this.firstSectyFrom = firstSectyFrom;
    }

    public String getFirstSectyMobile() {
        return firstSectyMobile;
    }

    public void setFirstSectyMobile(String firstSectyMobile) {
        this.firstSectyMobile = firstSectyMobile;
    }

    public String getFirstSectyName() {
        return firstSectyName;
    }

    public void setFirstSectyName(String firstSectyName) {
        this.firstSectyName = firstSectyName;
    }

    public int getFtPartyAffNum() {
        return ftPartyAffNum;
    }

    public void setFtPartyAffNum(int ftPartyAffNum) {
        this.ftPartyAffNum = ftPartyAffNum;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGeneralPartyBranchNum() {
        return generalPartyBranchNum;
    }

    public void setGeneralPartyBranchNum(String generalPartyBranchNum) {
        this.generalPartyBranchNum = generalPartyBranchNum;
    }

    public String getHasActivityFunds() {
        return hasActivityFunds;
    }

    public void setHasActivityFunds(String hasActivityFunds) {
        this.hasActivityFunds = hasActivityFunds;
    }

    public String getHasActivityPlace() {
        return hasActivityPlace;
    }

    public void setHasActivityPlace(String hasActivityPlace) {
        this.hasActivityPlace = hasActivityPlace;
    }

    public int getHasMemberNum() {
        return hasMemberNum;
    }

    public void setHasMemberNum(int hasMemberNum) {
        this.hasMemberNum = hasMemberNum;
    }

    public String getInstructorFrom() {
        return instructorFrom;
    }

    public void setInstructorFrom(String instructorFrom) {
        this.instructorFrom = instructorFrom;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getInstructorTel() {
        return instructorTel;
    }

    public void setInstructorTel(String instructorTel) {
        this.instructorTel = instructorTel;
    }

    public String getIsConformLiuyou() {
        return isConformLiuyou;
    }

    public void setIsConformLiuyou(String isConformLiuyou) {
        this.isConformLiuyou = isConformLiuyou;
    }

    public int getLiqMemberNum() {
        return liqMemberNum;
    }

    public void setLiqMemberNum(int liqMemberNum) {
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

    public String getOperDept() {
        return operDept;
    }

    public void setOperDept(String operDept) {
        this.operDept = operDept;
    }

    public String getOperDeptName() {
        return operDeptName;
    }

    public void setOperDeptName(String operDeptName) {
        this.operDeptName = operDeptName;
    }

    public String getOperOrgan() {
        return operOrgan;
    }

    public void setOperOrgan(String operOrgan) {
        this.operOrgan = operOrgan;
    }

    public String getOperOrganName() {
        return operOrganName;
    }

    public void setOperOrganName(String operOrganName) {
        this.operOrganName = operOrganName;
    }

    public String getOperTime() {
        return operTime;
    }

    public void setOperTime(String operTime) {
        this.operTime = operTime;
    }

    public String getOthInv() {
        return othInv;
    }

    public void setOthInv(String othInv) {
        this.othInv = othInv;
    }

    public int getOverEntNum() {
        return overEntNum;
    }

    public void setOverEntNum(int overEntNum) {
        this.overEntNum = overEntNum;
    }

    public String getPartyAffNum() {
        return partyAffNum;
    }

    public void setPartyAffNum(String partyAffNum) {
        this.partyAffNum = partyAffNum;
    }

    public String getPartyBranchNum() {
        return partyBranchNum;
    }

    public void setPartyBranchNum(String partyBranchNum) {
        this.partyBranchNum = partyBranchNum;
    }

    public String getPartyOgId() {
        return partyOgId;
    }

    public void setPartyOgId(String partyOgId) {
        this.partyOgId = partyOgId;
    }

    public String getPartyOgLevel() {
        return partyOgLevel;
    }

    public void setPartyOgLevel(String partyOgLevel) {
        this.partyOgLevel = partyOgLevel;
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

    public String getSecretaryJobStation() {
        return secretaryJobStation;
    }

    public void setSecretaryJobStation(String secretaryJobStation) {
        this.secretaryJobStation = secretaryJobStation;
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

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(address);
        dest.writeInt(applyMemberNum);
        dest.writeString(areaCode);
        dest.writeString(areaName);
        dest.writeString(buildWay);
        dest.writeString(entId);
        dest.writeString(entInv);
        dest.writeString(esDate);
        dest.writeString(financeBudgetInv);
        dest.writeString(firstSectyFrom);
        dest.writeString(firstSectyMobile);
        dest.writeString(firstSectyName);
        dest.writeInt(ftPartyAffNum);
        dest.writeString(fullName);
        dest.writeString(generalPartyBranchNum);
        dest.writeString(hasActivityFunds);
        dest.writeString(hasActivityPlace);
        dest.writeInt(hasMemberNum);
        dest.writeString(instructorFrom);
        dest.writeString(instructorName);
        dest.writeString(instructorTel);
        dest.writeString(isConformLiuyou);
        dest.writeInt(liqMemberNum);
        dest.writeString(memberDuesInv);
        dest.writeString(name);
        dest.writeString(operBy);
        dest.writeString(operByName);
        dest.writeString(operDept);
        dest.writeString(operDeptName);
        dest.writeString(operOrgan);
        dest.writeString(operOrganName);
        dest.writeString(operTime);
        dest.writeString(othInv);
        dest.writeInt(overEntNum);
        dest.writeString(partyAffNum);
        dest.writeString(partyBranchNum);
        dest.writeString(partyOgId);
        dest.writeString(partyOgLevel);
        dest.writeString(primaryKey);
        dest.writeString(secretaryEquipWay);
        dest.writeString(secretaryJobStation);
        dest.writeString(secretaryMobile);
        dest.writeString(secretaryName);
        dest.writeByte((byte) (selected ? 1 : 0));
        dest.writeString(superName);
        dest.writeString(swapStatus);
        dest.writeString(tableName);
        dest.writeString(tel);
        dest.writeString(valid);
    }
}
