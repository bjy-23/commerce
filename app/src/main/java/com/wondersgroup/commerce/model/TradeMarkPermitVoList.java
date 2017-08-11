package com.wondersgroup.commerce.model;

/**
 * Created by admin on 2017/3/6.
 * 商标许可信息列表项
 */

public class TradeMarkPermitVoList {

    private String permitNumber;      //序号
    private String permitName;      //被许可人
    private String type;      //许可类型
    private String goods;       //许可使用商品
    private String startDate;    //许可期限（起）
    private String endDate;    //许可期限（止）

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public String getPermitName() {
        return permitName;
    }

    public void setPermitName(String permitName) {
        this.permitName = permitName;
    }

    public String getPermitNumber() {
        return permitNumber;
    }

    public void setPermitNumber(String permitNumber) {
        this.permitNumber = permitNumber;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
