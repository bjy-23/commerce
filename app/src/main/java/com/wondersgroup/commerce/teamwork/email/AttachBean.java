package com.wondersgroup.commerce.teamwork.email;

/**
 * Created by bjy on 2017/9/8.
 */

public class AttachBean {
    private String attachFileStr;

    private String attachId;

    private String attachName;

    public void setAttachFileStr(String attachFileStr){
        this.attachFileStr = attachFileStr;
    }
    public String getAttachFileStr(){
        return this.attachFileStr;
    }
    public void setAttachId(String attachId){
        this.attachId = attachId;
    }
    public String getAttachId(){
        return this.attachId;
    }
    public void setAttachName(String attachName){
        this.attachName = attachName;
    }
    public String getAttachName(){
        return this.attachName;
    }
}
