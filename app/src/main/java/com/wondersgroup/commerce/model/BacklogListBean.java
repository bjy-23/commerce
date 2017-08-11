package com.wondersgroup.commerce.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yclli on 2015/3/7.
 */
public class BacklogListBean {

    private Result result;
    private String code;
    private String message;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public class Result {

        private List<BackLogVoList> backLogVoList = new ArrayList<BackLogVoList>();
        /**
         *
         * @return
         * The backLogVoList
         */
        public List<BackLogVoList> getBackLogVoList() {
            return backLogVoList;
        }

        /**
         *
         * @param backLogVoList
         * The backLogVoList
         */
        public void setBackLogVoList(List<BackLogVoList> backLogVoList) {
            this.backLogVoList = backLogVoList;
        }

        public class BackLogVoList {

            private String activityId;
            private String activityName;
            private String activityUuid;
            private String createAt;
            private String flowHandleUserName;
            private String flowUuid;
            private String title;
            private String type;
            private String isUrgency;

            public String getIsUrgency() {
                return isUrgency;
            }

            public void setIsUrgency(String isUrgency) {
                this.isUrgency = isUrgency;
            }

            /**
             *
             * @return
             * The activityId
             */
            public String getActivityId() {
                return activityId;
            }

            /**
             *
             * @param activityId
             * The activityId
             */
            public void setActivityId(String activityId) {
                this.activityId = activityId;
            }

            /**
             *
             * @return
             * The activityName
             */
            public String getActivityName() {
                return activityName;
            }

            /**
             *
             * @param activityName
             * The activityName
             */
            public void setActivityName(String activityName) {
                this.activityName = activityName;
            }

            /**
             *
             * @return
             * The activityUuid
             */
            public String getActivityUuid() {
                return activityUuid;
            }

            /**
             *
             * @param activityUuid
             * The activityUuid
             */
            public void setActivityUuid(String activityUuid) {
                this.activityUuid = activityUuid;
            }

            /**
             *
             * @return
             * The createAt
             */
            public String getCreateAt() {
                return createAt;
            }

            /**
             *
             * @param createAt
             * The createAt
             */
            public void setCreateAt(String createAt) {
                this.createAt = createAt;
            }

            /**
             *
             * @return
             * The flowHandleUserName
             */
            public String getFlowHandleUserName() {
                return flowHandleUserName;
            }

            /**
             *
             * @param flowHandleUserName
             * The flowHandleUserName
             */
            public void setFlowHandleUserName(String flowHandleUserName) {
                this.flowHandleUserName = flowHandleUserName;
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