package com.wondersgroup.commerce.ynwq.bean;

import java.util.ArrayList;

/**
 * Created by 1229 on 2016/1/5.
 */
public class CompanyAllDataBean {
    private CompanyInfoBean companyInfoBean;
    private ArrayList<EntBlackSetBean> EntBlackSetList = new ArrayList<EntBlackSetBean>();
    private ArrayList<EntBranchSet> entBranchSetList = new ArrayList<EntBranchSet>();
    private ArrayList<EntClearSetBean> entClearSetList = new ArrayList<EntClearSetBean>();
    private ArrayList<EntInvestorSetBean> entInvestorSetList = new ArrayList<EntInvestorSetBean>();
    private ArrayList<EntMemberSetBean> entMemberSetList = new ArrayList<EntMemberSetBean>();
    private ArrayList<EntMortgageSetBean> entMortgageSetList = new ArrayList<EntMortgageSetBean>();
    private ArrayList<EntPledgeSetBean> entPledgeSetList = new ArrayList<EntPledgeSetBean>();
    private ArrayList<EntPunishSetBean> entPunishSetList = new ArrayList<EntPunishSetBean>();
    private ArrayList<EntSpotCheckSetBean> entSpotCheckSetList = new ArrayList<EntSpotCheckSetBean>();
    private ArrayList<EntChangeSet> entChangeSet = new ArrayList<EntChangeSet>();
    private ArrayList<EntExceptSet> entExceptSetList = new ArrayList<EntExceptSet>();
    private int type = 0;

    public CompanyInfoBean getCompanyInfoBean() {
        return companyInfoBean;
    }

    public void setCompanyInfoBean(CompanyInfoBean companyInfoBean) {
        this.companyInfoBean = companyInfoBean;
    }

    public ArrayList<EntBlackSetBean> getEntBlackSetList() {
        return EntBlackSetList;
    }

    public void setEntBlackSetList(ArrayList<EntBlackSetBean> entBlackSetList) {
        EntBlackSetList = entBlackSetList;
    }

    public ArrayList<EntBranchSet> getEntBranchSetList() {
        return entBranchSetList;
    }

    public void setEntBranchSetList(ArrayList<EntBranchSet> entBranchSetList) {
        this.entBranchSetList = entBranchSetList;
    }

    public ArrayList<EntClearSetBean> getEntClearSetList() {
        return entClearSetList;
    }

    public void setEntClearSetList(ArrayList<EntClearSetBean> entClearSetList) {
        this.entClearSetList = entClearSetList;
    }

    public ArrayList<EntInvestorSetBean> getEntInvestorSetList() {
        return entInvestorSetList;
    }

    public void setEntInvestorSetList(
            ArrayList<EntInvestorSetBean> entInvestorSetList) {
        this.entInvestorSetList = entInvestorSetList;
    }

    public ArrayList<EntMemberSetBean> getEntMemberSetList() {
        return entMemberSetList;
    }

    public void setEntMemberSetList(ArrayList<EntMemberSetBean> entMemberSetList) {
        this.entMemberSetList = entMemberSetList;
    }

    public ArrayList<EntMortgageSetBean> getEntMortgageSetList() {
        return entMortgageSetList;
    }

    public void setEntMortgageSetList(
            ArrayList<EntMortgageSetBean> entMortgageSetList) {
        this.entMortgageSetList = entMortgageSetList;
    }

    public ArrayList<EntPledgeSetBean> getEntPledgeSetList() {
        return entPledgeSetList;
    }

    public void setEntPledgeSetList(ArrayList<EntPledgeSetBean> entPledgeSetList) {
        this.entPledgeSetList = entPledgeSetList;
    }

    public ArrayList<EntPunishSetBean> getEntPunishSetList() {
        return entPunishSetList;
    }

    public void setEntPunishSetList(ArrayList<EntPunishSetBean> entPunishSetList) {
        this.entPunishSetList = entPunishSetList;
    }

    public ArrayList<EntSpotCheckSetBean> getEntSpotCheckSetList() {
        return entSpotCheckSetList;
    }

    public void setEntSpotCheckSetList(
            ArrayList<EntSpotCheckSetBean> entSpotCheckSetList) {
        this.entSpotCheckSetList = entSpotCheckSetList;
    }

    public CompanyAllDataBean() {
        super();
        // TODO Auto-generated constructor stub
    }

    public ArrayList<EntChangeSet> getEntChangeSet() {
        return entChangeSet;
    }

    public void setEntChangeSet(ArrayList<EntChangeSet> entChangeSet) {
        this.entChangeSet = entChangeSet;
    }

    public ArrayList<EntExceptSet> getEntExceptSetList() {
        return entExceptSetList;
    }

    public void setEntExceptSetList(ArrayList<EntExceptSet> entExceptSetList) {
        this.entExceptSetList = entExceptSetList;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}

