package com.wondersgroup.commerce.teamwork.dailycheck;

/**
 * 移动执法工商人员登录后返回信息
 * 
 * @author quanpeng
 * 
 */
public class LoginUserInfo {

	private String userId = "";

	private String userName = "";

	private String loginName = "";

	private String deptId = "";

	private String deptName = "";

	private String organId = "";

	private String organName = "";

	public LoginUserInfo() {

	}

	// public LoginUserInfo(String userId, String userName, String loginName,
	// String deptId, String deptName, String organId, String organName) {
	// super();
	// this.userId = userId;
	// this.userName = userName;
	// this.loginName = loginName;
	// this.deptId = deptId;
	// this.deptName = deptName;
	// this.organId = organId;
	// this.organName = organName;
	// }

	public String getUserId() {
		return userId.trim();
	}

	public void setUserId(String userId) {
		this.userId = userId.trim();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getOrganId() {
		return organId;
	}

	public void setOrganId(String organId) {
		this.organId = organId;
	}

	public String getOrganName() {
		return organName;
	}

	public void setOrganName(String organName) {
		this.organName = organName;
	}

}
