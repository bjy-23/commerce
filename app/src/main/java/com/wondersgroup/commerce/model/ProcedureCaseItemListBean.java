package com.wondersgroup.commerce.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016/2/1.
 */
public class ProcedureCaseItemListBean {

    private String code;
    private String message;
    private ProcedureCaseItemListBean.result result;

    public ProcedureCaseItemListBean.result getResult() {
        return result;
    }

    public void setResult(ProcedureCaseItemListBean.result result) {
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

        private List<sampleCaseVoList> sampleCaseVoList = new ArrayList<sampleCaseVoList>();

        public List<sampleCaseVoList> getList() {
            return sampleCaseVoList;
        }

        public void setList(List<sampleCaseVoList> list) {
            this.sampleCaseVoList = list;
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
}
