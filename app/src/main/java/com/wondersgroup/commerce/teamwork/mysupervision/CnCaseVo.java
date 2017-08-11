package com.wondersgroup.commerce.teamwork.mysupervision;

import com.wondersgroup.commerce.model.yn.CnAccuse;
import com.wondersgroup.commerce.model.yn.CnBasic;
import com.wondersgroup.commerce.model.yn.CnContent;
import com.wondersgroup.commerce.model.yn.CnOpinion;
import com.wondersgroup.commerce.model.yn.CnProcess;

import java.util.List;


public class CnCaseVo {

	private CnAccusePre cnAccusePrel;
	
	private LoginUserInfo loginUserInfo;

	private CnApp cnApp;

	private CnBasic cnBasic;

	private CnAccuse cnAccuse;

	private CnContent cnContent;

	private CnProcess cnProcess;

	 private List<CnUpload> cnUploads;
	 

	private List<CnOpinion> cnOpinions;


	public CnAccusePre getCnAccusePrel() {
		return cnAccusePrel;
	}

	public void setCnAccusePrel(CnAccusePre cnAccusePrel) {
		this.cnAccusePrel = cnAccusePrel;
	}

	public LoginUserInfo getLoginUserInfo() {
		return loginUserInfo;
	}

	public void setLoginUserInfo(LoginUserInfo loginUserInfo) {
		this.loginUserInfo = loginUserInfo;
	}



	public CnApp getCnApp() {
		return cnApp;
	}

	public void setCnApp(CnApp cnApp) {
		this.cnApp = cnApp;
	}

	public CnBasic getCnBasic() {
		return cnBasic;
	}

	public void setCnBasic(CnBasic cnBasic) {
		this.cnBasic = cnBasic;
	}

	public CnAccuse getCnAccuse() {
		return cnAccuse;
	}

	public void setCnAccuse(CnAccuse cnAccuse) {
		this.cnAccuse = cnAccuse;
	}

	public CnContent getCnContent() {
		return cnContent;
	}

	public void setCnContent(CnContent cnContent) {
		this.cnContent = cnContent;
	}

	public CnProcess getCnProcess() {
		return cnProcess;
	}

	public void setCnProcess(CnProcess cnProcess) {
		this.cnProcess = cnProcess;
	}

	public List<CnOpinion> getCnOpinions() {
		return cnOpinions;
	}

	public void setCnOpinions(List<CnOpinion> cnOpinions) {
		this.cnOpinions = cnOpinions;
	}

	
	public List<CnUpload> getCnUploads() {
		return cnUploads;
	}

	public void setCnUploads(List<CnUpload> cnUploads) {
		this.cnUploads = cnUploads;
	}

	
}
