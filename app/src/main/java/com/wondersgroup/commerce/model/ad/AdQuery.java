package com.wondersgroup.commerce.model.ad;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by root on 9/21/17.
 */

public class AdQuery {
    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AdQueryResult getResult() {
        return result;
    }

    public void setResult(AdQueryResult result) {
        this.result = result;
    }

    private AdQueryResult result;

    public static class AdQueryResult {
        private int currPageNo;
        private int pageCount;
        private int pageSize;

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

        public int getTotalRecord() {
            return totalRecord;
        }

        public void setTotalRecord(int totalRecord) {
            this.totalRecord = totalRecord;
        }

        public List<List<AdOp>> getResultList() {
            return resultList;
        }

        public void setResultList(List<List<AdOp>> resultList) {
            this.resultList = resultList;
        }

        private int totalRecord;
        private List<List<AdOp>> resultList;

    }

    public static class AdOp {
        private String bulicNo;//广告发布登记编号
        private String buentName;//单位名称
        private String opLoc;//单位地址

        public String getBulicNo() {
            return bulicNo;
        }

        public void setBulicNo(String bulicNo) {
            this.bulicNo = bulicNo;
        }

        public String getBuentName() {
            return buentName;
        }

        public void setBuentName(String buentName) {
            this.buentName = buentName;
        }

        public String getOpLoc() {
            return opLoc;
        }

        public void setOpLoc(String opLoc) {
            this.opLoc = opLoc;
        }

        public String getLinkMan() {
            return linkMan;
        }

        public void setLinkMan(String linkMan) {
            this.linkMan = linkMan;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getBuentId() {
            return buentId;
        }

        public void setBuentId(String buentId) {
            this.buentId = buentId;
        }

        private String linkMan;//联系人
        private String tel;//联系电话

        @SerializedName(value = "buentId", alternate = {"adBuentId"})
        private String buentId;//查询详情用到的主键
    }
}
