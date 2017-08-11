package com.wondersgroup.commerce.model.yn;

import java.util.List;

/**
 * Created by kangrenhui on 2016/3/17.
 */
public class SearchListBean {

    public class Result {
        private String recordId;

        private String checkDate;

        private String etpsName;

        private String tmpFlag;

        private String checkType;

        private String etpsId;

        public void setRecordId(String recordId) {
            this.recordId = recordId;
        }

        public String getRecordId() {
            return this.recordId;
        }

        public void setCheckDate(String checkDate) {
            this.checkDate = checkDate;
        }

        public String getCheckDate() {
            return this.checkDate;
        }

        public void setEtpsName(String etpsName) {
            this.etpsName = etpsName;
        }

        public String getEtpsName() {
            return this.etpsName;
        }

        public void setTmpFlag(String tmpFlag) {
            this.tmpFlag = tmpFlag;
        }

        public String getTmpFlag() {
            return this.tmpFlag;
        }

        public void setCheckType(String checkType) {
            this.checkType = checkType;
        }

        public String getCheckType() {
            return this.checkType;
        }

        public void setEtpsId(String etpsId) {
            this.etpsId = etpsId;
        }

        public String getEtpsId() {
            return this.etpsId;
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
