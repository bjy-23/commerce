package com.wondersgroup.commerce.teamwork.email;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bjy on 2017/9/8.
 */

public class EmailDetailResult {
    @SerializedName("mail")
    private EmailDetailBean emailDetailBean;

    public EmailDetailBean getEmailDetailBean() {
        return emailDetailBean;
    }

    public void setEmailDetailBean(EmailDetailBean emailDetailBean) {
        this.emailDetailBean = emailDetailBean;
    }
}
