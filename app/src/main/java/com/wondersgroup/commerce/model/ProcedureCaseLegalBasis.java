package com.wondersgroup.commerce.model;

import java.io.Serializable;

/**
 * Created by admin on 2017/3/27.
 */

public class ProcedureCaseLegalBasis implements Serializable {

    private String ilglActId;       //	违法行为代码	String
    private String basisType;       //	违法行为类型	String
    private String lawCode;         //	定性/处罚依据代码	String
    private String lawContent;      //	定性/处罚依据内容	String
    private boolean isChecked;
    private boolean isMajorBasis;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public boolean isMajorBasis() {
        return isMajorBasis;
    }

    public void setMajorBasis(boolean majorBasis) {
        isMajorBasis = majorBasis;
    }

    public String getIlglActId() {
        return ilglActId;
    }

    public void setIlglActId(String ilglActId) {
        this.ilglActId = ilglActId;
    }

    public String getBasisType() {
        return basisType;
    }

    public void setBasisType(String basisType) {
        this.basisType = basisType;
    }

    public String getLawCode() {
        return lawCode;
    }

    public void setLawCode(String lawCode) {
        this.lawCode = lawCode;
    }

    public String getLawContent() {
        return lawContent;
    }

    public void setLawContent(String lawContent) {
        this.lawContent = lawContent;
    }
}
