package com.wondersgroup.commerce.teamwork.myhwggdq;

/**
 * Created by jiajiangyi on 2016/4/26 0026.
 */
public class HwggdqDetailBean {


    /**
     * msg : 查询成功
     * values : {"equEndDate":"2016-02-08 23:59:59","adModeId":"","posKindId":"201","moneyCostStr":"1400000.0(人民币)","equStartDate":"2014-03-05 23:59:59","posTypeId":"01020505 ","customerName":"苏州新太阳置业有限公司 ","endDate":"2016-02-08 23:59:59","posOwnerName":"上海沪青平高速公路建设发展有限公司 ","createDate":"2014-03-13 15:13:29","appName":"\u201c太阳湖大花园\u201d","moneyCost":1400000,"contentTypeId":"0401 ","unitName":"上海海溢广告有限公司","startDate":"2014-03-13 23:59:59","adModeName":"双面体","posKindName":"","posTypeName":"高速（两侧）","crncyCostId":"002","contentTypeName":"","realNo":"20142900012 "}
     * result : 0000
     */

    private String msg;
    /**
     * equEndDate : 2016-02-08 23:59:59
     * adModeId :
     * posKindId : 201
     * moneyCostStr : 1400000.0(人民币)
     * equStartDate : 2014-03-05 23:59:59
     * posTypeId : 01020505
     * customerName : 苏州新太阳置业有限公司
     * endDate : 2016-02-08 23:59:59
     * posOwnerName : 上海沪青平高速公路建设发展有限公司
     * createDate : 2014-03-13 15:13:29
     * appName : “太阳湖大花园”
     * moneyCost : 1400000
     * contentTypeId : 0401
     * unitName : 上海海溢广告有限公司
     * startDate : 2014-03-13 23:59:59
     * adModeName : 双面体
     * posKindName :
     * posTypeName : 高速（两侧）
     * crncyCostId : 002
     * contentTypeName :
     * realNo : 20142900012
     */

    private ValuesBean values;
    private String result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ValuesBean getValues() {
        return values;
    }

    public void setValues(ValuesBean values) {
        this.values = values;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public static class ValuesBean {
        private String equEndDate;
        private String adModeId;
        private String posKindId;
        private String moneyCostStr;
        private String equStartDate;
        private String posTypeId;
        private String customerName;
        private String endDate;
        private String posOwnerName;
        private String createDate;
        private String appName;
        private int moneyCost;
        private String contentTypeId;
        private String unitName;
        private String startDate;
        private String adModeName;
        private String posKindName;
        private String posTypeName;
        private String crncyCostId;
        private String contentTypeName;
        private String realNo;

        public String getEquEndDate() {
            return equEndDate;
        }

        public void setEquEndDate(String equEndDate) {
            this.equEndDate = equEndDate;
        }

        public String getAdModeId() {
            return adModeId;
        }

        public void setAdModeId(String adModeId) {
            this.adModeId = adModeId;
        }

        public String getPosKindId() {
            return posKindId;
        }

        public void setPosKindId(String posKindId) {
            this.posKindId = posKindId;
        }

        public String getMoneyCostStr() {
            return moneyCostStr;
        }

        public void setMoneyCostStr(String moneyCostStr) {
            this.moneyCostStr = moneyCostStr;
        }

        public String getEquStartDate() {
            return equStartDate;
        }

        public void setEquStartDate(String equStartDate) {
            this.equStartDate = equStartDate;
        }

        public String getPosTypeId() {
            return posTypeId;
        }

        public void setPosTypeId(String posTypeId) {
            this.posTypeId = posTypeId;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public String getPosOwnerName() {
            return posOwnerName;
        }

        public void setPosOwnerName(String posOwnerName) {
            this.posOwnerName = posOwnerName;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getAppName() {
            return appName;
        }

        public void setAppName(String appName) {
            this.appName = appName;
        }

        public int getMoneyCost() {
            return moneyCost;
        }

        public void setMoneyCost(int moneyCost) {
            this.moneyCost = moneyCost;
        }

        public String getContentTypeId() {
            return contentTypeId;
        }

        public void setContentTypeId(String contentTypeId) {
            this.contentTypeId = contentTypeId;
        }

        public String getUnitName() {
            return unitName;
        }

        public void setUnitName(String unitName) {
            this.unitName = unitName;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getAdModeName() {
            return adModeName;
        }

        public void setAdModeName(String adModeName) {
            this.adModeName = adModeName;
        }

        public String getPosKindName() {
            return posKindName;
        }

        public void setPosKindName(String posKindName) {
            this.posKindName = posKindName;
        }

        public String getPosTypeName() {
            return posTypeName;
        }

        public void setPosTypeName(String posTypeName) {
            this.posTypeName = posTypeName;
        }

        public String getCrncyCostId() {
            return crncyCostId;
        }

        public void setCrncyCostId(String crncyCostId) {
            this.crncyCostId = crncyCostId;
        }

        public String getContentTypeName() {
            return contentTypeName;
        }

        public void setContentTypeName(String contentTypeName) {
            this.contentTypeName = contentTypeName;
        }

        public String getRealNo() {
            return realNo;
        }

        public void setRealNo(String realNo) {
            this.realNo = realNo;
        }
    }
}
