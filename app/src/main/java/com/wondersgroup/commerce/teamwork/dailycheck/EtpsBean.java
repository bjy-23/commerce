package com.wondersgroup.commerce.teamwork.dailycheck;

public class EtpsBean {
	String etpsId;
	String etpsName;
	String recordId;
	String tmpFlag;
	String checkType;
	String checkDate;
	String abbuseLocation = "";
	String abbusePerson = "";

	public String getAbbuseLocation() {
		return abbuseLocation;
	}

	public void setAbbuseLocation(String abbuseLocation) {
		this.abbuseLocation = abbuseLocation;
	}

	public String getAbbusePerson() {
		return abbusePerson;
	}

	public void setAbbusePerson(String abbusePerson) {
		this.abbusePerson = abbusePerson;
	}

	public String getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public String getTmpFlag() {
		return tmpFlag;
	}

	public void setTmpFlag(String tmpFlag) {
		this.tmpFlag = tmpFlag;
	}

	public String getCheckType() {
		return checkType;
	}

	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}

	public String getEtpsId() {
		return etpsId;
	}

	public void setEtpsId(String etpsId) {
		this.etpsId = etpsId;
	}

	public String getEtpsName() {
		return etpsName;
	}

	public void setEtpsName(String etpsName) {
		this.etpsName = etpsName;
	}

}
