package com.wondersgroup.commerce.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kangrenhui on 2015/12/9.
 */
public class GwjsBean {

    private String message;
    private String code;
    private Result result;

    /**
     * @return The message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message The message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return The code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code The code
     */
    public void setCode(String code) {
        this.code = code;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public class Result{
        private List<Result_> result = new ArrayList<Result_>();
        private Integer currPageNo;
        private Integer pageCount;
        private Integer pageSize;
        private Integer totalRecord;
        private String deptId;
        private String userId;


        public List<Result_> getResult() {
            return result;
        }


        public void setResult(List<Result_> result) {
            this.result = result;
        }

        /**
         * @return The currPageNo
         */
        public Integer getCurrPageNo() {
            return currPageNo;
        }

        /**
         * @param currPageNo The currPageNo
         */
        public void setCurrPageNo(Integer currPageNo) {
            this.currPageNo = currPageNo;
        }

        /**
         * @return The pageCount
         */
        public Integer getPageCount() {
            return pageCount;
        }

        /**
         * @param pageCount The pageCount
         */
        public void setPageCount(Integer pageCount) {
            this.pageCount = pageCount;
        }

        /**
         * @return The pageSize
         */
        public Integer getPageSize() {
            return pageSize;
        }

        /**
         * @param pageSize The pageSize
         */
        public void setPageSize(Integer pageSize) {
            this.pageSize = pageSize;
        }

        /**
         * @return The totalRecord
         */
        public Integer getTotalRecord() {
            return totalRecord;
        }

        /**
         * @param totalRecord The totalRecord
         */
        public void setTotalRecord(Integer totalRecord) {
            this.totalRecord = totalRecord;
        }

        /**
         * @return The deptId
         */
        public String getDeptId() {
            return deptId;
        }

        /**
         * @param deptId The deptId
         */
        public void setDeptId(String deptId) {
            this.deptId = deptId;
        }

        /**
         * @return The userId
         */
        public String getUserId() {
            return userId;
        }

        /**
         * @param userId The userId
         */
        public void setUserId(String userId) {
            this.userId = userId;
        }

        public class Result_ {

            private String createAtDateStr;
            private String docId;
            private String flowUuid;
            private String title;
            private String type;

            /**
             * @return The createAt
             */
            public String getCreateAt() {
                return createAtDateStr;
            }

            /**
             * @param createAt The createAt
             */
            public void setCreateAt(String createAt) {
                this.createAtDateStr = createAt;
            }

            /**
             * @return The docId
             */
            public String getDocId() {
                return docId;
            }

            /**
             * @param docId The docId
             */
            public void setDocId(String docId) {
                this.docId = docId;
            }

            /**
             * @return The flowUuid
             */
            public String getFlowUuid() {
                return flowUuid;
            }

            /**
             * @param flowUuid The flowUuid
             */
            public void setFlowUuid(String flowUuid) {
                this.flowUuid = flowUuid;
            }

            /**
             * @return The title
             */
            public String getTitle() {
                return title;
            }

            /**
             * @param title The title
             */
            public void setTitle(String title) {
                this.title = title;
            }

            /**
             * @return The type
             */
            public String getType() {
                return type;
            }

            /**
             * @param type The type
             */
            public void setType(String type) {
                this.type = type;
            }

        }
    }
}
