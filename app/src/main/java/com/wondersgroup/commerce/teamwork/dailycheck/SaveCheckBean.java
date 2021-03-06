package com.wondersgroup.commerce.teamwork.dailycheck;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class SaveCheckBean {

	private String trdScope = "";
	private List<CnUpload> appUploads = new ArrayList<CnUpload>();
	private AppCheckInfo appCheckInfo = new AppCheckInfo();
	private LinkedHashMap<String, String> appEntInfo = new LinkedHashMap<String, String>();
	private AppRecordContent appRecordContent = new AppRecordContent();
	private List<AppRecordDetail> appRecordDetail = new ArrayList<AppRecordDetail>();

	public String getTrdScope() {
		return trdScope;
	}

	public void setTrdScope(String trdScope) {
		this.trdScope = trdScope;
	}

	public List<CnUpload> getAppUploads() {
		return appUploads;
	}

	public void setAppUploads(List<CnUpload> appUploads) {
		this.appUploads = appUploads;
	}

	public AppCheckInfo getAppCheckInfo() {
		return appCheckInfo;
	}

	public void setAppCheckInfo(AppCheckInfo appCheckInfo) {
		this.appCheckInfo = appCheckInfo;
	}

	public LinkedHashMap<String, String> getAppEntInfo() {
		return appEntInfo;
	}

	public void setAppEntInfo(LinkedHashMap<String, String> appEntInfo) {
		this.appEntInfo = appEntInfo;
	}

	public AppRecordContent getAppRecordContent() {
		return appRecordContent;
	}

	public void setAppRecordContent(AppRecordContent appRecordContent) {
		this.appRecordContent = appRecordContent;
	}

	public List<AppRecordDetail> getAppRecordDetail() {
		return appRecordDetail;
	}

	public void setAppRecordDetail(List<AppRecordDetail> appRecordDetail) {
		this.appRecordDetail = appRecordDetail;
	}

	public class AppCheckInfo {
		String etpsId = "";
		String checkType = "";
		String phonenumber = "";
		String photo = "";
		String inspector = "";
		String memo = "";
		String inspection = "";
		String organId = "";
		String deptId = "";
		String checkDate = "";
		String checkedName = "";
		String checkedNumber = "";
		String tmpFlag = "";
		String gpsInfo = "";
		String recordId = "";
		String submitUser = "";

		public String getSubmitUser() {
			return submitUser;
		}

		public void setSubmitUser(String submitUser) {
			this.submitUser = submitUser;
		}

		public String getRecordId() {
			return recordId;
		}

		public void setRecordId(String recordId) {
			this.recordId = recordId;
		}

		public String getEtpsId() {
			return etpsId;
		}

		public void setEtpsId(String etpsId) {
			this.etpsId = etpsId;
		}

		public String getCheckType() {
			return checkType;
		}

		public void setCheckType(String checkType) {
			this.checkType = checkType;
		}

		public String getPhonenumber() {
			return phonenumber;
		}

		public void setPhonenumber(String phonenumber) {
			this.phonenumber = phonenumber;
		}

		public String getPhoto() {
			return photo;
		}

		public void setPhoto(String photo) {
			this.photo = photo;
		}

		public String getInspector() {
			return inspector;
		}

		public void setInspector(String inspector) {
			this.inspector = inspector;
		}

		public String getMemo() {
			return memo;
		}

		public void setMemo(String memo) {
			this.memo = memo;
		}

		public String getInspection() {
			return inspection;
		}

		public void setInspection(String inspection) {
			this.inspection = inspection;
		}

		public String getOrganId() {
			return organId;
		}

		public void setOrganId(String organId) {
			this.organId = organId;
		}

		public String getDeptId() {
			return deptId;
		}

		public void setDeptId(String deptId) {
			this.deptId = deptId;
		}

		public String getCheckDate() {
			return checkDate;
		}

		public void setCheckDate(String checkDate) {
			this.checkDate = checkDate;
		}

		public String getCheckedName() {
			return checkedName;
		}

		public void setCheckedName(String checkedName) {
			this.checkedName = checkedName;
		}

		public String getCheckedNumber() {
			return checkedNumber;
		}

		public void setCheckedNumber(String checkedNumber) {
			this.checkedNumber = checkedNumber;
		}

		public String getTmpFlag() {
			return tmpFlag;
		}

		public void setTmpFlag(String tmpFlag) {
			this.tmpFlag = tmpFlag;
		}

		public String getGpsInfo() {
			return gpsInfo;
		}

		public void setGpsInfo(String gpsInfo) {
			this.gpsInfo = gpsInfo;
		}

	}

	public class AppRecordContent {
		String checkType = "";
		String focusIndustryType = "";
		String focusCheckMode = "";
		String specialName = "";
		String specialArrangeOrgan = "";
		String specialFileName = "";
		String specialRegulateMatter = "";
		String problem = "";
		String treatment = "";
		String groupJoinTime = "";
		String groupScale = "";
		String groupChargeCharecter = "";
		String groupPartyDate = "";
		String groupPartyType = "";
		String groupPartyForm = "";
		String groupPartyNumber = "";
		String groupName = "";
		String groupUnionDate = "";
		String groupYoungDate = "";
		String groupWomenDate = "";
		String groupActivityItems = "";
		String groupActivityDetail = "";
		String groupProblems = "";
		String groupSuggestions = "";
		String serviceActivityItems = "";
		String serviceActivityDetail = "";
		String serviceProblems = "";
		String serviceSuggestions = "";
		String serviceBusinessStatus = "";
		String serviceFinancialSystem = "";
		String serviceAdBehavior = "";
		String serviceInnerMansys = "";
		String serviceBrandReguse = "";

		String abbuseSource = "";
		String abbusePerson = "";
		String abbuseSex = "";
		String abbuseAge = "";
		String abbuseCompany = "";
		String abbuseOccupation = "";
		String abbuseName = "";
		String abbuseNumber = "";
		String abbuseIdType = "";
		String abbuseIdNumber = "";
		String abbuseAddress = "";
		String abbuseLeaderCompany = "";
		String abbuseLeaderName = "";
		String abbuseLeaderRegNumber = "";
		String abbuseLeaderAddress = "";
		String abbuseLeaderNumber = "";
		String abbuseLocation = "";
		String abbuseDate = "";
		String abbuseValue = "";
		String abbuseScale = "";
		String abbuseIllegal = "";
		String abbuseType = "";
		String abbuseTitle = "";
		String abbuseBasis = "";
		String abbuseFormmer = "";

		public String getServiceBusinessStatus() {
			return serviceBusinessStatus;
		}

		public void setServiceBusinessStatus(String serviceBusinessStatus) {
			this.serviceBusinessStatus = serviceBusinessStatus;
		}

		public String getServiceFinancialSystem() {
			return serviceFinancialSystem;
		}

		public void setServiceFinancialSystem(String serviceFinancialSystem) {
			this.serviceFinancialSystem = serviceFinancialSystem;
		}

		public String getServiceAdBehavior() {
			return serviceAdBehavior;
		}

		public void setServiceAdBehavior(String serviceAdBehavior) {
			this.serviceAdBehavior = serviceAdBehavior;
		}

		public String getServiceInnerMansys() {
			return serviceInnerMansys;
		}

		public void setServiceInnerMansys(String serviceInnerMansys) {
			this.serviceInnerMansys = serviceInnerMansys;
		}

		public String getServiceBrandReguse() {
			return serviceBrandReguse;
		}

		public void setServiceBrandReguse(String serviceBrandReguse) {
			this.serviceBrandReguse = serviceBrandReguse;
		}

		public String getCheckType() {
			return checkType;
		}

		public void setCheckType(String checkType) {
			this.checkType = checkType;
		}

		public String getFocusIndustryType() {
			return focusIndustryType;
		}

		public void setFocusIndustryType(String focusIndustryType) {
			this.focusIndustryType = focusIndustryType;
		}

		public String getFocusCheckMode() {
			return focusCheckMode;
		}

		public void setFocusCheckMode(String focusCheckMode) {
			this.focusCheckMode = focusCheckMode;
		}

		public String getSpecialName() {
			return specialName;
		}

		public void setSpecialName(String specialName) {
			this.specialName = specialName;
		}

		public String getSpecialArrangeOrgan() {
			return specialArrangeOrgan;
		}

		public void setSpecialArrangeOrgan(String specialArrangeOrgan) {
			this.specialArrangeOrgan = specialArrangeOrgan;
		}

		public String getSpecialFileName() {
			return specialFileName;
		}

		public void setSpecialFileName(String specialFileName) {
			this.specialFileName = specialFileName;
		}

		public String getSpecialRegulateMatter() {
			return specialRegulateMatter;
		}

		public void setSpecialRegulateMatter(String specialRegulateMatter) {
			this.specialRegulateMatter = specialRegulateMatter;
		}

		public String getProblem() {
			return problem;
		}

		public void setProblem(String problem) {
			this.problem = problem;
		}

		public String getTreatment() {
			return treatment;
		}

		public void setTreatment(String treatment) {
			this.treatment = treatment;
		}

		public String getGroupJoinTime() {
			return groupJoinTime;
		}

		public void setGroupJoinTime(String groupJoinTime) {
			this.groupJoinTime = groupJoinTime;
		}

		public String getGroupScale() {
			return groupScale;
		}

		public void setGroupScale(String groupScale) {
			this.groupScale = groupScale;
		}

		public String getGroupChargeCharecter() {
			return groupChargeCharecter;
		}

		public void setGroupChargeCharecter(String groupChargeCharecter) {
			this.groupChargeCharecter = groupChargeCharecter;
		}

		public String getGroupPartyDate() {
			return groupPartyDate;
		}

		public void setGroupPartyDate(String groupPartyDate) {
			this.groupPartyDate = groupPartyDate;
		}

		public String getGroupPartyType() {
			return groupPartyType;
		}

		public void setGroupPartyType(String groupPartyType) {
			this.groupPartyType = groupPartyType;
		}

		public String getGroupPartyForm() {
			return groupPartyForm;
		}

		public void setGroupPartyForm(String groupPartyForm) {
			this.groupPartyForm = groupPartyForm;
		}

		public String getGroupPartyNumber() {
			return groupPartyNumber;
		}

		public void setGroupPartyNumber(String groupPartyNumber) {
			this.groupPartyNumber = groupPartyNumber;
		}

		public String getGroupName() {
			return groupName;
		}

		public void setGroupName(String groupName) {
			this.groupName = groupName;
		}

		public String getGroupUnionDate() {
			return groupUnionDate;
		}

		public void setGroupUnionDate(String groupUnionDate) {
			this.groupUnionDate = groupUnionDate;
		}

		public String getGroupYoungDate() {
			return groupYoungDate;
		}

		public void setGroupYoungDate(String groupYoungDate) {
			this.groupYoungDate = groupYoungDate;
		}

		public String getGroupWomenDate() {
			return groupWomenDate;
		}

		public void setGroupWomenDate(String groupWomenDate) {
			this.groupWomenDate = groupWomenDate;
		}

		public String getGroupActivityItems() {
			return groupActivityItems;
		}

		public void setGroupActivityItems(String groupActivityItems) {
			this.groupActivityItems = groupActivityItems;
		}

		public String getGroupActivityDetail() {
			return groupActivityDetail;
		}

		public void setGroupActivityDetail(String groupActivityDetail) {
			this.groupActivityDetail = groupActivityDetail;
		}

		public String getGroupProblems() {
			return groupProblems;
		}

		public void setGroupProblems(String groupProblems) {
			this.groupProblems = groupProblems;
		}

		public String getGroupSuggestions() {
			return groupSuggestions;
		}

		public void setGroupSuggestions(String groupSuggestions) {
			this.groupSuggestions = groupSuggestions;
		}

		public String getServiceActivityItems() {
			return serviceActivityItems;
		}

		public void setServiceActivityItems(String serviceActivityItems) {
			this.serviceActivityItems = serviceActivityItems;
		}

		public String getServiceActivityDetail() {
			return serviceActivityDetail;
		}

		public void setServiceActivityDetail(String serviceActivityDetail) {
			this.serviceActivityDetail = serviceActivityDetail;
		}

		public String getServiceProblems() {
			return serviceProblems;
		}

		public void setServiceProblems(String serviceProblems) {
			this.serviceProblems = serviceProblems;
		}

		public String getServiceSuggestions() {
			return serviceSuggestions;
		}

		public void setServiceSuggestions(String serviceSuggestions) {
			this.serviceSuggestions = serviceSuggestions;
		}

		public String getAbbuseSource() {
			return abbuseSource;
		}

		public void setAbbuseSource(String abbuseSource) {
			this.abbuseSource = abbuseSource;
		}

		public String getAbbusePerson() {
			return abbusePerson;
		}

		public void setAbbusePerson(String abbusePerson) {
			this.abbusePerson = abbusePerson;
		}

		public String getAbbuseSex() {
			return abbuseSex;
		}

		public void setAbbuseSex(String abbuseSex) {
			this.abbuseSex = abbuseSex;
		}

		public String getAbbuseAge() {
			return abbuseAge;
		}

		public void setAbbuseAge(String abbuseAge) {
			this.abbuseAge = abbuseAge;
		}

		public String getAbbuseCompany() {
			return abbuseCompany;
		}

		public void setAbbuseCompany(String abbuseCompany) {
			this.abbuseCompany = abbuseCompany;
		}

		public String getAbbuseOccupation() {
			return abbuseOccupation;
		}

		public void setAbbuseOccupation(String abbuseOccupation) {
			this.abbuseOccupation = abbuseOccupation;
		}

		public String getAbbuseName() {
			return abbuseName;
		}

		public void setAbbuseName(String abbuseName) {
			this.abbuseName = abbuseName;
		}

		public String getAbbuseNumber() {
			return abbuseNumber;
		}

		public void setAbbuseNumber(String abbuseNumber) {
			this.abbuseNumber = abbuseNumber;
		}

		public String getAbbuseIdType() {
			return abbuseIdType;
		}

		public void setAbbuseIdType(String abbuseIdType) {
			this.abbuseIdType = abbuseIdType;
		}

		public String getAbbuseIdNumber() {
			return abbuseIdNumber;
		}

		public void setAbbuseIdNumber(String abbuseIdNumber) {
			this.abbuseIdNumber = abbuseIdNumber;
		}

		public String getAbbuseAddress() {
			return abbuseAddress;
		}

		public void setAbbuseAddress(String abbuseAddress) {
			this.abbuseAddress = abbuseAddress;
		}

		public String getAbbuseLeaderCompany() {
			return abbuseLeaderCompany;
		}

		public void setAbbuseLeaderCompany(String abbuseLeaderCompany) {
			this.abbuseLeaderCompany = abbuseLeaderCompany;
		}

		public String getAbbuseLeaderName() {
			return abbuseLeaderName;
		}

		public void setAbbuseLeaderName(String abbuseLeaderName) {
			this.abbuseLeaderName = abbuseLeaderName;
		}

		public String getAbbuseLeaderRegNumber() {
			return abbuseLeaderRegNumber;
		}

		public void setAbbuseLeaderRegNumber(String abbuseLeaderRegNumber) {
			this.abbuseLeaderRegNumber = abbuseLeaderRegNumber;
		}

		public String getAbbuseLeaderAddress() {
			return abbuseLeaderAddress;
		}

		public void setAbbuseLeaderAddress(String abbuseLeaderAddress) {
			this.abbuseLeaderAddress = abbuseLeaderAddress;
		}

		public String getAbbuseLeaderNumber() {
			return abbuseLeaderNumber;
		}

		public void setAbbuseLeaderNumber(String abbuseLeaderNumber) {
			this.abbuseLeaderNumber = abbuseLeaderNumber;
		}

		public String getAbbuseLocation() {
			return abbuseLocation;
		}

		public void setAbbuseLocation(String abbuseLocation) {
			this.abbuseLocation = abbuseLocation;
		}

		public String getAbbuseDate() {
			return abbuseDate;
		}

		public void setAbbuseDate(String abbuseDate) {
			this.abbuseDate = abbuseDate;
		}

		public String getAbbuseValue() {
			return abbuseValue;
		}

		public void setAbbuseValue(String abbuseValue) {
			this.abbuseValue = abbuseValue;
		}

		public String getAbbuseScale() {
			return abbuseScale;
		}

		public void setAbbuseScale(String abbuseScale) {
			this.abbuseScale = abbuseScale;
		}

		public String getAbbuseIllegal() {
			return abbuseIllegal;
		}

		public void setAbbuseIllegal(String abbuseIllegal) {
			this.abbuseIllegal = abbuseIllegal;
		}

		public String getAbbuseType() {
			return abbuseType;
		}

		public void setAbbuseType(String abbuseType) {
			this.abbuseType = abbuseType;
		}

		public String getAbbuseTitle() {
			return abbuseTitle;
		}

		public void setAbbuseTitle(String abbuseTitle) {
			this.abbuseTitle = abbuseTitle;
		}

		public String getAbbuseBasis() {
			return abbuseBasis;
		}

		public void setAbbuseBasis(String abbuseBasis) {
			this.abbuseBasis = abbuseBasis;
		}

		public String getAbbuseFormmer() {
			return abbuseFormmer;
		}

		public void setAbbuseFormmer(String abbuseFormmer) {
			this.abbuseFormmer = abbuseFormmer;
		}

	}
}
