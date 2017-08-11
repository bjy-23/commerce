package com.wondersgroup.commerce.fgdj.bean;

import java.util.List;

/**
 * Created by bjy on 2017/5/2.
 */

public class FgdjEntList {
    private int currPageNo;//当前页数
    private int pageCount;//总页数
    private int totalRecord;//总条数
    private int pageSize;
    private List<FgdjEntListBean> resultList;

    public int getCurrPageNo() {
        return currPageNo;
    }

    public void setCurrPageNo(int currPageNo) {
        this.currPageNo = currPageNo;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<FgdjEntListBean> getResultList() {
        return resultList;
    }

    public void setResultList(List<FgdjEntListBean> resultList) {
        this.resultList = resultList;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }
}
