package com.wondersgroup.commerce.model;

/**
 * Created by admin on 2017/3/22.
 * 查询个体工商户登记信息
 */

public class ProcedureCaseAICItemsVo {

    private String clueNo;  //	线索号	String
    private String entityId;    //	个体主键	String
    private String regNo;       //	个体工商户注册号	String
    private String uniScid;     //	个体工商户统一社会信用代码	String
    private String litigtName;      //	个体工商户名称	String
    private String legalName;       //	法定代表人	String

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
}
