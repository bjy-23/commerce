package com.wondersgroup.commerce.teamwork.wywork.javabean;

public class KeyValue {
	String key;
	String value;
	Boolean isTitle = false;
	Boolean isCheck=false;
	
	

	public Boolean getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(Boolean isCheck) {
		this.isCheck = isCheck;
	}

	public Boolean getIsTitle() {
		return isTitle;
	}

	public void setIsTitle(Boolean isTitle) {
		this.isTitle = isTitle;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
