package com.wondersgroup.commerce.model;

import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2017/3/21.
 */

public class ProcedureCaseIllegalCodeResultObject {

    private String code;
    private String message;
    private List<ProcedureCaseActnItemsVo> actnItemsVo;

    public List<ProcedureCaseActnItemsVo> getActnItemsVo() {
        return actnItemsVo;
    }

    public void setActnItemsVo(List<ProcedureCaseActnItemsVo> actnItemsVo) {
        this.actnItemsVo = actnItemsVo;
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
