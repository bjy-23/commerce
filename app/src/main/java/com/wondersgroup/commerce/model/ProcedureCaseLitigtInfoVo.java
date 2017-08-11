package com.wondersgroup.commerce.model;

/**
 * Created by admin on 2017/4/20.
 */
//当事人基本情况信息
public class ProcedureCaseLitigtInfoVo {

    private String assort = "0";	            //当事人分类------------不需要
    private String isDomination = "2";        //	当事人具体类型------不需要
    private String litigtName;          //	个体/企业名称------------------OK
    private String legalName;           //	个体/企业法定代表人/经营者-----OK
    private String regNo;               //	个体/企业注册号----------------OK
    private String uniScid;             //	个体/企业统一社会信用代码---不能修改
    private String address;             //	个体/企业现居住地/经营场所-----OK
    private String cerType;             //	证件类型（G）------------------OK
    private String cerNo;               //	证件号码（G）-----------------OK
    private String age;                 //	年龄（G）---------------------OK
    private String sex;                 //	性别（G）---------------------OK
    private String tel;                 //	联系电话（G）-----------------OK
    private String workunit;            //	工作单位（G）-----------------OK
    private String occupation;          //	职业（G）---------------------OK
    private String house;               //	住所（G）---------------------OK
    private String postalcode;          //	邮政编码（G）-----------------OK
    private String pripid;              //	主体身份代码--------------不能修改
    private String entityId;            //	当事人主键----------------不能修改
    private String regOrganId;          //	个体/企业登记机关---------不能修改
    private String etpsTypeGb;          //	企业类型------------------不能修改

    public String getAssort() {
        return assort;
    }

    public void setAssort(String assort) {
        this.assort = assort;
    }

    public String getIsDomination() {
        return isDomination;
    }

    public void setIsDomination(String isDomination) {
        this.isDomination = isDomination;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getWorkunit() {
        return workunit;
    }

    public void setWorkunit(String workunit) {
        this.workunit = workunit;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getPripid() {
        return pripid;
    }

    public void setPripid(String pripid) {
        this.pripid = pripid;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
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
