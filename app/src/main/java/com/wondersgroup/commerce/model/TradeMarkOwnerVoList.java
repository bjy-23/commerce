package com.wondersgroup.commerce.model;

/**
 * Created by admin on 2017/3/6.
 * 注册商标共有人列表项
 */

public class TradeMarkOwnerVoList {

    private String ownerNumber;      //共有人序号
    private String chineseName;      //中文名字
    private String englishName;      //外文名字
    private String chineseAddress;       //中文地址
    private String enghlishAddress;    //外文地址

    public String getChineseAddress() {
        return chineseAddress;
    }

    public void setChineseAddress(String chineseAddress) {
        this.chineseAddress = chineseAddress;
    }

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public String getEnghlishAddress() {
        return enghlishAddress;
    }

    public void setEnghlishAddress(String enghlishAddress) {
        this.enghlishAddress = enghlishAddress;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getOwnerNumber() {
        return ownerNumber;
    }

    public void setOwnerNumber(String ownerNumber) {
        this.ownerNumber = ownerNumber;
    }
}
