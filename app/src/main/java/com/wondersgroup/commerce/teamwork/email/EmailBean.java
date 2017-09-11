package com.wondersgroup.commerce.teamwork.email;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bjy on 2017/9/6.
 */

public class EmailBean implements Parcelable{
    @SerializedName("VIEWFLAG")
    private String flag;//1:已读； 0：未读

    @SerializedName("USERNAME")
    private String userName;

    @SerializedName("TITLE")
    private String title;

    @SerializedName("SENDTIME")
    private String date;

    @SerializedName("MAIL_ID")
    private String mailId;

    public EmailBean(){

    }

    protected EmailBean(Parcel in) {
        flag = in.readString();
        userName = in.readString();
        title = in.readString();
        date = in.readString();
        mailId = in.readString();
    }

    public static final Creator<EmailBean> CREATOR = new Creator<EmailBean>() {
        @Override
        public EmailBean createFromParcel(Parcel in) {
            return new EmailBean(in);
        }

        @Override
        public EmailBean[] newArray(int size) {
            return new EmailBean[size];
        }
    };

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMailId() {
        return mailId;
    }

    public void setMailId(String mailId) {
        this.mailId = mailId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(flag);
        dest.writeString(userName);
        dest.writeString(title);
        dest.writeString(date);
        dest.writeString(mailId);
    }
}
