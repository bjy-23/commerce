package com.wondersgroup.commerce.model.yn;

/**
 * Created by kangrenhui on 2016/3/17.
 */
public class RequestSearchList {
    private String checkType;
    private String organId;
    private String tmpFlag;
    private String submitUser;

    public String getCheckType() {
        return checkType;
    }

    public void setCheckType(String checkType) {
        this.checkType = checkType;
    }

    public String getOrganId() {
        return organId;
    }

    public void setOrganId(String organId) {
        this.organId = organId;
    }

    public String getTmpFlag() {
        return tmpFlag;
    }

    public void setTmpFlag(String tmpFlag) {
        this.tmpFlag = tmpFlag;
    }

    public String getSubmitUser() {
        return submitUser;
    }

    public void setSubmitUser(String submitUser) {
        this.submitUser = submitUser;
    }
}
