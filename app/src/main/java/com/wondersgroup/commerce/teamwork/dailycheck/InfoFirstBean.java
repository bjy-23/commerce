package com.wondersgroup.commerce.teamwork.dailycheck;

public class InfoFirstBean {
	int code;
	String message;
	InfoBean result=new InfoBean();

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public InfoBean getResult() {
		return result;
	}

	public void setResult(InfoBean result) {
		this.result = result;
	}

}
