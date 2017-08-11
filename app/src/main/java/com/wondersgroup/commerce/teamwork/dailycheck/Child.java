package com.wondersgroup.commerce.teamwork.dailycheck;

public class Child {
	
	private int childFlag=2;
	String checkType;
	String matterId;
	String matterName;
	String parentMatterId;

	
	
	public int getChildFlag() {
		return childFlag;
	}

	public void setChildFlag(int childFlag) {
		this.childFlag = childFlag;
	}

	public String getCheckType() {
		return checkType;
	}

	public void setCheckType(String checkType) {
		this.checkType = checkType;
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

	public String getParentMatterId() {
		return parentMatterId;
	}

	public void setParentMatterId(String parentMatterId) {
		this.parentMatterId = parentMatterId;
	}

}
