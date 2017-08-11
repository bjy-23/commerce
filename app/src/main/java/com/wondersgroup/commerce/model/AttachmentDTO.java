package com.wondersgroup.commerce.model;

import java.io.Serializable;

/**
 * Created by admin on 2016/3/9.
 * 服务器端附件的结构体
 */
public class AttachmentDTO implements Serializable {

    private String attachId = null;
    private String attachName = null;
    private String attachFileStr = null;

    public String getAttachId() {
        return attachId;
    }

    public void setAttachId(String attachId) {
        this.attachId = attachId;
    }

    public String getAttachName() {
        return attachName;
    }

    public void setAttachName(String attachName) {
        this.attachName = attachName;
    }

    public String getAttachFileStr() {
        return attachFileStr;
    }

    public void setAttachFileStr(String attachFileStr) {
        this.attachFileStr = attachFileStr;
    }
}
