package com.wondersgroup.commerce.model;

/**
 * Created by Lee on 2016/2/4.
 * 询问笔录记录内容
 */
public class NoteRecordEnquire {

    private String serialNo;        //序列号
    private String enqTimeStart;    //询问开始时间
    private String enqTimeEnd;      //询问结束时间
    private String enqordNo;        //询问次序
    private String inquirer;        //询问人
    private String inquiredname;    //被询问人名称

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getEnqTimeStart() {
        return enqTimeStart;
    }

    public void setEnqTimeStart(String enqTimeStart) {
        this.enqTimeStart = enqTimeStart;
    }

    public String getEnqTimeEnd() {
        return enqTimeEnd;
    }

    public void setEnqTimeEnd(String enqTimeEnd) {
        this.enqTimeEnd = enqTimeEnd;
    }

    public String getEnqordNo() {
        return enqordNo;
    }

    public void setEnqordNo(String enqordNo) {
        this.enqordNo = enqordNo;
    }

    public String getInquirer() {
        return inquirer;
    }

    public void setInquirer(String inquirer) {
        this.inquirer = inquirer;
    }

    public String getInquiredname() {
        return inquiredname;
    }

    public void setInquiredname(String inquiredname) {
        this.inquiredname = inquiredname;
    }
}
