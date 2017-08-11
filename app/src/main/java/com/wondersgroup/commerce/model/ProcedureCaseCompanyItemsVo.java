package com.wondersgroup.commerce.model;

/**
 * Created by admin on 2017/3/22.
 * 5.3.5. 企业查询
 */

public class ProcedureCaseCompanyItemsVo {

    private String clueNo;  //	线索号	String----------------------------不要
    private String entityId;  //		个体主键	String------------不能修改
    private String regNo;  //		个体工商户注册号	--------------------OK
    private String uniScid;  //		个体工商户统一社会信用代码String--不能修改
    private String litigtName;  //		个体工商户名称	--------------------OK
    private String legalName;  //		法定代表人	------------------------OK
    private String address;  //		个体/企业现居住地/经营场所	------------OK
    private String cerType;  //		证件类型（G）	String------------------OK
    private String cerNo;  //		证件号码（G）	String------------------OK
    private String pripId;  //		主体身份代码	------------------------OK
    private String regOrganId;  //		个体/企业登记机关 	String----不能修改
    private String etpsTypeGb;  //		企业类型	String------------不能修改


    public String getClueNo() {
        return clueNo;
    }

    public void setClueNo(String clueNo) {
        this.clueNo = clueNo;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getUniScid() {
        return uniScid;
    }

    public void setUniScid(String uniScid) {
        this.uniScid = uniScid;
    }

    public String getLitigtName() {
        return litigtName;
    }

    public void setLitigtName(String litigtName) {
        this.litigtName = litigtName;
    }

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCerType() {
        return cerType;
    }

    public void setCerType(String cerType) {
        this.cerType = cerType;
    }

    public String getCerNo() {
        return cerNo;
    }

    public void setCerNo(String cerNo) {
        this.cerNo = cerNo;
    }

    public String getPripId() {
        return pripId;
    }

    public void setPripId(String pripId) {
        this.pripId = pripId;
    }

    public String getRegOrganId() {
        return regOrganId;
    }

    public void setRegOrganId(String regOrganId) {
        this.regOrganId = regOrganId;
    }

    public String getEtpsTypeGb() {
        return etpsTypeGb;
    }

    public void setEtpsTypeGb(String etpsTypeGb) {
        this.etpsTypeGb = etpsTypeGb;
    }
}
