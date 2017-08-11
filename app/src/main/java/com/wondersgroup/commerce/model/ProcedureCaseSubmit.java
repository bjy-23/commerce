package com.wondersgroup.commerce.model;

/**
 * Created by Lee on 2017/3/13.
 * 简易程序案件提交对象
 */
public class ProcedureCaseSubmit {

        CaseDetailVo caseDetailVo = new CaseDetailVo();            //案件基本情况信息
        ProcedureCaseLitigtInfoVo litigtInfoVo = new ProcedureCaseLitigtInfoVo();          //当事人基本情况信息
        ProcedureCasePunishVo     punishVo = new ProcedureCasePunishVo();              //处罚基本信息

        public CaseDetailVo getmCaseDetail() {
            return caseDetailVo;
        }

        public void setmCaseDetail(CaseDetailVo mCaseDetail) {
            this.caseDetailVo = mCaseDetail;
        }

        public ProcedureCaseLitigtInfoVo getmLitigtInfoVo() {
            return litigtInfoVo;
        }

        public void setmLitigtInfoVo(ProcedureCaseLitigtInfoVo mLitigtInfoVo) {
            this.litigtInfoVo = mLitigtInfoVo;
        }

        public ProcedureCasePunishVo getmPunishVo() {
            return punishVo;
        }

        public void setmPunishVo(ProcedureCasePunishVo mPunishVo) {
            this.punishVo = mPunishVo;
        }

        //案件基本情况信息
        public class CaseDetailVo{

            private String clueNo;              //	线索号
            private String caseName;            //  案件名称
            private String userIdMainName;      //  承办人
            private String ceCity;              //  案发地（G）所在市划
            private String cedistrictid;        //	案发地（G）所在区划
            private String caseSpot;            //	案发地点
            private String casesou;             //	案件来源
            private String caseVal;             //	案值估计
            private String caseReason;          //	案由(G)

            public String getCeCity() {
                return ceCity;
            }

            public void setCeCity(String ceCity) {
                this.ceCity = ceCity;
            }

            public String getClueNo() {
                return clueNo;
            }

            public void setClueNo(String clueNo) {
                this.clueNo = clueNo;
            }

            public String getCaseName() {
                return caseName;
            }

            public void setCaseName(String caseName) {
                this.caseName = caseName;
            }

            public String getUserIdMainName() {
                return userIdMainName;
            }

            public void setUserIdMainName(String userIdMainName) {
                this.userIdMainName = userIdMainName;
            }

            public String getCedistrictid() {
                return cedistrictid;
            }

            public void setCedistrictid(String cedistrictid) {
                this.cedistrictid = cedistrictid;
            }

            public String getCaseSpot() {
                return caseSpot;
            }

            public void setCaseSpot(String caseSpot) {
                this.caseSpot = caseSpot;
            }

            public String getCasesou() {
                return casesou;
            }

            public void setCasesou(String casesou) {
                this.casesou = casesou;
            }

            public String getCaseVal() {
                return caseVal;
            }

            public void setCaseVal(String caseVal) {
                this.caseVal = caseVal;
            }

            public String getCaseReason() {
                return caseReason;
            }

            public void setCaseReason(String caseReason) {
                this.caseReason = caseReason;
            }
        }
}
