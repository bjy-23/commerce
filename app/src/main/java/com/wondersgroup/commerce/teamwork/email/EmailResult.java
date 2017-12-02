package com.wondersgroup.commerce.teamwork.email;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by bjy on 2017/9/7.
 */

public class EmailResult {
    @SerializedName("result")
    private List<EmailBean> emailList;

    @SerializedName(value = "totalRecord")
    private int totalRecord;
    @SerializedName(value = "pageCount")
    private int pageCount;

    public List<EmailBean> getEmailList() {
        return emailList;
    }

    public void setEmailList(List<EmailBean> emailList) {
        this.emailList = emailList;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
}
