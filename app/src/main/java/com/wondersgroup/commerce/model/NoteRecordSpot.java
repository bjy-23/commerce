package com.wondersgroup.commerce.model;

/**
 * Created by Lee on 2016/2/4.
 * 现场笔录记录内容
 */
public class NoteRecordSpot {

    private String serialNo;    //序列号
    private String insfromStr;  //检查起始时间
    private String instoStr;    //检查截止时间
    private String insspot;     //检查地点
    private String inspector;   //检查人
    private String litigantName;//被检查人名称

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getInsfromStr() {
        return insfromStr;
    }

    public void setInsfromStr(String insfromStr) {
        this.insfromStr = insfromStr;
    }

    public String getInstoStr() {
        return instoStr;
    }

    public void setInstoStr(String instoStr) {
        this.instoStr = instoStr;
    }

    public String getInsspot() {
        return insspot;
    }

    public void setInsspot(String insspot) {
        this.insspot = insspot;
    }

    public String getInspector() {
        return inspector;
    }

    public void setInspector(String inspector) {
        this.inspector = inspector;
    }

    public String getLitigantName() {
        return litigantName;
    }

    public void setLitigantName(String litigantName) {
        this.litigantName = litigantName;
    }
}
