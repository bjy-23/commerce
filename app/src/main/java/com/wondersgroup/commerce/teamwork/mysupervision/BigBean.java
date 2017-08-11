package com.wondersgroup.commerce.teamwork.mysupervision;

import com.wondersgroup.commerce.teamwork.dailycheck.BookBean;

import java.util.LinkedHashMap;
import java.util.List;

public class BigBean {
    String etpsId;
    String tradeScope;
    String checkedNumber;
    String checkedName;

    List<BookBean> userList;
    List<Father> checkMatters;
    LinkedHashMap<String, String> etpsInfoVo;
    LinkedHashMap<String, String> deal;
    LinkedHashMap<String, String> sex;
    LinkedHashMap<String, String> abbuseFormmer;
    LinkedHashMap<String, String> cardType;
    LinkedHashMap<String, String> caseFrom;
    LinkedHashMap<String, String> entityType;
    LinkedHashMap<String, String> legalBasis;
    LinkedHashMap<String, String> unLicensedType;
    LinkedHashMap<String, String> focusCatagory;
    LinkedHashMap<String, String> partyFormat;
    LinkedHashMap<String, String> partyType;
    LinkedHashMap<String, String> politicalStatus;
    LinkedHashMap<String, String> serviceMatter;
    LinkedHashMap<String, String> arrangeDep;
    LinkedHashMap<String, String> etpsDic = new LinkedHashMap<String, String>();
    LinkedHashMap<String, String> adBehavior;
    LinkedHashMap<String, String> brandRegUse;
    LinkedHashMap<String, String> businessStatus;
    LinkedHashMap<String, String> financialSystem;
    LinkedHashMap<String, String> innerManageSystem;
    LinkedHashMap<String, String> checkMode;
    LinkedHashMap<String, String> instituteMatter;

    public LinkedHashMap<String, String> getInstituteMatter() {
        return instituteMatter;
    }

    public void setInstituteMatter(LinkedHashMap<String, String> instituteMatter) {
        this.instituteMatter = instituteMatter;
    }

    public LinkedHashMap<String, String> getSex() {
        return sex;
    }

    public void setSex(LinkedHashMap<String, String> sex) {
        this.sex = sex;
    }

    public LinkedHashMap<String, String> getAbbuseFormmer() {
        return abbuseFormmer;
    }

    public void setAbbuseFormmer(LinkedHashMap<String, String> abbuseFormmer) {
        this.abbuseFormmer = abbuseFormmer;
    }

    public LinkedHashMap<String, String> getCheckMode() {
        return checkMode;
    }

    public void setCheckMode(LinkedHashMap<String, String> checkMode) {
        this.checkMode = checkMode;
    }

    public String getEtpsId() {
        return etpsId;
    }

    public void setEtpsId(String etpsId) {
        this.etpsId = etpsId;
    }

    public String getTradeScope() {
        return tradeScope;
    }

    public void setTradeScope(String tradeScope) {
        this.tradeScope = tradeScope;
    }

    public String getCheckedNumber() {
        return checkedNumber;
    }

    public void setCheckedNumber(String checkedNumber) {
        this.checkedNumber = checkedNumber;
    }

    public String getCheckedName() {
        return checkedName;
    }

    public void setCheckedName(String checkedName) {
        this.checkedName = checkedName;
    }

    public LinkedHashMap<String, String> getAdBehavior() {
        return adBehavior;
    }

    public void setAdBehavior(LinkedHashMap<String, String> adBehavior) {
        this.adBehavior = adBehavior;
    }

    public LinkedHashMap<String, String> getBrandRegUse() {
        return brandRegUse;
    }

    public void setBrandRegUse(LinkedHashMap<String, String> brandRegUse) {
        this.brandRegUse = brandRegUse;
    }

    public LinkedHashMap<String, String> getBusinessStatus() {
        return businessStatus;
    }

    public void setBusinessStatus(LinkedHashMap<String, String> businessStatus) {
        this.businessStatus = businessStatus;
    }

