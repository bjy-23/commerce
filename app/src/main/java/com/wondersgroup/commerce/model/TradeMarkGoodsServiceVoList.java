package com.wondersgroup.commerce.model;

/**
 * Created by admin on 2017/3/6.
 * 注册商标商品服务列表项
 */

public class TradeMarkGoodsServiceVoList {

    private String goodsServiceNumber;      //商品服务序号
    private String similarGroup;      //类似群
    private String goods;      //商品

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public String getGoodsServiceNumber() {
        return goodsServiceNumber;
    }

    public void setGoodsServiceNumber(String goodsServiceNumber) {
        this.goodsServiceNumber = goodsServiceNumber;
    }

    public String getSimilarGroup() {
        return similarGroup;
    }

    public void setSimilarGroup(String similarGroup) {
        this.similarGroup = similarGroup;
    }
}
