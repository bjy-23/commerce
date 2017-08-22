package com.wondersgroup.commerce.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kangrenhui on 2015/12/10.
 */
public class SwTypeBean {

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
        private List<Doctype> doctype = new ArrayList<Doctype>();

        /**
         * @return The doctype
         */
        public List<Doctype> getDoctype() {
            return doctype;
        }

        /**
         * @param doctype The doctype
         */
        public void setDoctype(List<Doctype> doctype) {
            this.doctype = doctype;
        }

        public class Doctype {

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