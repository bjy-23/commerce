package com.wondersgroup.commerce.teamwork.casedeal.bean;

/**
 * Created by bjy on 2017/9/22.
 */

public class CaseQueryBean {
    private String caseNo;//案件编号

    private String caseName;//案件名称

    private String regCaseDateShow1;//立案日期

    private String endCaseDateShow1;//结案日期

    private int caseDuration;//时限（小于10天字体变红）

    private String regStage2Show1;//案件阶段

    private String regStage2Show;//案件阶段

    private String transactOrganShow;//立案机关

    private String conficateAmount1;//罚没金额（元）

    private String inWareAmount1;//入库金额（元）

    private String punishOrgan;//处罚决定机关

    private String pendecissdate1Show;//处罚决定日期

    private String penDecWritNo;//处罚决定书文号

    private String finishCaseDateShow1;//办结日期

    private String clueNo;

    public String getRegStage2Show() {
        return regStage2Show;
    }

    public void setRegStage2Show(String regStage2Show) {
        this.regStage2Show = regStage2Show;
    }

    public String getClueNo() {
        return clueNo;
    }

    public void setClueNo(String clueNo) {
        this.clueNo = clueNo;
    }

    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public String getRegCaseDateShow1() {
        return regCaseDateShow1;
    }

    public void setRegCaseDateShow1(String regCaseDateShow1) {
        this.regCaseDateShow1 = regCaseDateShow1;
    }

    public String getEndCaseDateShow1() {
        return endCaseDateShow1;
    }

    public void setEndCaseDateShow1(String endCaseDateShow1) {
        this.endCaseDateShow1 = endCaseDateShow1;
    }

    public int getCaseDuration() {
        return caseDuration;
    }

    public void setCaseDuration(int caseDuration) {
        this.caseDuration = caseDuration;
    }

    public String getRegStage2Show1() {
        return regStage2Show1;
    }

    public void setRegStage2Show1(String regStage2Show1) {
        this.regStage2Show1 = regStage2Show1;
    }

    public String getTransactOrganShow() {
        return transactOrganShow;
    }

    public void setTransactOrganShow(String transactOrganShow) {
        this.transactOrganShow = transactOrganShow;
    }

    public String getConficateAmount1() {
        return conficateAmount1;
    }

    public void setConficateAmount1(String conficateAmount1) {
        this.conficateAmount1 = conficateAmount1;
    }

    public String getInWareAmount1() {
        return inWareAmount1;
    }

    public void setInWareAmount1(String inWareAmount1) {
        this.inWareAmount1 = inWareAmount1;
    }

    public String getPunishOrgan() {
        return punishOrgan;
    }

    public void setPunishOrgan(String punishOrgan) {
        this.punishOrgan = punishOrgan;
    }

    public String getPendecissdate1Show() {
        return pendecissdate1Show;
    }

    public void setPendecissdate1Show(String pendecissdate1Show) {
        this.pendecissdate1Show = pendecissdate1Show;
    }

    public String getPenDecWritNo() {
        return penDecWritNo;
    }

    public void setPenDecWritNo(String penDecWritNo) {
        this.penDecWritNo = penDecWritNo;
    }

    public String getFinishCaseDateShow1() {
        return finishCaseDateShow1;
    }

    public void setFinishCaseDateShow1(String finishCaseDateShow1) {
        this.finishCaseDateShow1 = finishCaseDateShow1;
    }
}
