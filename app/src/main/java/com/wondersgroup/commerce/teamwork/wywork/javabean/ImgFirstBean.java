package com.wondersgroup.commerce.teamwork.wywork.javabean;


import com.wondersgroup.commerce.teamwork.wywork.javabeansend.CnUpload;

import java.util.List;

public class ImgFirstBean {

	List<CnUpload> result;
	String message;
	int code;

	public List<CnUpload> getResult() {
		return result;
	}

	public void setResult(List<CnUpload> result) {
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
