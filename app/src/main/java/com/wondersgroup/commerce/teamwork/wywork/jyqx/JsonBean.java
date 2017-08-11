package com.wondersgroup.commerce.teamwork.wywork.jyqx;

public class JsonBean {
	private String msg;
	private String resultFlag;
	private String values;
	private String currentPageSize;
	private String result;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getResultFlag() {
		return resultFlag;
	}

	public String getCurrentPageSize() {
		return currentPageSize;
	}

	public void setCurrentPageSize(String currentPageSize) {
		this.currentPageSize = currentPageSize;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public void setResultFlag(String resultFlag) {
		this.resultFlag = resultFlag;
	}

	public String getValues() {
		return values;
	}

	public void setValues(String values) {
		this.values = values;
	}

	public JsonBean(String resultFlag, String values) {
		super();
		this.resultFlag = resultFlag;
		this.values = values;
	}

}
