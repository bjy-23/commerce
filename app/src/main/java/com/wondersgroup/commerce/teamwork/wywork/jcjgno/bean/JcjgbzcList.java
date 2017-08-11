package com.wondersgroup.commerce.teamwork.wywork.jcjgno.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/4/27.
 */
public class JcjgbzcList {

    public class Result {
        private String checkResult;

        private String regNo;

        private String checkDate;

        private String name;

        private String organMap;

        private String organName;

        private String manageDes;

        private String entityId;

        private String organTradeName;

        public void setCheckResult(String checkResult){
            this.checkResult = checkResult;
        }
        public String getCheckResult(){
            return this.checkResult;
        }
        public void setRegNo(String regNo){
            this.regNo = regNo;
        }
        public String getRegNo(){
            return this.regNo;
        }
        public void setCheckDate(String checkDate){
            this.checkDate = checkDate;
        }
        public String getCheckDate(){
            return this.checkDate;
        }
        public void setName(String name){
            this.name = name;
        }
        public String getName(){
            return this.name;
        }
        public void setOrganMap(String organMap){
            this.organMap = organMap;
        }
        public String getOrganMap(){
            return this.organMap;
        }
        public void setOrganName(String organName){
            this.organName = organName;
        }
        public String getOrganName(){
            return this.organName;
        }
        public void setManageDes(String manageDes){
            this.manageDes = manageDes;
        }
        public String getManageDes(){
            return this.manageDes;
        }
        public void setEntityId(String entityId){
            this.entityId = entityId;
        }
        public String getEntityId(){
            return this.entityId;
        }
        public void setOrganTradeName(String organTradeName){
            this.organTradeName = organTradeName;
        }
        public String getOrganTradeName(){
            return this.organTradeName;
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
