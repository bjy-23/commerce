package com.wondersgroup.commerce.model;

import java.util.List;

/**
 * Created by admin on 2017/3/21.
 */

public class ProcedureCaseAICRequiryComResultObject {

    private String code;
    private String message;
    private List<ProcedureCaseAICItemsVo> getEtpsList;

    public List<ProcedureCaseAICItemsVo> getGetEtpsList() {
        return getEtpsList;
    }

    public void setGetEtpsList(List<ProcedureCaseAICItemsVo> getEtpsList) {
        this.getEtpsList = getEtpsList;
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
