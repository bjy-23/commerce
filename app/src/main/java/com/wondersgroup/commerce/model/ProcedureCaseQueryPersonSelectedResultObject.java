package com.wondersgroup.commerce.model;

import java.util.List;

/**
 * Created by admin on 2017/3/21.
 * 5.3.4. 个体工商户选择
 */

public class ProcedureCaseQueryPersonSelectedResultObject {

    private String code;
    private String message;
    private ProcedureCaseLitigtInfoVo persVo;

    public ProcedureCaseLitigtInfoVo getPersVo() {
        return persVo;
    }

    public void setPersVo(ProcedureCaseLitigtInfoVo persVo) {
        this.persVo = persVo;
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
