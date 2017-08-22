package com.wondersgroup.commerce.model;

import java.util.List;

/**
 * Created by admin on 2016/2/1.
 */
public class TradeMarkDictTypeListBean {

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

    public class Result{
        private List<KeyValue> dicTmType;   //商标类型字典表
        private List<KeyValue> registerType;//权利人类型字典表
        private List<KeyValue> acceptCondition;//认领状态字典表

        public List<KeyValue> getAcceptCondition() {
            return acceptCondition;
        }

        public void setAcceptCondition(List<KeyValue> acceptCondition) {
            this.acceptCondition = acceptCondition;
        }

        public List<KeyValue> getDicTmType() {
            return dicTmType;
        }

        public void setDicTmType(List<KeyValue> dicTmType) {
            this.dicTmType = dicTmType;
        }

        public List<KeyValue> getRegisterType() {
            return registerType;
        }

        public void setRegisterType(List<KeyValue> registerType) {
            this.registerType = registerType;
        }
    }
}
