package com.wondersgroup.commerce.teamwork.email;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by bjy on 2017/9/8.
 */

public class EmailDetailBean {
    private String content;

    private String sendtime;

    private String title;

    @SerializedName("username")
    private String sendName;

    @SerializedName("mailAttachVoList")
    private List<AttachBean> attachList;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSendtime() {
        return sendtime;
    }

    public void setSendtime(String sendtime) {
        this.sendtime = sendtime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSendName() {
        return sendName;
    }

    public void setSendName(String sendName) {
        this.sendName = sendName;
    }

    public List<AttachBean> getAttachList() {
        return attachList;
    }

    public void setAttachList(List<AttachBean> attachList) {
        this.attachList = attachList;
    }
}
