package com.wondersgroup.commerce.model.ccjc;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 薛定猫 on 16-5-10.
 */
public class CCOpnnResult {

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

        @SerializedName("resultName")
        @Expose
        private String resultName;
        @SerializedName("actionName")
        @Expose
        private String actionName;
        @SerializedName("staffDate")
        @Expose
        private String staffDate;
        @SerializedName("staffName")
        @Expose
        private String staffName;
        @SerializedName("opnnText")
        @Expose
        private String opnnText;

        /**
         *
         * @return
         * The resultName
         */
        public String getResultName() {
            return resultName;
        }

        /**
         *
         * @param resultName
         * The resultName
         */
        public void setResultName(String resultName) {
            this.resultName = resultName;
        }

        /**
         *
         * @return
         * The actionName
         */
        public String getActionName() {
            return actionName;
        }

        /**
         *
         * @param actionName
         * The actionName
         */
        public void setActionName(String actionName) {
            this.actionName = actionName;
        }

        /**
         *
         * @return
         * The staffDate
         */
        public String getStaffDate() {
            return staffDate;
        }

        /**
         *
         * @param staffDate
         * The staffDate
         */
        public void setStaffDate(String staffDate) {
            this.staffDate = staffDate;
        }

        /**
         *
         * @return
         * The staffName
         */
        public String getStaffName() {
            return staffName;
        }

        /**
         *
         * @param staffName
         * The staffName
         */
        public void setStaffName(String staffName) {
            this.staffName = staffName;
        }

        /**
         *
         * @return
         * The opnnText
         */
        public String getOpnnText() {
            return opnnText;
        }

        /**
         *
         * @param opnnText
         * The opnnText
         */
        public void setOpnnText(String opnnText) {
            this.opnnText = opnnText;
        }

    }
}
