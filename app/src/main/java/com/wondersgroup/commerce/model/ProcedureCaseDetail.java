package com.wondersgroup.commerce.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Lee on 2017/3/13.
 * 简易程序案件
 */
public class ProcedureCaseDetail {

    private String code;
    private String message;
    private sampleCaseVo result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public sampleCaseVo getResult() {
        return result;
    }

    public void setResult(sampleCaseVo result) {
        this.result = result;
    }

    public class sampleCaseVo{

        CaseDetailVo caseDetailVo;            //案件基本情况信息
        ProcedureCaseLitigtInfoVo litigtInfoVo;          //当事人基本情况信息
        ProcedureCasePunishVo     punishVo;              //处罚基本信息
        ProcedureCaseDicVo dicVo;         //相关字典描述
        List<ProcedureCaseAttachMentVo> attachList;

        public List<ProcedureCaseAttachMentVo> getAttachList() {
            return attachList;
        }

        public void setAttachList(List<ProcedureCaseAttachMentVo> attachList) {
            this.attachList = attachList;
        }

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

        public ProcedureCaseDicVo getmDicVo() {
            return dicVo;
        }

        public void setmDicVo(ProcedureCaseDicVo mDicVo) {
            this.dicVo = mDicVo;
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

//        public class ProcedureCaseLitigtInfoVo

    }

}
