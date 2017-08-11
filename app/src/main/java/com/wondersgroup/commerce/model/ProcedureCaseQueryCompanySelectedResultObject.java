package com.wondersgroup.commerce.model;

/**
 * Created by admin on 2017/3/21.
 * 5.3.4. 企业工商户选择
 */

public class ProcedureCaseQueryCompanySelectedResultObject {

    private String code;
    private String message;
    private ProcedureCaseCompanyItemsVo getEtpsVo;

    public ProcedureCaseCompanyItemsVo getGetEtpsVo() {
        return getEtpsVo;
    }

    public void setGetEtpsVo(ProcedureCaseCompanyItemsVo getEtpsVo) {
        this.getEtpsVo = getEtpsVo;
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
