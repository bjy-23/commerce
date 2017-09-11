package com.wondersgroup.commerce.teamwork.email;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by bjy on 2017/9/7.
 */

public class EmailResult {
    @SerializedName("result")
    private List<EmailBean> emailList;

    public List<EmailBean> getEmailList() {
        return emailList;
    }

    public void setEmailList(List<EmailBean> emailList) {
        this.emailList = emailList;
    }
}
