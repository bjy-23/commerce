
package com.wondersgroup.commerce.model.yn;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class CnBasic {

    @SerializedName("addr")
    @Expose
    private String addr;
    @SerializedName("caseId")
    @Expose
    private String caseId;
    @SerializedName("fixTel")
    @Expose
    private String fixTel;
    @SerializedName("handiSign")
    @Expose
    private String handiSign;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("perIde")
    @Expose
    private String perIde;
    @SerializedName("perType")
    @Expose
    private String perType;
    @SerializedName("sex")
    @Expose
    private String sex;
    @SerializedName("tel")
    @Expose
    private String tel;
    @SerializedName("workUnit")
    @Expose
    private String workUnit;

    /**
     * 
     * @return
     *     The addr
     */
    public String getAddr() {
        return addr;
    }

    /**
     * 
     * @param addr
     *     The addr
     */
    public void setAddr(String addr) {
        this.addr = addr;
    }

    /**
     * 
     * @return
     *     The caseId
     */
    public String getCaseId() {
        return caseId;
    }

    /**
     * 
     * @param caseId
     *     The caseId
     */
    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    /**
     * 
     * @return
     *     The fixTel
     */
    public String getFixTel() {
        return fixTel;
    }

    /**
     * 
     * @param fixTel
     *     The fixTel
     */
    public void setFixTel(String fixTel) {
        this.fixTel = fixTel;
    }

    /**
     * 
     * @return
     *     The handiSign
     */
    public String getHandiSign() {
        return handiSign;
    }

    /**
     * 
     * @param handiSign
     *     The handiSign
     */
    public void setHandiSign(String handiSign) {
        this.handiSign = handiSign;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The perIde
     */
    public String getPerIde() {
        return perIde;
    }

    /**
     * 
     * @param perIde
     *     The perIde
     */
    public void setPerIde(String perIde) {
        this.perIde = perIde;
    }

    /**
     * 
     * @return
     *     The perType
     */
    public String getPerType() {
        return perType;
    }

    /**
     * 
     * @param perType
     *     The perType
     */
    public void setPerType(String perType) {
        this.perType = perType;
    }

    /**
     * 
     * @return
     *     The sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * 
     * @param sex
     *     The sex
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * 
     * @return
     *     The tel
     */
    public String getTel() {
        return tel;
    }

    /**
     * 
     * @param tel
     *     The tel
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * 
     * @return
     *     The workUnit
     */
    public String getWorkUnit() {
        return workUnit;
    }

    /**
     * 
     * @param workUnit
     *     The workUnit
     */
    public void setWorkUnit(String workUnit) {
        this.workUnit = workUnit;
    }

}
