package com.wondersgroup.commerce.fgdj.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by bjy on 2017/5/9.
 */

public class LeagueMem implements Parcelable{
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

    private String leagueId;

    private String leagueMemId;

    private String leagueOrgName;

    private String leaguePositon;

    private String membershipInfo;

    private String mobile;

    private String name;

    private String nation;

    private String primaryKey;

    private boolean selected;

    private String sex;

    private String swapStatus;

    private String tableName;

    private String type;

    private String valid;

    public LeagueMem() {
    }

    protected LeagueMem(Parcel in) {
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
        leagueId = in.readString();
        leagueMemId = in.readString();
        leagueOrgName = in.readString();
        leaguePositon = in.readString();
        membershipInfo = in.readString();
        mobile = in.readString();
        name = in.readString();
        nation = in.readString();
        primaryKey = in.readString();
        selected = in.readByte() != 0;
        sex = in.readString();
        swapStatus = in.readString();
        tableName = in.readString();
        type = in.readString();
        valid = in.readString();
    }

    public static final Creator<LeagueMem> CREATOR = new Creator<LeagueMem>() {
        @Override
        public LeagueMem createFromParcel(Parcel in) {
            return new LeagueMem(in);
        }

        @Override
        public LeagueMem[] newArray(int size) {
            return new LeagueMem[size];
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

    public String getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(String leagueId) {
        this.leagueId = leagueId;
    }

    public String getLeagueMemId() {
        return leagueMemId;
    }

    public void setLeagueMemId(String leagueMemId) {
        this.leagueMemId = leagueMemId;
    }

    public String getLeagueOrgName() {
        return leagueOrgName;
    }

    public void setLeagueOrgName(String leagueOrgName) {
        this.leagueOrgName = leagueOrgName;
    }

    public String getLeaguePositon() {
        return leaguePositon;
    }

    public void setLeaguePositon(String leaguePositon) {
        this.leaguePositon = leaguePositon;
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
        dest.writeString(leagueId);
        dest.writeString(leagueMemId);
        dest.writeString(leagueOrgName);
        dest.writeString(leaguePositon);
        dest.writeString(membershipInfo);
        dest.writeString(mobile);
        dest.writeString(name);
        dest.writeString(nation);
        dest.writeString(primaryKey);
        dest.writeByte((byte) (selected ? 1 : 0));
        dest.writeString(sex);
        dest.writeString(swapStatus);
        dest.writeString(tableName);
        dest.writeString(type);
        dest.writeString(valid);
    }
}
