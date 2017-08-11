package com.wondersgroup.commerce.fgdj.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by bjy on 2017/5/9.
 */

public class PartyMem implements Parcelable{
    private String birthday;

    private String cerno;

    private String certype;

    private String company;

    private String education;

    private String fixtel;

    private String invalidRsn;

    private String jobPosition;

    private String joinDate;

    private String jsonStr;

    private String membershipInfo;

    private String mobile;

    private String name;

    private String nation;

    private String partyMbId;

    private String partyOgId;

    private String partyOgName;

    private String partyOrgName;

    private String partyPositon;

    private String primaryKey;

    private boolean selected;

    private String sex;

    private String swapStatus;

    private String tableName;

    private String type;

    private String valid;

    private String isFloating;

    public PartyMem() {
    }

    protected PartyMem(Parcel in) {
        birthday = in.readString();
        cerno = in.readString();
        certype = in.readString();
        company = in.readString();
        education = in.readString();
        fixtel = in.readString();
        invalidRsn = in.readString();
        jobPosition = in.readString();
        joinDate = in.readString();
        jsonStr = in.readString();
        membershipInfo = in.readString();
        mobile = in.readString();
        name = in.readString();
        nation = in.readString();
        partyMbId = in.readString();
        partyOgId = in.readString();
        partyOgName = in.readString();
        partyOrgName = in.readString();
        partyPositon = in.readString();
        primaryKey = in.readString();
        selected = in.readByte() != 0;
        sex = in.readString();
        swapStatus = in.readString();
        tableName = in.readString();
        type = in.readString();
        valid = in.readString();
        isFloating = in.readString();
    }

    public static final Creator<PartyMem> CREATOR = new Creator<PartyMem>() {
        @Override
        public PartyMem createFromParcel(Parcel in) {
            return new PartyMem(in);
        }

        @Override
        public PartyMem[] newArray(int size) {
            return new PartyMem[size];
        }
    };

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCerno() {
        return cerno;
    }

    public void setCerno(String cerno) {
        this.cerno = cerno;
    }

    public String getCertype() {
        return certype;
    }

    public void setCertype(String certype) {
        this.certype = certype;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getFixtel() {
        return fixtel;
    }

    public void setFixtel(String fixtel) {
        this.fixtel = fixtel;
    }

    public String getInvalidRsn() {
        return invalidRsn;
    }

    public void setInvalidRsn(String invalidRsn) {
        this.invalidRsn = invalidRsn;
    }

    public String getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public String getJsonStr() {
        return jsonStr;
    }

    public void setJsonStr(String jsonStr) {
        this.jsonStr = jsonStr;
    }

    public String getMembershipInfo() {
        return membershipInfo;
    }

    public void setMembershipInfo(String membershipInfo) {
        this.membershipInfo = membershipInfo;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getPartyMbId() {
        return partyMbId;
    }

    public void setPartyMbId(String partyMbId) {
        this.partyMbId = partyMbId;
    }

    public String getPartyOgId() {
        return partyOgId;
    }

    public void setPartyOgId(String partyOgId) {
        this.partyOgId = partyOgId;
    }

    public String getPartyOgName() {
        return partyOgName;
    }

    public void setPartyOgName(String partyOgName) {
        this.partyOgName = partyOgName;
    }

    public String getPartyOrgName() {
        return partyOrgName;
    }

    public void setPartyOrgName(String partyOrgName) {
        this.partyOrgName = partyOrgName;
    }

    public String getPartyPositon() {
        return partyPositon;
    }

    public void setPartyPositon(String partyPositon) {
        this.partyPositon = partyPositon;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    public String getIsFloating() {
        return isFloating;
    }

    public void setIsFloating(String isFloating) {
        this.isFloating = isFloating;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(birthday);
        dest.writeString(cerno);
        dest.writeString(certype);
        dest.writeString(company);
        dest.writeString(education);
        dest.writeString(fixtel);
        dest.writeString(invalidRsn);
        dest.writeString(jobPosition);
        dest.writeString(joinDate);
        dest.writeString(jsonStr);
        dest.writeString(membershipInfo);
        dest.writeString(mobile);
        dest.writeString(name);
        dest.writeString(nation);
        dest.writeString(partyMbId);
        dest.writeString(partyOgId);
        dest.writeString(partyOgName);
        dest.writeString(partyOrgName);
        dest.writeString(partyPositon);
        dest.writeString(primaryKey);
        dest.writeByte((byte) (selected ? 1 : 0));
        dest.writeString(sex);
        dest.writeString(swapStatus);
        dest.writeString(tableName);
        dest.writeString(type);
        dest.writeString(valid);
        dest.writeString(isFloating);
    }
}
