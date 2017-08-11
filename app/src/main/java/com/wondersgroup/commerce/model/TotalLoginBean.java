package com.wondersgroup.commerce.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yclli on 2015/12/1.
 */
public class TotalLoginBean {

    private Result result;
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

        private String deptId;
        private String deptName;
        private String organId;
        private String organName;
        private String userId;
        private String userName;
        private String errorMsg;
        private List<String> deptIdInfo = new ArrayList<String>();

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

        public String getErrorMsg() {
            return errorMsg;
        }

        public void setErrorMsg(String errorMsg) {
            this.errorMsg = errorMsg;
        }

        /**
         *
         * @return
         * The deptIdInfo
         */
        public List<String> getDeptIdInfo() {
            return deptIdInfo;
        }

        /**
         *
         * @param deptIdInfo
         * The deptIdInfo
         */
        public void setDeptIdInfo(List<String> deptIdInfo) {
            this.deptIdInfo = deptIdInfo;
        }
    }
}



