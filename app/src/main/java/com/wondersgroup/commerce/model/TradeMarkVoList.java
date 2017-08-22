package com.wondersgroup.commerce.model;

/**
 * Created by admin on 2017/3/6.
 * 注册商标列表项
 */

public class TradeMarkVoList {

    private String brandName;      //	商标名称
    private String isAccept;      //	认领状态
    private String regNo;      //	注册证号
    private String registerName;      //	权利人名称
    private String chProposerAddr;      //	权利人地址
    private String areaOrganId;      //	管辖机关
    private String tmId;      //	商标id

    public String getAreaOrganId() {
        return areaOrganId;
    }

    public void setAreaOrganId(String areaOrganId) {
        this.areaOrganId = areaOrganId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getChProposerAddr() {
        return chProposerAddr;
    }

    public void setChProposerAddr(String chProposerAddr) {
        this.chProposerAddr = chProposerAddr;
    }

    public String getIsAccept() {
        return isAccept;
    }

    public void setIsAccept(String isAccept) {
        this.isAccept = isAccept;
    }

    public String getRegisterName() {
        return registerName;
    }

    public void setRegisterName(String registerName) {
        this.registerName = registerName;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getTmId() {
        return tmId;
    }

    public void setTmId(String tmId) {
        this.tmId = tmId;
    }
}
