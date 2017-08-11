package com.wondersgroup.commerce.model.hn;

/**
 * Created by yclli on 2016/1/5.
 */
public class LoginRes {

    private String message;
    private Result result;
    private String resultCode;

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

    /**
     *
     * @return
     * The resultCode
     */
    public String getResultCode() {
        return resultCode;
    }

    /**
     *
     * @param resultCode
     * The resultCode
     */
    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public class Result {

        private String userName;
        private String organId;
        private String deptName;
        private String deptId;
        private String todoCount;
        private String token;
        private String organName;

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
         * The todoCount
         */
        public String getTodoCount() {
            return todoCount;
        }

        /**
         *
         * @param todoCount
         * The todoCount
         */
        public void setTodoCount(String todoCount) {
            this.todoCount = todoCount;
        }

        /**
         *
         * @return
         * The token
         */
        public String getToken() {
            return token;
        }

        /**
         *
         * @param token
         * The token
         */
        public void setToken(String token) {
            this.token = token;
        }

        public String getDeptId() {
            return deptId;
        }

        public void setDeptId(String deptId) {
            this.deptId = deptId;
        }

        public String getOrganName() {
            return organName;
        }

        public void setOrganName(String organName) {
            this.organName = organName;
        }
    }

}


