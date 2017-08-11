package com.wondersgroup.commerce.teamwork.mysupervision;

public class CnAccusePre {
	// 登记序号,作主键用
	private String caseId = "";

	// 登记类型

	private String caseType = "";

	// 投诉方式

	private String acquireMode = "";

	// 消费者名称

	private String accuserName = "";

	// 消费者性别

	private String accuserGender = "";

	// 消费者地址

	private String accuserAddress = "";

	// 消费者联系方式

	private String accuserPhone = "";

	// 消费者邮编

	private String accuserZipcode = "";

	// 消费者类别

	private String accuserType = "";

	// 消费者身份

	private String accuserIdentity = "";

	// // 消费者邮箱
	// @Column(name = "ACCUSER_EMAIL")
	// private String accuserEmail;

	// 被诉单位名称

	private String accusedName = "";

	// 被诉单位地址

	private String accusedAddress = "";

	// 被诉单位邮编

	private String accusedZipcode = "";

	// 被诉单位联系电话

	private String accusedPhone = "";

	// 投诉商品服务名称

	private String goodsName = "";

	// 商品商标

	private String accuseBrandId = "";

	// 国产/进口

	private String ifDomestic = "";

	// 商品型号

	private String model = "";

	// 购买日期
	private String buyDate = "";

	// 凭证编号

	private String invoiceNo = "";

	// 价格,单位（万元）

	private Float price;

	// 发生时间（事发时间）

	private String occurDate = "";

	// 发生地所属区划

	private String occurAreaId = "";

	// 发生地点

	private String occurAddress = "";

	// 登记日期

	private String regDate = "";

	// 标识位

	private String flag = "";

	// 意见内容

	private String content = "";

	// 查询码

	private String searchCode = "";

	// 年龄

	private String age = "";

	// 所在行业类别

	private String ubIndType = "";

	// 商品服务类别

	private String obType = "";

	// 销售方式

	private String saleType = "";

	// 投诉问题类别/举报问题类别

	private String applBasQue = "";

	// 投诉问题描述

	private String accuseContent = "";

	// 是否需要回复

	private String replySign = "";

	// 咨询范围

	private String conlRange = "";

	// 接收处理机关

	private String acceptOrgan = "";

	// 交换标志

	private String editType = "";

	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	public String getCaseType() {
		return caseType;
	}

	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}

	public String getAcquireMode() {
		return acquireMode;
	}

	public void setAcquireMode(String acquireMode) {
		this.acquireMode = acquireMode;
	}

	public String getAccuserName() {
		return accuserName;
	}

	public void setAccuserName(String accuserName) {
		this.accuserName = accuserName;
	}

	public String getAccuserGender() {
		return accuserGender;
	}

	public void setAccuserGender(String accuserGender) {
		this.accuserGender = accuserGender;
	}

	public String getAccuserAddress() {
		return accuserAddress;
	}

	public void setAccuserAddress(String accuserAddress) {
		this.accuserAddress = accuserAddress;
	}

	public String getAccuserPhone() {
		return accuserPhone;
	}

	public void setAccuserPhone(String accuserPhone) {
		this.accuserPhone = accuserPhone;
	}

	public String getAccuserZipcode() {
		return accuserZipcode;
	}

	public void setAccuserZipcode(String accuserZipcode) {
		this.accuserZipcode = accuserZipcode;
	}

	public String getAccuserType() {
		return accuserType;
	}

	public void setAccuserType(String accuserType) {
		this.accuserType = accuserType;
	}

	public String getAccuserIdentity() {
		return accuserIdentity;
	}

	public void setAccuserIdentity(String accuserIdentity) {
		this.accuserIdentity = accuserIdentity;
	}

	public String getAccusedName() {
		return accusedName;
	}

	public void setAccusedName(String accusedName) {
		this.accusedName = accusedName;
	}

	public String getAccusedAddress() {
		return accusedAddress;
	}

	public void setAccusedAddress(String accusedAddress) {
		this.accusedAddress = accusedAddress;
	}

	public String getAccusedZipcode() {
		return accusedZipcode;
	}

	public void setAccusedZipcode(String accusedZipcode) {
		this.accusedZipcode = accusedZipcode;
	}

	public String getAccusedPhone() {
		return accusedPhone;
	}

	public void setAccusedPhone(String accusedPhone) {
		this.accusedPhone = accusedPhone;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getAccuseBrandId() {
		return accuseBrandId;
	}

	public void setAccuseBrandId(String accuseBrandId) {
		this.accuseBrandId = accuseBrandId;
	}

	public String getIfDomestic() {
		return ifDomestic;
	}

	public void setIfDomestic(String ifDomestic) {
		this.ifDomestic = ifDomestic;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(String buyDate) {
		this.buyDate = buyDate;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getOccurDate() {
		return occurDate;
	}

	public void setOccurDate(String occurDate) {
		this.occurDate = occurDate;
	}

	public String getOccurAreaId() {
		return occurAreaId;
	}

	public void setOccurAreaId(String occurAreaId) {
		this.occurAreaId = occurAreaId;
	}

	public String getOccurAddress() {
		return occurAddress;
	}

	public void setOccurAddress(String occurAddress) {
		this.occurAddress = occurAddress;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSearchCode() {
		return searchCode;
	}

	public void setSearchCode(String searchCode) {
		this.searchCode = searchCode;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getUbIndType() {
		return ubIndType;
	}

	public void setUbIndType(String ubIndType) {
		this.ubIndType = ubIndType;
	}

	public String getObType() {
		return obType;
	}

	public void setObType(String obType) {
		this.obType = obType;
	}

	public String getSaleType() {
		return saleType;
	}

	public void setSaleType(String saleType) {
		this.saleType = saleType;
	}

	public String getApplBasQue() {
		return applBasQue;
	}

	public void setApplBasQue(String applBasQue) {
		this.applBasQue = applBasQue;
	}

	public String getAccuseContent() {
		return accuseContent;
	}

	public void setAccuseContent(String accuseContent) {
		this.accuseContent = accuseContent;
	}

	public String getReplySign() {
		return replySign;
	}

	public void setReplySign(String replySign) {
		this.replySign = replySign;
	}

	public String getConlRange() {
		return conlRange;
	}

	public void setConlRange(String conlRange) {
		this.conlRange = conlRange;
	}

	public String getAcceptOrgan() {
		return acceptOrgan;
	}

	public void setAcceptOrgan(String acceptOrgan) {
		this.acceptOrgan = acceptOrgan;
	}

	public String getEditType() {
		return editType;
	}

	public void setEditType(String editType) {
		this.editType = editType;
	}

}
