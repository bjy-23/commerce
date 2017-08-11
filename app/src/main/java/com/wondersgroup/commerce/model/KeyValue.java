package com.wondersgroup.commerce.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by bjy on 2017/5/11.
 */

public class KeyValue implements Parcelable{
    private String key;
    private String value;

    public KeyValue() {
    }

    protected KeyValue(Parcel in) {
        key = in.readString();
        value = in.readString();
    }

    public static final Creator<KeyValue> CREATOR = new Creator<KeyValue>() {
        @Override
        public KeyValue createFromParcel(Parcel in) {
            return new KeyValue(in);
        }

        @Override
        public KeyValue[] newArray(int size) {
            return new KeyValue[size];
        }
    };

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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
        dest.writeString(key);
        dest.writeString(value);
    }
}