    public LinkedHashMap<String, String> getFinancialSystem() {
        return financialSystem;
    }

    public void setFinancialSystem(LinkedHashMap<String, String> financialSystem) {
        this.financialSystem = financialSystem;
    }

    public LinkedHashMap<String, String> getInnerManageSystem() {
        return innerManageSystem;
    }

    public void setInnerManageSystem(
            LinkedHashMap<String, String> innerManageSystem) {
        this.innerManageSystem = innerManageSystem;
    }

    public LinkedHashMap<String, String> getEtpsDic() {
        return etpsDic;
    }

    public void setEtpsDic(LinkedHashMap<String, String> etpsDic) {
        this.etpsDic = etpsDic;
    }

    public List<BookBean> getUserList() {
        return userList;
    }

    public void setUserList(List<BookBean> userList) {
        this.userList = userList;
    }

    public LinkedHashMap<String, String> getArrangeDep() {
        return arrangeDep;
    }

    public void setArrangeDep(LinkedHashMap<String, String> arrangeDep) {
        this.arrangeDep = arrangeDep;
    }

    public List<Father> getCheckMatters() {
        return checkMatters;
    }

    public void setCheckMatters(List<Father> checkMatters) {
        this.checkMatters = checkMatters;
    }

    public LinkedHashMap<String, String> getEtpsInfoVo() {
        return etpsInfoVo;
    }

    public void setEtpsInfoVo(LinkedHashMap<String, String> etpsInfoVo) {
        this.etpsInfoVo = etpsInfoVo;
    }

    public LinkedHashMap<String, String> getDeal() {
        return deal;
    }

    public void setDeal(LinkedHashMap<String, String> deal) {
        this.deal = deal;
    }

    public LinkedHashMap<String, String> getCardType() {
        return cardType;
    }

    public void setCardType(LinkedHashMap<String, String> cardType) {
        this.cardType = cardType;
    }

    public LinkedHashMap<String, String> getCaseFrom() {
        return caseFrom;
    }

    public void setCaseFrom(LinkedHashMap<String, String> caseFrom) {
        this.caseFrom = caseFrom;
    }

    public LinkedHashMap<String, String> getEntityType() {
        return entityType;
    }

    public void setEntityType(LinkedHashMap<String, String> entityType) {
        this.entityType = entityType;
    }

    public LinkedHashMap<String, String> getLegalBasis() {
        return legalBasis;
    }

    public void setLegalBasis(LinkedHashMap<String, String> legalBasis) {
        this.legalBasis = legalBasis;
    }

    public LinkedHashMap<String, String> getUnLicensedType() {
        return unLicensedType;
    }

    public void setUnLicensedType(LinkedHashMap<String, String> unLicensedType) {
        this.unLicensedType = unLicensedType;
    }

    public LinkedHashMap<String, String> getFocusCatagory() {
        return focusCatagory;
    }

    public void setFocusCatagory(LinkedHashMap<String, String> focusCatagory) {
        this.focusCatagory = focusCatagory;
    }

    public LinkedHashMap<String, String> getPartyFormat() {
        return partyFormat;
    }

    public void setPartyFormat(LinkedHashMap<String, String> partyFormat) {
        this.partyFormat = partyFormat;
    }

    public LinkedHashMap<String, String> getPartyType() {
        return partyType;
    }

    public void setPartyType(LinkedHashMap<String, String> partyType) {
        this.partyType = partyType;
    }

    public LinkedHashMap<String, String> getPoliticalStatus() {
        return politicalStatus;
    }

    public void setPoliticalStatus(LinkedHashMap<String, String> politicalStatus) {
        this.politicalStatus = politicalStatus;
    }

    public LinkedHashMap<String, String> getServiceMatter() {
        return serviceMatter;
    }

    public void setServiceMatter(LinkedHashMap<String, String> serviceMatter) {
        this.serviceMatter = serviceMatter;
    }

}
