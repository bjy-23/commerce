package com.wondersgroup.commerce.teamwork.myhwggdq;

import java.util.List;

/**
 * Created by jiajiangyi on 2016/4/26 0026.
 */
public class HwggdqBean {

    public class Result {
        private String createOrganId;

        private String startDate;

        private String adAddress;

        private String createOrgan;

        private String adContent;

        private String endDate;

        private String appName;

        private String addressMain;

        private String adName;

        private String entityId;

        private String realNo;

        private String unitName;

        public void setCreateOrganId(String createOrganId){
            this.createOrganId = createOrganId;
        }
        public String getCreateOrganId(){
            return this.createOrganId;
        }
        public void setStartDate(String startDate){
            this.startDate = startDate;
        }
        public String getStartDate(){
            return this.startDate;
        }
        public void setAdAddress(String adAddress){
            this.adAddress = adAddress;
        }
        public String getAdAddress(){
            return this.adAddress;
        }
        public void setCreateOrgan(String createOrgan){
            this.createOrgan = createOrgan;
        }
        public String getCreateOrgan(){
            return this.createOrgan;
        }
        public void setAdContent(String adContent){
            this.adContent = adContent;
        }
        public String getAdContent(){
            return this.adContent;
        }
        public void setEndDate(String endDate){
            this.endDate = endDate;
        }
        public String getEndDate(){
            return this.endDate;
        }
        public void setAppName(String appName){
            this.appName = appName;
        }
        public String getAppName(){
            return this.appName;
        }
        public void setAddressMain(String addressMain){
            this.addressMain = addressMain;
        }
        public String getAddressMain(){
            return this.addressMain;
        }
        public void setAdName(String adName){
            this.adName = adName;
        }
        public String getAdName(){
            return this.adName;
        }
        public void setEntityId(String entityId){
            this.entityId = entityId;
        }
        public String getEntityId(){
            return this.entityId;
        }
        public void setRealNo(String realNo){
            this.realNo = realNo;
        }
        public String getRealNo(){
            return this.realNo;
        }
        public void setUnitName(String unitName){
            this.unitName = unitName;
        }
        public String getUnitName(){
            return this.unitName;
        }

    }
    public class Values {
        private int pageSize;

        private int start;

        private int nextPageNo;

        private int totalSize;

        private int currentPageSize;

        private int currentPageNo;

        private int startOfNextPage;

        private int end;

        private int totalPageCount;

        private List<Result> result ;

        private int startOfPreviousPage;

        public void setPageSize(int pageSize){
            this.pageSize = pageSize;
        }
        public int getPageSize(){
            return this.pageSize;
        }
        public void setStart(int start){
            this.start = start;
        }
        public int getStart(){
            return this.start;
        }
        public void setNextPageNo(int nextPageNo){
            this.nextPageNo = nextPageNo;
        }
        public int getNextPageNo(){
            return this.nextPageNo;
        }
        public void setTotalSize(int totalSize){
            this.totalSize = totalSize;
        }
        public int getTotalSize(){
            return this.totalSize;
        }
        public void setCurrentPageSize(int currentPageSize){
            this.currentPageSize = currentPageSize;
        }
        public int getCurrentPageSize(){
            return this.currentPageSize;
        }
        public void setCurrentPageNo(int currentPageNo){
            this.currentPageNo = currentPageNo;
        }
        public int getCurrentPageNo(){
            return this.currentPageNo;
        }
        public void setStartOfNextPage(int startOfNextPage){
            this.startOfNextPage = startOfNextPage;
        }
        public int getStartOfNextPage(){
            return this.startOfNextPage;
        }
        public void setEnd(int end){
            this.end = end;
        }
        public int getEnd(){
            return this.end;
        }
        public void setTotalPageCount(int totalPageCount){
            this.totalPageCount = totalPageCount;
        }
        public int getTotalPageCount(){
            return this.totalPageCount;
        }
        public void setResult(List<Result> result){
            this.result = result;
        }
        public List<Result> getResult(){
            return this.result;
        }
        public void setStartOfPreviousPage(int startOfPreviousPage){
            this.startOfPreviousPage = startOfPreviousPage;
        }
        public int getStartOfPreviousPage(){
            return this.startOfPreviousPage;
        }

    }
        private String msg;

        private Values values;

        private String result;

        public void setMsg(String msg){
            this.msg = msg;
        }
        public String getMsg(){
            return this.msg;
        }
        public void setValues(Values values){
            this.values = values;
        }
        public Values getValues(){
            return this.values;
        }
        public void setResult(String result){
            this.result = result;
        }
        public String getResult(){
            return this.result;
        }


}
