package com.wondersgroup.commerce.model.ccjc;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 薛定猫 on 16-5-10.
 */
public class CCCXResult {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("pageCount")
    @Expose
    private Integer pageCount;
    @SerializedName("result")
    @Expose
    private List<Result> result = new ArrayList<Result>();
    @SerializedName("totalRecord")
    @Expose
    private Integer totalRecord;

    /**
     *
     * @return
     * The code
     */
    public String getCode() {
        return code;
    }

    /**
     *
     * @param code
     * The code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     *
     * @return
     * The message
     */
    public String getMessage() {
        return message;
    }

    /**
     *
     * @param message
     * The message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     *
     * @return
     * The pageCount
     */
    public Integer getPageCount() {
        return pageCount;
    }

    /**
     *
     * @param pageCount
     * The pageCount
     */
    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    /**
     *
     * @return
     * The result
     */
    public List<Result> getResult() {
        return result;
    }

    /**
     *
     * @param result
     * The result
     */
    public void setResult(List<Result> result) {
        this.result = result;
    }

    /**
     *
     * @return
     * The totalRecord
     */
    public Integer getTotalRecord() {
        return totalRecord;
    }

    /**
     *
     * @param totalRecord
     * The totalRecord
     */
    public void setTotalRecord(Integer totalRecord) {
        this.totalRecord = totalRecord;
    }

    public class Result {

        @SerializedName("entName")
        @Expose
        private String entName;
        @SerializedName("uniScid")
        @Expose
        private String uniScid;
        @SerializedName("regNo")
        @Expose
        private String regNo;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("flowStatus")
        @Expose
        private String flowStatus;
        @SerializedName("entId")
        @Expose
        private String entId;
        @SerializedName("checkId")
        @Expose
        private String checkId;

        /**
         *
         * @return
         * The entName
         */
        public String getEntName() {
            return entName;
        }

        /**
         *
         * @param entName
         * The entName
         */
        public void setEntName(String entName) {
            this.entName = entName;
        }

        /**
         *
         * @return
         * The uniScid
         */
        public String getUniScid() {
            return uniScid;
        }

        /**
         *
         * @param uniScid
         * The uniScid
         */
        public void setUniScid(String uniScid) {
            this.uniScid = uniScid;
        }

        /**
         *
         * @return
         * The regNo
         */
        public String getRegNo() {
            return regNo;
        }

        /**
         *
         * @param regNo
         * The regNo
         */
        public void setRegNo(String regNo) {
            this.regNo = regNo;
        }

        /**
         *
         * @return
         * The address
         */
        public String getAddress() {
            return address;
        }

        /**
         *
         * @param address
         * The address
         */
        public void setAddress(String address) {
            this.address = address;
        }

        /**
         *
         * @return
         * The flowStatus
         */
        public String getFlowStatus() {
            return flowStatus;
        }

        /**
         *
         * @param flowStatus
         * The flowStatus
         */
        public void setFlowStatus(String flowStatus) {
            this.flowStatus = flowStatus;
        }

        /**
         *
         * @return
         * The entId
         */
        public String getEntId() {
            return entId;
        }

        /**
         *
         * @param entId
         * The entId
         */
        public void setEntId(String entId) {
            this.entId = entId;
        }

        /**
         *
         * @return
         * The checkId
         */
        public String getCheckId() {
            return checkId;
        }

        /**
         *
         * @param checkId
         * The checkId
         */
        public void setCheckId(String checkId) {
            this.checkId = checkId;
        }

    }
}
