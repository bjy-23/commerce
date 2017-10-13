package com.wondersgroup.commerce.model;

/**
 * Created by admin on 2017/9/27.
 */

public class CaseTrademark {

    private String trademarkSerialNo;
    /**
     * 当事人ID
     */

    private String litigantId;
    /**
     * 违法行为ID
     */

    private String ilglUuid;
    /**
     * 线索号
     */

    private String clueNo;
    /**
     * 商标名称
     */

    private String trademarkName;
    /**
     * 重点涉案行业
     */

    private String involvedIndustry;
    /**
     * 商标类别
     */

    private String trademarkType;
    /**
     * 核定使用商品（服务）
     */

    private String approveGood;
    /**
     * 商标注册人
     */

    private String trademarkRgeUser;
    /**
     * 商标注册证号
     */

    private String trademarkRgeNo;
    /**
     * 商标有效日期（自）
     */

    private String validStartDate;
    /**
     * 商标有效日期（至）
     */

    private String validEndDate;
    /**
     * 商标注册人国别（地区）
     */

    private String trademarkRgeRegion;
    /**
     * 案件发生环节
     */

    private String caseLink;
    /**
     * 是否主要商标
     */

    private String isMain;
    /**
     * 侵权商品/服务种类
     */

    private String infringerGood;
    /**
     * 没收侵权商品（件）
     */

    private String confiscateGood;
    /**
     * 没收主要用于制造侵权商品的工具（件）
     */

    private String confiscateGoodTools;
    /**
     * 没收主要用于伪造注册商标标识的工具（件）
     */

    private String confiscateTrademarkTools;
    /**
     * 违法经营额（万元）
     */

    private String ilglAmount;
    /**
     * 是否侵犯驰名商标权益
     */

    private String isWellKnownTrademark;
    /**
     * 是否侵权地理标志专用权案件
     */

    private String isInvasionGeographyLogo;
    /**
     * 是否侵犯特殊标志所有权案件
     */

    private String isInvasionSpecialLogo;
    /**
     * 是否印制商标标识案件
     */

    private String isPrintTrademarkLogo;
    /**
     * 有效性
     */

    private String validity;
    /**
     * 新老标识
     */

    private String newFlg;


    public String getApproveGood() {
        return approveGood;
    }

    public void setApproveGood(String approveGood) {
        this.approveGood = approveGood;
    }

    public String getCaseLink() {
        return caseLink;
    }

    public void setCaseLink(String caseLink) {
        this.caseLink = caseLink;
    }

    public String getClueNo() {
        return clueNo;
    }

    public void setClueNo(String clueNo) {
        this.clueNo = clueNo;
    }

    public String getConfiscateGood() {
        return confiscateGood;
    }

    public void setConfiscateGood(String confiscateGood) {
        this.confiscateGood = confiscateGood;
    }

    public String getConfiscateGoodTools() {
        return confiscateGoodTools;
    }

    public void setConfiscateGoodTools(String confiscateGoodTools) {
        this.confiscateGoodTools = confiscateGoodTools;
    }

    public String getConfiscateTrademarkTools() {
        return confiscateTrademarkTools;
    }

    public void setConfiscateTrademarkTools(String confiscateTrademarkTools) {
        this.confiscateTrademarkTools = confiscateTrademarkTools;
    }

    public String getIlglAmount() {
        return ilglAmount;
    }

    public void setIlglAmount(String ilglAmount) {
        this.ilglAmount = ilglAmount;
    }

    public String getIlglUuid() {
        return ilglUuid;
    }

    public void setIlglUuid(String ilglUuid) {
        this.ilglUuid = ilglUuid;
    }

    public String getInfringerGood() {
        return infringerGood;
    }

    public void setInfringerGood(String infringerGood) {
        this.infringerGood = infringerGood;
    }

    public String getInvolvedIndustry() {
        return involvedIndustry;
    }

    public void setInvolvedIndustry(String involvedIndustry) {
        this.involvedIndustry = involvedIndustry;
    }

    public String getIsInvasionGeographyLogo() {
        return isInvasionGeographyLogo;
    }

    public void setIsInvasionGeographyLogo(String isInvasionGeographyLogo) {
        this.isInvasionGeographyLogo = isInvasionGeographyLogo;
    }

    public String getIsInvasionSpecialLogo() {
        return isInvasionSpecialLogo;
    }

    public void setIsInvasionSpecialLogo(String isInvasionSpecialLogo) {
        this.isInvasionSpecialLogo = isInvasionSpecialLogo;
    }

    public String getIsMain() {
        return isMain;
    }

    public void setIsMain(String isMain) {
        this.isMain = isMain;
    }

    public String getIsPrintTrademarkLogo() {
        return isPrintTrademarkLogo;
    }

    public void setIsPrintTrademarkLogo(String isPrintTrademarkLogo) {
        this.isPrintTrademarkLogo = isPrintTrademarkLogo;
    }

    public String getIsWellKnownTrademark() {
        return isWellKnownTrademark;
    }

    public void setIsWellKnownTrademark(String isWellKnownTrademark) {
        this.isWellKnownTrademark = isWellKnownTrademark;
    }

    public String getLitigantId() {
        return litigantId;
    }

    public void setLitigantId(String litigantId) {
        this.litigantId = litigantId;
    }

    public String getNewFlg() {
        return newFlg;
    }

    public void setNewFlg(String newFlg) {
        this.newFlg = newFlg;
    }

    public String getTrademarkName() {
        return trademarkName;
    }

    public void setTrademarkName(String trademarkName) {
        this.trademarkName = trademarkName;
    }

    public String getTrademarkRgeNo() {
        return trademarkRgeNo;
    }

    public void setTrademarkRgeNo(String trademarkRgeNo) {
        this.trademarkRgeNo = trademarkRgeNo;
    }

    public String getTrademarkRgeRegion() {
        return trademarkRgeRegion;
    }

    public void setTrademarkRgeRegion(String trademarkRgeRegion) {
        this.trademarkRgeRegion = trademarkRgeRegion;
    }

    public String getTrademarkRgeUser() {
        return trademarkRgeUser;
    }

    public void setTrademarkRgeUser(String trademarkRgeUser) {
        this.trademarkRgeUser = trademarkRgeUser;
    }

    public String getTrademarkSerialNo() {
        return trademarkSerialNo;
    }

    public void setTrademarkSerialNo(String trademarkSerialNo) {
        this.trademarkSerialNo = trademarkSerialNo;
    }

    public String getTrademarkType() {
        return trademarkType;
    }

    public void setTrademarkType(String trademarkType) {
        this.trademarkType = trademarkType;
    }

    public String getValidEndDate() {
        return validEndDate;
    }

    public void setValidEndDate(String validEndDate) {
        this.validEndDate = validEndDate;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public String getValidStartDate() {
        return validStartDate;
    }

    public void setValidStartDate(String validStartDate) {
        this.validStartDate = validStartDate;
    }
}
