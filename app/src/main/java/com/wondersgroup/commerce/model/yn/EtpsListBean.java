package com.wondersgroup.commerce.model.yn;

import java.util.List;

/**
 * Created by kangrenhui on 2016/3/17.
 */
public class EtpsListBean {
    public class Result {
        private String etpsId;

        private String etpsName;

        public void setEtpsId(String etpsId) {
            this.etpsId = etpsId;
        }

        public String getEtpsId() {
            return this.etpsId;
        }

        public void setEtpsName(String etpsName) {
            this.etpsName = etpsName;
        }

        public String getEtpsName() {
            return this.etpsName;
        }

    }

    private int code;

    private String message;

    private List<Result> result;

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    public List<Result> getResult() {
        return this.result;
    }


}
