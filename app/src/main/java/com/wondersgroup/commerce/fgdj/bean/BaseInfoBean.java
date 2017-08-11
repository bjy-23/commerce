package com.wondersgroup.commerce.fgdj.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by bjy on 2017/5/8.
 */

public class BaseInfoBean implements Parcelable{
    private EntBaseInfo entBaseInfo;
    private LeaderInfo leaderInfo;
    private PartyInfo partyInfo;
    private List<PartyMem> partyMems;
    private LeagueInfo leagueInfo;
    private List<LeagueMem> leagueMems;

    public BaseInfoBean() {
    }

    protected BaseInfoBean(Parcel in) {
        entBaseInfo = in.readParcelable(EntBaseInfo.class.getClassLoader());
        leaderInfo = in.readParcelable(LeaderInfo.class.getClassLoader());
        partyInfo = in.readParcelable(PartyInfo.class.getClassLoader());
        partyMems = in.createTypedArrayList(PartyMem.CREATOR);
        leagueInfo = in.readParcelable(LeagueInfo.class.getClassLoader());
        leagueMems = in.createTypedArrayList(LeagueMem.CREATOR);
    }

    public static final Creator<BaseInfoBean> CREATOR = new Creator<BaseInfoBean>() {
        @Override
        public BaseInfoBean createFromParcel(Parcel in) {
            return new BaseInfoBean(in);
        }

        @Override
        public BaseInfoBean[] newArray(int size) {
            return new BaseInfoBean[size];
        }
    };

    public EntBaseInfo getEntBaseInfo() {
        return entBaseInfo;
    }

    public void setEntBaseInfo(EntBaseInfo entBaseInfo) {
        this.entBaseInfo = entBaseInfo;
    }

    public LeaderInfo getLeaderInfo() {
        return leaderInfo;
    }

    public void setLeaderInfo(LeaderInfo leaderInfo) {
        this.leaderInfo = leaderInfo;
    }

    public PartyInfo getPartyInfo() {
        return partyInfo;
    }

    public void setPartyInfo(PartyInfo partyInfo) {
        this.partyInfo = partyInfo;
    }

    public List<PartyMem> getPartyMems() {
        return partyMems;
    }

    public void setPartyMems(List<PartyMem> partyMems) {
        this.partyMems = partyMems;
    }

    public LeagueInfo getLeagueInfo() {
        return leagueInfo;
    }

    public void setLeagueInfo(LeagueInfo leagueInfo) {
        this.leagueInfo = leagueInfo;
    }

    public List<LeagueMem> getLeagueMems() {
        return leagueMems;
    }

    public void setLeagueMems(List<LeagueMem> leagueMems) {
        this.leagueMems = leagueMems;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(entBaseInfo, flags);
        dest.writeParcelable(leaderInfo, flags);
        dest.writeParcelable(partyInfo, flags);
        dest.writeTypedList(partyMems);
        dest.writeParcelable(leagueInfo, flags);
        dest.writeTypedList(leagueMems);
    }
}
