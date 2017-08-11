package com.wondersgroup.commerce.model.ccjc;

/**
 * Created by 薛定猫 on 16-5-10.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResult {

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
    private Result result;
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

        @SerializedName("userName")
        @Expose
        private String userName;
        @SerializedName("userId")
        @Expose
        private String userId;
        @SerializedName("organId")
        @Expose
        private String organId;
        @SerializedName("organName")
        @Expose
        private String organName;
        @SerializedName("deptName")
        @Expose
        private String deptName;
        @SerializedName("deptId")
        @Expose
        private String deptId;

        /**
         *
         * @return
         * The userName
         */
        public String getUserName() {
            return userName;
        }

        /**
         *
         * @param userName
         * The userName
         */
        public void setUserName(String userName) {
            this.userName = userName;
        }

        /**
         *
         * @return
         * The userId
         */
        public String getUserId() {
            return userId;
        }

        /**
         *
         * @param userId
         * The userId
         */
        public void setUserId(String userId) {
            this.userId = userId;
        }

        /**
         *
         * @return
         * The organId
         */
        public String getOrganId() {
            return organId;
        }

        /**
         *
         * @param organId
         * The organId
         */
        public void setOrganId(String organId) {
            this.organId = organId;
        }

        /**
         *
         * @return
         * The organName
         */
        public String getOrganName() {
            return organName;
        }

        /**
         *
         * @param organName
         * The organName
         */
        public void setOrganName(String organName) {
            this.organName = organName;
        }

        /**
         *
         * @return
         * The deptName
         */
        public String getDeptName() {
            return deptName;
        }

        /**
         *
         * @param deptName
         * The deptName
         */
        public void setDeptName(String deptName) {
            this.deptName = deptName;
        }

        /**
         *
         * @return
         * The deptId
         */
        public String getDeptId() {
            return deptId;
        }

        /**
         *
         * @param deptId
         * The deptId
         */
        public void setDeptId(String deptId) {
            this.deptId = deptId;
        }

    }

}
