package com.wondersgroup.commerce.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kangrenhui on 2015/12/10.
 */
public class GwNx {

    private String message;
    private Result result;
    private String code;

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

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

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

    public class Result {
        private List<Result_> result = new ArrayList<Result_>();
        private List<SendList> sendList = new ArrayList<SendList>();

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
        public void setResult(List<Result_> result) {
            this.result = result;
        }

        /**
         *
         * @return
         * The sendList
         */
        public List<SendList> getSendList() {
            return sendList;
        }

        /**
         *
         * @param sendList
         * The sendList
         */
        public void setSendList(List<SendList> sendList) {
            this.sendList = sendList;
        }

        public class Result_ {

            private String createAtDate;
            private String createAtDateStr;
            private String docId;
            private String flowType;
            private String flowUuid;
            private String title;
            private String type;

            /**
             *
             * @return
             * The createAtDate
             */
            public String getCreateAtDate() {
                return createAtDate;
            }

            /**
             *
             * @param createAtDate
             * The createAtDate
             */
            public void setCreateAtDate(String createAtDate) {
                this.createAtDate = createAtDate;
            }

            /**
             *
             * @return
             * The createAtDateStr
             */
            public String getCreateAtDateStr() {
                return createAtDateStr;
            }

            /**
             *
             * @param createAtDateStr
             * The createAtDateStr
             */
            public void setCreateAtDateStr(String createAtDateStr) {
                this.createAtDateStr = createAtDateStr;
            }

            /**
             *
             * @return
             * The docId
             */
            public String getDocId() {
                return docId;
            }

            /**
             *
             * @param docId
             * The docId
             */
            public void setDocId(String docId) {
                this.docId = docId;
            }

            /**
             *
             * @return
             * The flowType
             */
            public String getFlowType() {
                return flowType;
            }

            /**
             *
             * @param flowType
             * The flowType
             */
            public void setFlowType(String flowType) {
                this.flowType = flowType;
            }

            /**
             *
             * @return
             * The flowUuid
             */
            public String getFlowUuid() {
                return flowUuid;
            }

            /**
             *
             * @param flowUuid
             * The flowUuid
             */
            public void setFlowUuid(String flowUuid) {
                this.flowUuid = flowUuid;
            }

            /**
             *
             * @return
             * The title
             */
            public String getTitle() {
                return title;
            }

            /**
             *
             * @param title
             * The title
             */
            public void setTitle(String title) {
                this.title = title;
            }

            /**
             *
             * @return
             * The type
             */
            public String getType() {
                return type;
            }

            /**
             *
             * @param type
             * The type
             */
            public void setType(String type) {
                this.type = type;
            }

        }

        public class SendList {

            private String createAtDate;
            private String createAtDateStr;
            private String docId;
            private String flowType;
            private String flowUuid;
            private String title;
            private String type;

            /**
             *
             * @return
             * The createAtDate
             */
            public String getCreateAtDate() {
                return createAtDate;
            }

            /**
             *
             * @param createAtDate
             * The createAtDate
             */
            public void setCreateAtDate(String createAtDate) {
                this.createAtDate = createAtDate;
            }

            /**
             *
             * @return
             * The createAtDateStr
             */
            public String getCreateAtDateStr() {
                return createAtDateStr;
            }

            /**
             *
             * @param createAtDateStr
             * The createAtDateStr
             */
            public void setCreateAtDateStr(String createAtDateStr) {
                this.createAtDateStr = createAtDateStr;
            }

            /**
             *
             * @return
             * The docId
             */
            public String getDocId() {
                return docId;
            }

            /**
             *
             * @param docId
             * The docId
             */
            public void setDocId(String docId) {
                this.docId = docId;
            }

            /**
             *
             * @return
             * The flowType
             */
            public String getFlowType() {
                return flowType;
            }

            /**
             *
             * @param flowType
             * The flowType
             */
            public void setFlowType(String flowType) {
                this.flowType = flowType;
            }

            /**
             *
             * @return
             * The flowUuid
             */
            public String getFlowUuid() {
                return flowUuid;
            }

            /**
             *
             * @param flowUuid
             * The flowUuid
             */
            public void setFlowUuid(String flowUuid) {
                this.flowUuid = flowUuid;
            }

            /**
             *
             * @return
             * The title
             */
            public String getTitle() {
                return title;
            }

            /**
             *
             * @param title
             * The title
             */
            public void setTitle(String title) {
                this.title = title;
            }

            /**
             *
             * @return
             * The type
             */
            public String getType() {
                return type;
            }

            /**
             *
             * @param type
             * The type
             */
            public void setType(String type) {
                this.type = type;
            }

        }
    }
}