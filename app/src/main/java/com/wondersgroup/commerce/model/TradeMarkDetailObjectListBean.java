package com.wondersgroup.commerce.model;

import com.google.gson.JsonElement;
import com.wondersgroup.commerce.teamwork.myxqzgdq.XqzgdqGLXXBean;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by admin on 2016/2/1.
 */
public class TradeMarkDetailObjectListBean {

    private String resultCode;
    private String message;
    private String totalRecord;
    private List<Assembles> result;

    public String getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(String totalRecord) {
        this.totalRecord = totalRecord;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Assembles> getResult() {
        return result;
    }

    public void setResult(List<Assembles> result) {
        this.result = result;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public class Assembles{
        private String name;
        private String type;
        private List<JsonElement> value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<JsonElement> getValue() {
            return value;
        }

        public void setValue(List<JsonElement> value) {
            this.value = value;
        }
    }
}
