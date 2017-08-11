package com.wondersgroup.commerce.model;

import java.util.Map;

/**
 * Created by admin on 2017/3/21.
 */

public class ProcedureCaseIllegalSmalTypeResultObject {

    private String code;
    private String message;
    private Map<String, String> subMap;

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

    public Map<String, String> getSubMap() {
        return subMap;
    }

    public void setSubMap(Map<String, String> subMap) {
        this.subMap = subMap;
    }
}
