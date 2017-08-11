package com.wondersgroup.commerce.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lee on 2016/3/2.
 * queryMyCase//简易程序案件查询
 * 返回的结果
 */
public class ProcedureCaseQueryResult {
    private String code;
    private String message;
    private int pageCount;
    private int totalRecord;
    private Condition condition;
    private List<sampleCaseVoList> result = new ArrayList<sampleCaseVoList>();

    private class Condition {

        private String caseName;
        private String caseNo;
        private String caseStatus;
        private int currentPage;
        private String regDateBegin;
        private String regDateEnd;
        private String suitProcedure;

        public String getCaseName() {
            return caseName;
        }

        public void setCaseName(String caseName) {
            this.caseName = caseName;
        }

        public String getCaseNo() {
            return caseNo;
        }

        public void setCaseNo(String caseNo) {
            this.caseNo = caseNo;
        }

        public String getCaseStatus() {
            return caseStatus;
        }

        public void setCaseStatus(String caseStatus) {
            this.caseStatus = caseStatus;
        }

        public int getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public String getRegDateBegin() {
            return regDateBegin;
        }

        public void setRegDateBegin(String regDateBegin) {
            this.regDateBegin = regDateBegin;
        }

        public String getRegDateEnd() {
            return regDateEnd;
        }

        public void setRegDateEnd(String regDateEnd) {
            this.regDateEnd = regDateEnd;
        }

        public String getSuitProcedure() {
            return suitProcedure;
        }

        public void setSuitProcedure(String suitProcedure) {
            this.suitProcedure = suitProcedure;
        }
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {this.pageCount = pageCount;}

    public String getCode() { return code; }

    public void setCode(String code) { this.code = code; }

    public String getMessage() { return message; }

    public void setMessage(String message) { this.message = message; }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public List<sampleCaseVoList> getResult() {
        return result;
    }

    public void setResult(List<sampleCaseVoList> result) {
        this.result = result;
    }
}
