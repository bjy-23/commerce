package com.wondersgroup.yngs.entity;

import java.util.ArrayList;

/**
 * Created by 1229 on 2016/1/5.
 */
public class DcdyDetail {
    private String entUuid;
    private String morRegCno;
    private String pefperForm;
    private String pefperTo;
    private String priclasecAm;
    private String priclasecKind;
    private String priclasecKindInterpreted;
    private String regDate;
    private String regOrg;
    private String regOrgInterpreted;
    private String remark;
    private String type;
    private String uuid;
    private String warCov;

    private ArrayList<EntMortgageCancel> entMortgageCancelList = new ArrayList<EntMortgageCancel>();
    private ArrayList<EntMortgageCancelSet> entMortgageCancelSetList = new ArrayList<EntMortgageCancelSet>();
    private ArrayList<EntMortgageChangeSet> entMortgageChangeSetList = new ArrayList<EntMortgageChangeSet>();
    private ArrayList<EntMortgageGuaSet> entMortgageGuaSetList = new ArrayList<EntMortgageGuaSet>();
    private ArrayList<EntMortgageMorSet> entMortgageMorSetList = new ArrayList<EntMortgageMorSet>();

    public String getEntUuid() {
        return entUuid;
    }

    public void setEntUuid(String entUuid) {
        this.entUuid = entUuid;
    }

    public String getMorRegCno() {
        return morRegCno;
    }

    public void setMorRegCno(String morRegCno) {
        this.morRegCno = morRegCno;
    }

    public String getPefperForm() {
        return pefperForm;
    }

    public void setPefperForm(String pefperForm) {
        this.pefperForm = pefperForm;
    }

    public String getPefperTo() {
        return pefperTo;
    }

    public void setPefperTo(String pefperTo) {
        this.pefperTo = pefperTo;
    }

    public String getPriclasecAm() {
        return priclasecAm;
    }

    public void setPriclasecAm(String priclasecAm) {
        this.priclasecAm = priclasecAm;
    }

    public String getPriclasecKind() {
        return priclasecKind;
    }

    public void setPriclasecKind(String priclasecKind) {
        this.priclasecKind = priclasecKind;
    }

    public String getPriclasecKindInterpreted() {
        return priclasecKindInterpreted;
    }

    public void setPriclasecKindInterpreted(String priclasecKindInterpreted) {
        this.priclasecKindInterpreted = priclasecKindInterpreted;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getRegOrg() {
        return regOrg;
    }

    public void setRegOrg(String regOrg) {
        this.regOrg = regOrg;
    }

    public String getRegOrgInterpreted() {
        return regOrgInterpreted;
    }

    public void setRegOrgInterpreted(String regOrgInterpreted) {
        this.regOrgInterpreted = regOrgInterpreted;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getWarCov() {
        return warCov;
    }

    public void setWarCov(String warCov) {
        this.warCov = warCov;
    }

    public ArrayList<EntMortgageCancel> getEntMortgageCancelList() {
        return entMortgageCancelList;
    }

    public void setEntMortgageCancelList(
            ArrayList<EntMortgageCancel> entMortgageCancelList) {
        this.entMortgageCancelList = entMortgageCancelList;
    }

    public ArrayList<EntMortgageCancelSet> getEntMortgageCancelSetList() {
        return entMortgageCancelSetList;
    }

    public void setEntMortgageCancelSetList(
            ArrayList<EntMortgageCancelSet> entMortgageCancelSetList) {
        this.entMortgageCancelSetList = entMortgageCancelSetList;
    }

    public ArrayList<EntMortgageChangeSet> getEntMortgageChangeSetList() {
        return entMortgageChangeSetList;
    }

    public void setEntMortgageChangeSetList(
            ArrayList<EntMortgageChangeSet> entMortgageChangeSetList) {
        this.entMortgageChangeSetList = entMortgageChangeSetList;
    }

    public ArrayList<EntMortgageGuaSet> getEntMortgageGuaSetList() {
        return entMortgageGuaSetList;
    }

    public void setEntMortgageGuaSetList(
            ArrayList<EntMortgageGuaSet> entMortgageGuaSetList) {
        this.entMortgageGuaSetList = entMortgageGuaSetList;
    }

    public ArrayList<EntMortgageMorSet> getEntMortgageMorSetList() {
        return entMortgageMorSetList;
    }

    public void setEntMortgageMorSetList(
            ArrayList<EntMortgageMorSet> entMortgageMorSetList) {
        this.entMortgageMorSetList = entMortgageMorSetList;
    }

}

