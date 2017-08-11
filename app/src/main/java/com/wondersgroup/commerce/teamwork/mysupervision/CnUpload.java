package com.wondersgroup.commerce.teamwork.mysupervision;

/**
 * 上传附件
 * 
 * @author tangMinxiang
 */
public class CnUpload {

	// 登记序号,作主键用
	private String serialNo;

	// 登记编号
	private String caseId;

	// 上传环节
	private String actId = "0205000106";

	// 上传日期
	private String uploadDate;

	// 上传附件名称
	private String fileName;

	// 上传附件路径
	private String filePath;

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	public String getActId() {
		return actId;
	}

	public void setActId(String actId) {
		this.actId = actId;
	}

	public String getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(String uploadDate) {
		this.uploadDate = uploadDate;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public String toString() {
		return "CnUpload [serialNo=" + serialNo + ", caseId=" + caseId
				+ ", actId=" + actId + ", uploadDate=" + uploadDate
				+ ", fileName=" + fileName + ", filePath=" + filePath + "]";
	}

}