package com.wondersgroup.commerce.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kangrenhui on 2015/12/10.
 */
public class FwTypeBean {

    private String code;
    private String message;
    private Result result;

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

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public class Result {
        private List<DocNoItem> docNoItems = new ArrayList<DocNoItem>();
        private List<DocTypeItem> docTypeItems = new ArrayList<DocTypeItem>();
        private List<DocClassItem> docClassItems = new ArrayList<DocClassItem>();
        private List<DeptMap> deptMap = new ArrayList<DeptMap>();

        /**
         * @return The docNoItems
         */
        public List<DocNoItem> getDocNoItems() {
            return docNoItems;
        }

        /**
         * @param docNoItems The docNoItems
         */
        public void setDocNoItems(List<DocNoItem> docNoItems) {
            this.docNoItems = docNoItems;
        }

        /**
         * @return The docTypeItems
         */
        public List<DocTypeItem> getDocTypeItems() {
            return docTypeItems;
        }

        /**
         * @param docTypeItems The docTypeItems
         */
        public void setDocTypeItems(List<DocTypeItem> docTypeItems) {
            this.docTypeItems = docTypeItems;
        }

        /**
         * @return The docClassItems
         */
        public List<DocClassItem> getDocClassItems() {
            return docClassItems;
        }

        /**
         * @param docClassItems The docClassItems
         */
        public void setDocClassItems(List<DocClassItem> docClassItems) {
            this.docClassItems = docClassItems;
        }

        /**
         * @return The deptMap
         */
        public List<DeptMap> getDeptMap() {
            return deptMap;
        }

        /**
         * @param deptMap The deptMap
         */
        public void setDeptMap(List<DeptMap> deptMap) {
            this.deptMap = deptMap;
        }


        public class DeptMap {

            private String foreignKey;
            private String key;
            private String value;

            /**
             * @return The foreignKey
             */
            public String getForeignKey() {
                return foreignKey;
            }

            /**
             * @param foreignKey The foreignKey
             */
            public void setForeignKey(String foreignKey) {
                this.foreignKey = foreignKey;
            }

            /**
             * @return The key
             */
            public String getKey() {
                return key;
            }

            /**
             * @param key The key
             */
            public void setKey(String key) {
                this.key = key;
            }

            /**
             * @return The value
             */
            public String getValue() {
                return value;
            }

            /**
             * @param value The value
             */
            public void setValue(String value) {
                this.value = value;
            }

        }


        public class DocClassItem {

            private String foreignKey;
            private String key;
            private String value;

            /**
             * @return The foreignKey
             */
            public String getForeignKey() {
                return foreignKey;
            }

            /**
             * @param foreignKey The foreignKey
             */
            public void setForeignKey(String foreignKey) {
                this.foreignKey = foreignKey;
            }

            /**
             * @return The key
             */
            public String getKey() {
                return key;
            }

            /**
             * @param key The key
             */
            public void setKey(String key) {
                this.key = key;
            }

            /**
             * @return The value
             */
            public String getValue() {
                return value;
            }

            /**
             * @param value The value
             */
            public void setValue(String value) {
                this.value = value;
            }

        }


        public class DocNoItem {

            private String foreignKey;
            private String key;
            private String value;

            /**
             * @return The foreignKey
             */
            public String getForeignKey() {
                return foreignKey;
            }

            /**
             * @param foreignKey The foreignKey
             */
            public void setForeignKey(String foreignKey) {
                this.foreignKey = foreignKey;
            }

            /**
             * @return The key
             */
            public String getKey() {
                return key;
            }

            /**
             * @param key The key
             */
            public void setKey(String key) {
                this.key = key;
            }

            /**
             * @return The value
             */
            public String getValue() {
                return value;
            }

            /**
             * @param value The value
             */
            public void setValue(String value) {
                this.value = value;
            }

        }


        public class DocTypeItem {

            private String foreignKey;
            private String key;
            private String value;

            /**
             * @return The foreignKey
             */
            public String getForeignKey() {
                return foreignKey;
            }

            /**
             * @param foreignKey The foreignKey
             */
            public void setForeignKey(String foreignKey) {
                this.foreignKey = foreignKey;
            }

            /**
             * @return The key
             */
            public String getKey() {
                return key;
            }

            /**
             * @param key The key
             */
            public void setKey(String key) {
                this.key = key;
            }

            /**
             * @return The value
             */
            public String getValue() {
                return value;
            }

            /**
             * @param value The value
             */
            public void setValue(String value) {
                this.value = value;
            }

        }
    }

}



