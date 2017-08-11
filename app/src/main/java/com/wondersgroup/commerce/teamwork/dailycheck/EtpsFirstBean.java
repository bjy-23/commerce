package com.wondersgroup.commerce.teamwork.dailycheck;

import java.util.ArrayList;
import java.util.List;

public class EtpsFirstBean {
	List<EtpsBean> result = new ArrayList<EtpsBean>();
	String message;
	int code;

	public List<EtpsBean> getResult() {
		return result;
	}

	public void setResult(List<EtpsBean> result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
