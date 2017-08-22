package com.example.fgdj.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bjy on 2017/5/8.
 */

public class LeaderInfo implements Parcelable{
    private String cerno;

    private String certype;

    private String entId;

    private String isLeagueMember;

    private String isLeagueSecty;//是否团组织书记

    private String isPartyMember;

    private String isPartySecty;//是否党组织书记

    private String leaderpId;

    private String name;

    private String primaryKey;

    private boolean selected;

    private String sex;

    private String swapStatus;

    private String tableName;

    private String tel;//联系电话

    private String socialDuty;//主要社会职务

    private String politicalStatus;//政治面貌

    @SerializedName("leagueName")
    private String leagueName;//团组织名称

    @SerializedName("partyOrgnztion")
    private String partyOrgnztion;//党组织名称

    public LeaderInfo() {
    }

    protected LeaderInfo(Parcel in) {
        cerno = in.readString();
        certype = in.readString();
        entId = in.readString();
        isLeagueMember = in.readString();
        isLeagueSecty = in.readString();
        isPartyMember = in.readString();
        isPartySecty = in.readString();
        leaderpId = in.readString();
        name = in.readString();
        primaryKey = in.readString();
        selected = in.readByte() != 0;
        sex = in.readString();
        swapStatus = in.readString();
        tableName = in.readString();
        tel = in.readString();
        leagueName = in.readString();
        partyOrgnztion = in.readString();
        socialDuty = in.readString();
        politicalStatus = in.readString();
    }

    public static final Creator<LeaderInfo> CREATOR = new Creator<LeaderInfo>() {
        @Override
        public LeaderInfo createFromParcel(Parcel in) {
            return new LeaderInfo(in);
        }

        @Override
        public LeaderInfo[] newArray(int size) {
            return new LeaderInfo[size];
        }
    };

    public String getSocialDuty() {
        return socialDuty;
    }

    public void setSocialDuty(String socialDuty) {
        this.socialDuty = socialDuty;
    }

    public String getPoliticalStatus() {
        return politicalStatus;
    }

    public void setPoliticalStatus(String politicalStatus) {
        this.politicalStatus = politicalStatus;
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

    public String getEntId() {
        return entId;
    }

    public void setEntId(String entId) {
        this.entId = entId;
    }

    public String getIsLeagueMember() {
        return isLeagueMember;
    }

    public void setIsLeagueMember(String isLeagueMember) {
        this.isLeagueMember = isLeagueMember;
    }

    public String getIsLeagueSecty() {
        return isLeagueSecty;
    }

    public void setIsLeagueSecty(String isLeagueSecty) {
        this.isLeagueSecty = isLeagueSecty;
    }

    public String getIsPartyMember() {
        return isPartyMember;
    }

    public void setIsPartyMember(String isPartyMember) {
        this.isPartyMember = isPartyMember;
    }

    public String getIsPartySecty() {
        return isPartySecty;
    }

    public void setIsPartySecty(String isPartySecty) {
        this.isPartySecty = isPartySecty;
    }

    public String getLeaderpId() {
        return leaderpId;
    }

    public void setLeaderpId(String leaderpId) {
        this.leaderpId = leaderpId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public String getPartyOrgnztion() {
        return partyOrgnztion;
    }

    public void setPartyOrgnztion(String partyOrgnztion) {
        this.partyOrgnztion = partyOrgnztion;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cerno);
        dest.writeString(certype);
        dest.writeString(entId);
        dest.writeString(isLeagueMember);
        dest.writeString(isLeagueSecty);
        dest.writeString(isPartyMember);
        dest.writeString(isPartySecty);
        dest.writeString(leaderpId);
        dest.writeString(name);
        dest.writeString(primaryKey);
        dest.writeByte((byte) (selected ? 1 : 0));
        dest.writeString(sex);
        dest.writeString(swapStatus);
        dest.writeString(tableName);
        dest.writeString(tel);
        dest.writeString(leagueName);
        dest.writeString(partyOrgnztion);
        dest.writeString(socialDuty);
        dest.writeString(politicalStatus);
    }
}
