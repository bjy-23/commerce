package com.wondersgroup.commerce.ynwq.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 1229 on 2016/6/13.
 */
public class PicBean implements Parcelable {
    private String picName;
    private String picPath;
    private int picNum;
    private int type;//0,网络，1,本地
    private boolean loaded;//加载完成

    public PicBean(){

    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public int getPicNum() {
        return picNum;
    }

    public void setPicNum(int picNum) {
        this.picNum = picNum;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isLoaded() {
        return loaded;
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    protected PicBean(Parcel in) {
        picName = in.readString();
        picPath = in.readString();
        picNum = in.readInt();
        type = in.readInt();
        loaded = in.readByte() != 0;
    }

    public static final Creator<PicBean> CREATOR = new Creator<PicBean>() {
        @Override
        public PicBean createFromParcel(Parcel in) {
            return new PicBean(in);
        }

        @Override
        public PicBean[] newArray(int size) {
            return new PicBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(picName);
        dest.writeString(picPath);
        dest.writeInt(picNum);
        dest.writeInt(type);
        dest.writeByte((byte) (loaded ? 1 : 0));
    }
}
