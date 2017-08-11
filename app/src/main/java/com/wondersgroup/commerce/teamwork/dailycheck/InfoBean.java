package com.wondersgroup.commerce.teamwork.dailycheck;



import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class InfoBean {

	LinkedHashMap<String, String> appEntInfoVo = new LinkedHashMap<String, String>();
	LinkedHashMap<String, String> etpsInfoVo;
	AppRecordContent appRecordContent = new AppRecordContent();
	AppRecordContentVo appRecordContentVo = new AppRecordContentVo();
	AppCheckInfo appCheckInfo = new AppCheckInfo();
	List<AppRecordDetail> appRecordDetails = new ArrayList<AppRecordDetail>();
	LinkedHashMap<String, String> deal;
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
	LinkedHashMap<String, String> etpsDic;
	LinkedHashMap<String, String> adBehavior;
	LinkedHashMap<String, String> brandRegUse;
	LinkedHashMap<String, String> businessStatus;
	LinkedHashMap<String, String> financialSystem;
	LinkedHashMap<String, String> innerManageSystem;
	LinkedHashMap<String, String> abbuseFormmer;
	LinkedHashMap<String, String> sex;

	String trdScope;
	String trdScopeChg;
	String checkedNumber;
	String checkedName;
	String appRecordDetailStr = "";

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

	List<BookBean> userList = new ArrayList<BookBean>();
	List<Father> checkMatters = new ArrayList<Father>();
	LinkedHashMap<String, String> checkMode;

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

	public String getTrdScope() {
		return trdScope;
	}

	public void setTrdScope(String trdScope) {
		this.trdScope = trdScope;
	}

	public String getTrdScopeChg() {
		return trdScopeChg;
	}

	public void setTrdScopeChg(String trdScopeChg) {
		this.trdScopeChg = trdScopeChg;
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

	public List<BookBean> getUserList() {
		return userList;
	}

	public void setUserList(List<BookBean> userList) {
		this.userList = userList;
	}

	public List<Father> getCheckMatters() {
		return checkMatters;
	}

	public void setCheckMatters(List<Father> checkMatters) {
		this.checkMatters = checkMatters;
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

	public LinkedHashMap<String, String> getArrangeDep() {
		return arrangeDep;
	}

	public void setArrangeDep(LinkedHashMap<String, String> arrangeDep) {
		this.arrangeDep = arrangeDep;
	}

	public LinkedHashMap<String, String> getEtpsDic() {
		return etpsDic;
	}

	public void setEtpsDic(LinkedHashMap<String, String> etpsDic) {
		this.etpsDic = etpsDic;
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

	public LinkedHashMap<String, String> getEtpsInfoVo() {
		return etpsInfoVo;
	}

	public void setEtpsInfoVo(LinkedHashMap<String, String> etpsInfoVo) {
		this.etpsInfoVo = etpsInfoVo;
	}

	public LinkedHashMap<String, String> getAppEntInfoVo() {
		return appEntInfoVo;
	}

	public void setAppEntInfoVo(LinkedHashMap<String, String> appEntInfoVo) {
		this.appEntInfoVo = appEntInfoVo;
	}

	public AppRecordContent getAppRecordContent() {
		return appRecordContent;
	}

	public void setAppRecordContent(AppRecordContent appRecordContent) {
		this.appRecordContent = appRecordContent;
	}

	public AppCheckInfo getAppCheckInfo() {
		return appCheckInfo;
	}

	public void setAppCheckInfo(AppCheckInfo appCheckInfo) {
		this.appCheckInfo = appCheckInfo;
	}

	public List<AppRecordDetail> getAppRecordDetails() {
		return appRecordDetails;
	}

	public void setAppRecordDetails(List<AppRecordDetail> appRecordDetails) {
		this.appRecordDetails = appRecordDetails;
	}

	public AppRecordContentVo getAppRecordContentVo() {
		return appRecordContentVo;
	}

	public void setAppRecordContentVo(AppRecordContentVo appRecordContentVo) {
		this.appRecordContentVo = appRecordContentVo;
	}

	public String getAppRecordDetailStr() {
		return appRecordDetailStr;
	}

	public void setAppRecordDetailStr(String appRecordDetailStr) {
		this.appRecordDetailStr = appRecordDetailStr;
	}

}
