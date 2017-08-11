package com.wondersgroup.commerce.law_rule.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bjy on 2017/5/17.
 */

public class LawTypeBean extends LawBean implements Parcelable{
    @SerializedName("codeId")
    private String typeId;

    @SerializedName("lawName")
    private String typeName;

    @SerializedName("lawType")
    private String lawType;

    private boolean isHead;

    public LawTypeBean() {
    }

    protected LawTypeBean(Parcel in) {
        typeId = in.readString();
        typeName = in.readString();
        lawType = in.readString();
        isHead = in.readByte() != 0;
    }

    public static final Creator<LawTypeBean> CREATOR = new Creator<LawTypeBean>() {
        @Override
        public LawTypeBean createFromParcel(Parcel in) {
            return new LawTypeBean(in);
        }

        @Override
        public LawTypeBean[] newArray(int size) {
            return new LawTypeBean[size];
        }
    };

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getLawType() {
        return lawType;
    }

    public void setLawType(String lawType) {
        this.lawType = lawType;
    }

    public boolean isHead() {
        return isHead;
    }

    public void setHead(boolean head) {
        isHead = head;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(typeId);
        dest.writeString(typeName);
        dest.writeString(lawType);
        dest.writeByte((byte) (isHead ? 1 : 0));
    }
}
