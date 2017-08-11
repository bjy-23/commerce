package com.wondersgroup.commerce.fgdj.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by bjy on 2017/7/12.
 */

public class AreaBean implements Parcelable{
    private String id;
    private String fullName;
    private String gbCode;
    private String name;
    private String pId;
    private int rank;
    private String valid;
    private boolean hasChild;


    public AreaBean(){

    }

    protected AreaBean(Parcel in) {
        id = in.readString();
        fullName = in.readString();
        gbCode = in.readString();
        name = in.readString();
        pId = in.readString();
        rank = in.readInt();
        valid = in.readString();
        hasChild = in.readByte() != 0;
    }

    public static final Creator<AreaBean> CREATOR = new Creator<AreaBean>() {
        @Override
        public AreaBean createFromParcel(Parcel in) {
            return new AreaBean(in);
        }

        @Override
        public AreaBean[] newArray(int size) {
            return new AreaBean[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGbCode() {
        return gbCode;
    }

    public void setGbCode(String gbCode) {
        this.gbCode = gbCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    public boolean isHasChild() {
        return hasChild;
    }

    public void setHasChild(boolean hasChild) {
        this.hasChild = hasChild;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(fullName);
        dest.writeString(gbCode);
        dest.writeString(name);
        dest.writeString(pId);
        dest.writeInt(rank);
        dest.writeString(valid);
        dest.writeByte((byte) (hasChild ? 1 : 0));
    }
}
