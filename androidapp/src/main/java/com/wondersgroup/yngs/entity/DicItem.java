package com.wondersgroup.yngs.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 薛定猫 on 2016/1/12.
 */
public class DicItem implements Parcelable {
    @SerializedName("key")
    private String name;
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.value);
    }

    public DicItem() {
    }

    protected DicItem(Parcel in) {
        this.name = in.readString();
        this.value = in.readString();
    }

    public static final Parcelable.Creator<DicItem> CREATOR = new Parcelable.Creator<DicItem>() {
        public DicItem createFromParcel(Parcel source) {
            return new DicItem(source);
        }

        public DicItem[] newArray(int size) {
            return new DicItem[size];
        }
    };
}
