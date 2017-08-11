package com.wondersgroup.commerce.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016/2/1.
 */
public class CaseInvestigateListBean {

    private String code;
    private String message;
    private CaseInvestigateListBean.result result;
    private Object caseStatus;

    public CaseInvestigateListBean.result getResult() {
        return result;
    }

    public void setResult(CaseInvestigateListBean.result result) {
        this.result = result;
    }

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

    public class result {

        private int pageCount;

        private int totalRecord;

        private List<CaseInvestigateTitle> list = new ArrayList<CaseInvestigateTitle>();

        public List<CaseInvestigateTitle> getList() {
            return list;
        }

        public void setList(List<CaseInvestigateTitle> list) {
            this.list = list;
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

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

    }

    public Object getCaseStatus() {
        return caseStatus;
    }

    public void setCaseStatus(Object caseStatus) {
        this.caseStatus = caseStatus;
    }
}
