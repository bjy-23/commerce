package com.wondersgroup.commerce.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yclli on 2015/12/2.
 */
public class TodoMessageBean {

    private Result result;

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

        private String count;
        private List<TodoMessageInfo> todoMessageInfo = new ArrayList<TodoMessageInfo>();

        /**
         *
         * @return
         * The count
         */
        public String getCount() {
            return count;
        }

        /**
         *
         * @param count
         * The count
         */
        public void setCount(String count) {
            this.count = count;
        }

        /**
         *
         * @return
         * The todoMessageInfo
         */
        public List<TodoMessageInfo> getTodoMessageInfo() {
            return todoMessageInfo;
        }

        /**
         *
         * @param todoMessageInfo
         * The todoMessageInfo
         */
        public void setTodoMessageInfo(List<TodoMessageInfo> todoMessageInfo) {
            this.todoMessageInfo = todoMessageInfo;
        }

    }

    public class TodoMessageInfo {

        private String appName;
        private String createDate;
        private String createUser;
        private String flowName;
        private String title;
        private String uuid;

        /**
         *
         * @return
         * The appName
         */
        public String getAppName() {
            return appName;
        }

        /**
         *
         * @param appName
         * The appName
         */
        public void setAppName(String appName) {
            this.appName = appName;
        }

        /**
         *
         * @return
         * The createDate
         */
        public String getCreateDate() {
            return createDate;
        }

        /**
         *
         * @param createDate
         * The createDate
         */
        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        /**
         *
         * @return
         * The createUser
         */
        public String getCreateUser() {
            return createUser;
        }

        /**
         *
         * @param createUser
         * The createUser
         */
        public void setCreateUser(String createUser) {
            this.createUser = createUser;
        }

        /**
         *
         * @return
         * The flowName
         */
        public String getFlowName() {
            return flowName;
        }

        /**
         *
         * @param flowName
         * The flowName
         */
        public void setFlowName(String flowName) {
            this.flowName = flowName;
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
         * The uuid
         */
        public String getUuid() {
            return uuid;
        }

        /**
         *
         * @param uuid
         * The uuid
         */
        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

    }

}


