package com.wondersgroup.commerce.law_rule.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by bjy on 2017/5/16.
 */

public class LawDetailsBean extends LawBean implements Parcelable{
    private String childFlg;
    private String detailCode;
    private String detailContent;
    private String showTitle;

    public LawDetailsBean() {
    }

    protected LawDetailsBean(Parcel in) {
        childFlg = in.readString();
        detailCode = in.readString();
        detailContent = in.readString();
        showTitle = in.readString();
    }

    public static final Creator<LawDetailsBean> CREATOR = new Creator<LawDetailsBean>() {
        @Override
        public LawDetailsBean createFromParcel(Parcel in) {
            return new LawDetailsBean(in);
        }

        @Override
        public LawDetailsBean[] newArray(int size) {
            return new LawDetailsBean[size];
        }
    };

    public String getChildFlg() {
        return childFlg;
    }

    public void setChildFlg(String childFlg) {
        this.childFlg = childFlg;
    }

    public String getDetailCode() {
        return detailCode;
    }

    public void setDetailCode(String detailCode) {
        this.detailCode = detailCode;
    }

    public String getDetailContent() {
        return detailContent;
    }

    public void setDetailContent(String detailContent) {
        this.detailContent = detailContent;
    }

    public String getShowTitle() {
        return showTitle;
    }

    public void setShowTitle(String showTitle) {
        this.showTitle = showTitle;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(childFlg);
        dest.writeString(detailCode);
        dest.writeString(detailContent);
        dest.writeString(showTitle);
    }
}
