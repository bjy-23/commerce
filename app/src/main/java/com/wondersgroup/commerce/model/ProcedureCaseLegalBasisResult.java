package com.wondersgroup.commerce.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lee on 2016/3/2.
 * getLegalBasisList//定性/处罚依据（G）查询
 * 返回的结果
 */
public class ProcedureCaseLegalBasisResult {
    private String code;
    private String message;
    private List<ProcedureCaseLegalBasis> legalBasisList = new ArrayList<ProcedureCaseLegalBasis>();

    public String getCode() { return code; }

    public void setCode(String code) { this.code = code; }

    public String getMessage() { return message; }

    public void setMessage(String message) { this.message = message; }

    public List<ProcedureCaseLegalBasis> getLegalBasisList() {
        return legalBasisList;
    }

    public void setLegalBasisList(List<ProcedureCaseLegalBasis> legalBasisList) {
        this.legalBasisList = legalBasisList;
    }
}
