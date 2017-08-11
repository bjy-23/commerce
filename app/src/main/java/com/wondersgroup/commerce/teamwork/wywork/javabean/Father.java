package com.wondersgroup.commerce.teamwork.wywork.javabean;

import java.util.List;

public class Father {
	String checkType;
	String matterId;
	String matterName;
	String parentMatterId;
	List<Child> childMatter;
	int checkFlag = 2;
	
	

	public int getCheckFlag() {
		return checkFlag;
	}

	public void setCheckFlag(int checkFlag) {
		this.checkFlag = checkFlag;
	}

	public String getParentMatterId() {
		return parentMatterId;
	}

	public void setParentMatterId(String parentMatterId) {
		this.parentMatterId = parentMatterId;
	}

	public String getCheckType() {
		return checkType;
	}

	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}

	public List<Child> getChildMatter() {
		return childMatter;
	}

	public void setChildMatter(List<Child> childMatter) {
		this.childMatter = childMatter;
	}

	public String getMatterId() {
		return matterId;
	}

	public void setMatterId(String matterId) {
		this.matterId = matterId;
	}

	public String getMatterName() {
		return matterName;
	}

	public void setMatterName(String matterName) {
		this.matterName = matterName;
	}

}
