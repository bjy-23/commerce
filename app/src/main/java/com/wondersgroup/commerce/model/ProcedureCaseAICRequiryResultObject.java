package com.wondersgroup.commerce.model;

import java.util.List;

/**
 * Created by admin on 2017/3/21.
 */

public class ProcedureCaseAICRequiryResultObject {

    private String code;
    private String message;
    private List<ProcedureCaseAICItemsVo> getPersList;

    public List<ProcedureCaseAICItemsVo> getGetPersList() {
        return getPersList;
    }

    public void setGetPersList(List<ProcedureCaseAICItemsVo> getPersList) {
        this.getPersList = getPersList;
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

}
