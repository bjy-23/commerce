package com.wondersgroup.commerce.ynwq.bean;

import java.util.ArrayList;

/**
 * Created by 1229 on 2016/1/5.
 */
public class EntPunishSetBean {
    private String caseNo;
    private String clueNo;
    private String entPunishUuid;
    private String entType;
    private String entUuid;
    private String illegAct;
    private String illegActType;
    private String illegPt;
    private String penType;
    private String personName;
    private String regNo;
    private String penPunishCon;
    private String penOrgan;
    private String penDecissDate;
    private String penDecNo;

    private ArrayList<EntPunishAltSet> entPunishAltSet = new ArrayList<EntPunishAltSet>();

    public String getPenDecNo() {
        return penDecNo;
    }

    public void setPenDecNo(String penDecNo) {
        this.penDecNo = penDecNo;
    }

    public String getPenOrgan() {
        return penOrgan;
    }

    public void setPenOrgan(String penOrgan) {
        this.penOrgan = penOrgan;
    }

    private EntPunishSetBean epsList;

    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    public String getClueNo() {
        return clueNo;
    }

    public void setClueNo(String clueNo) {
        this.clueNo = clueNo;
    }

    public String getEntPunishUuid() {
        return entPunishUuid;
    }

    public void setEntPunishUuid(String entPunishUuid) {
        this.entPunishUuid = entPunishUuid;
    }

    public String getEntType() {
        return entType;
    }

    public void setEntType(String entType) {
        this.entType = entType;
    }

    public String getEntUuid() {
        return entUuid;
    }

    public void setEntUuid(String entUuid) {
        this.entUuid = entUuid;
    }

    public String getIllegAct() {
        return illegAct;
    }

    public void setIllegAct(String illegAct) {
        this.illegAct = illegAct;
    }

    public String getIllegActType() {
        return illegActType;
    }

    public void setIllegActType(String illegActType) {
        this.illegActType = illegActType;
    }

    public String getIllegPt() {
        return illegPt;
    }

    public void setIllegPt(String illegPt) {
        this.illegPt = illegPt;
    }

    public String getPenType() {
        return penType;
    }

    public void setPenType(String penType) {
        this.penType = penType;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public EntPunishSetBean() {
        super();
        // TODO Auto-generated constructor stub
    }

    public EntPunishSetBean getEpsList() {
        return epsList;
    }

    public void setEpsList(EntPunishSetBean epsList) {
        this.epsList = epsList;
    }

    public String getPenPunishCon() {
        return penPunishCon;
    }

    public void setPenPunishCon(String penPunishCon) {
        this.penPunishCon = penPunishCon;
    }

    public String getPenDecissDate() {
        return penDecissDate;
    }

    public void setPenDecissDate(String penDecissDate) {
        this.penDecissDate = penDecissDate;
    }

    public ArrayList<EntPunishAltSet> getEntPunishAltSet() {
        return entPunishAltSet;
    }

    public void setEntPunishAltSet(ArrayList<EntPunishAltSet> entPunishAltSet) {
        this.entPunishAltSet = entPunishAltSet;
    }

}
