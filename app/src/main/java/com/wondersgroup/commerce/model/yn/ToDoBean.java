package com.wondersgroup.commerce.model.yn;

/**
 * Created by 薛定猫 on 2016/3/15.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ToDoBean {

    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("result")
    @Expose
    private Result result;

    /**
     *
     * @return
     * The code
     */
    public Integer getCode() {
        return code;
    }

    /**
     *
     * @param code
     * The code
     */
    public void setCode(Integer code) {
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
     * The result
     */
    public Result getResult() {
        return result;
    }

    /**
     *
     * @param result
     * The result
     */
    public void setResult(Result result) {
        this.result = result;
    }

    public class Result {
        @SerializedName("totalRecord")
        private String totalRecord;
        @SerializedName("count")
        @Expose
        private Integer count;
        @SerializedName("pageCount")
        @Expose
        private Integer pageCount;
        @SerializedName("pageNo")
        @Expose
        private Integer pageNo;
        @SerializedName("pageSize")
        @Expose
        private Integer pageSize;
        @SerializedName(value = "result",alternate = {"resultList"})
        @Expose
        private List<Result_> result = new ArrayList<Result_>();

        public String getTotalRecord() {
            return totalRecord;
        }

        public void setTotalRecord(String totalRecord) {
            this.totalRecord = totalRecord;
        }

        /**
         *
         * @return
         * The count
         */
        public Integer getCount() {
            return count;
        }

        /**
         *
         * @param count
         * The count
         */
        public void setCount(Integer count) {
            this.count = count;
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
         * The pageNo
         */
        public Integer getPageNo() {
            return pageNo;
        }

        /**
         *
         * @param pageNo
         * The pageNo
         */
        public void setPageNo(Integer pageNo) {
            this.pageNo = pageNo;
        }

        /**
         *
         * @return
         * The pageSize
         */
        public Integer getPageSize() {
            return pageSize;
        }

        /**
         *
         * @param pageSize
         * The pageSize
         */
        public void setPageSize(Integer pageSize) {
            this.pageSize = pageSize;
        }

        /**
         *
         * @return
         * The result
         */
        public List<Result_> getResult() {
            return result;
        }

        /**
         *
         * @param result
         * The result
         */
        public void setResultList(List<Result_> result) {
            this.result = result;
        }

    }
    public class Result_ {

        @SerializedName("accuseName")
        @Expose
        private String accuseName;
        @SerializedName("incForm")
        @Expose
        private String incForm;
        @SerializedName("basicName")
        @Expose
        private String basicName;
        @SerializedName("caseId")
        @Expose
        private String caseId;
        @SerializedName("regDate")
        @Expose
        private String regDate;

        /**
         *
         * @return
         * The accuseName
         */
        public String getAccuseName() {
            return accuseName;
        }

        /**
         *
         * @param accuseName
         * The accuseName
         */
        public void setAccuseName(String accuseName) {
            this.accuseName = accuseName;
        }

        /**
         *
         * @return
         * The incForm
         */
        public String getIncForm() {
            return incForm;
        }

        /**
         *
         * @param incForm
         * The incForm
         */
        public void setIncForm(String incForm) {
            this.incForm = incForm;
        }

        /**
         *
         * @return
         * The basicName
         */
        public String getBasicName() {
            return basicName;
        }

        /**
         *
         * @param basicName
         * The basicName
         */
        public void setBasicName(String basicName) {
            this.basicName = basicName;
        }

        /**
         *
         * @return
         * The caseId
         */
        public String getCaseId() {
            return caseId;
        }

        /**
         *
         * @param caseId
         * The caseId
         */
        public void setCaseId(String caseId) {
            this.caseId = caseId;
        }

        /**
         *
         * @return
         * The regDate
         */
        public String getRegDate() {
            return regDate;
        }

        /**
         *
         * @param regDate
         * The regDate
         */
        public void setRegDate(String regDate) {
            this.regDate = regDate;
        }

    }
}
