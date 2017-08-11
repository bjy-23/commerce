package com.wondersgroup.commerce.model.yn;

import java.util.List;

/**
 * Created by 薛定猫 on 16-5-13.
 */
public class DealInfoBean {
    private UserInfoBean loginUserInfo;
    private CnAccuse cnAccuse;
    private CnProcess cnProcess;
    private List<CnUpload> cnUploads;

    public UserInfoBean getLoginUserInfo() {
        return loginUserInfo;
    }

    public void setLoginUserInfo(UserInfoBean loginUserInfo) {
        this.loginUserInfo = loginUserInfo;
    }

    public CnAccuse getCnAccuse() {
        return cnAccuse;
    }

    public void setCnAccuse(CnAccuse cnAccuse) {
        this.cnAccuse = cnAccuse;
    }

    public CnProcess getCnProcess() {
        return cnProcess;
    }

    public void setCnProcess(CnProcess cnProcess) {
        this.cnProcess = cnProcess;
    }

    public List<CnUpload> getCnUploads() {
        return cnUploads;
    }

    public void setCnUploads(List<CnUpload> cnUploads) {
        this.cnUploads = cnUploads;
    }
}
