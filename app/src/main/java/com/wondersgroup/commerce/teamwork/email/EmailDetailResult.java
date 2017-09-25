package com.wondersgroup.commerce.teamwork.email;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by bjy on 2017/9/8.
 */

public class EmailDetailResult {
    @SerializedName("mail")
    private EmailDetailBean emailDetailBean;

    @SerializedName("mailAttachVoList")
    private List<AttachBean> attachList;

    public EmailDetailBean getEmailDetailBean() {
        return emailDetailBean;
    }

    public void setEmailDetailBean(EmailDetailBean emailDetailBean) {
        this.emailDetailBean = emailDetailBean;
    }

    public List<AttachBean> getAttachList() {
        return attachList;
    }

    public void setAttachList(List<AttachBean> attachList) {
        this.attachList = attachList;
    }
}
