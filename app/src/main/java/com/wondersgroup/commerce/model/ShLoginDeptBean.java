package com.wondersgroup.commerce.model;

import java.util.List;

/**
 * Created by kangrenhui on 2016/1/27.
 */
public class ShLoginDeptBean {
    private String msg;

    private List<Values> values;

    private String result;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setValues(List<Values> values) {
        this.values = values;
    }

    public List<Values> getValues() {
        return this.values;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return this.result;
    }

    public class Values {
        private String name;

        private String code;

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getCode() {
            return this.code;
        }
    }
}
