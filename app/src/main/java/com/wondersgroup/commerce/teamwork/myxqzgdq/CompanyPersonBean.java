package com.wondersgroup.commerce.teamwork.myxqzgdq;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/4/12 0012.
 */

public class CompanyPersonBean {
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

        private ArrayList<Result> result ;

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
        public void setResult(ArrayList<Result> result){
            this.result = result;
        }
        public ArrayList<Result> getResult(){
            return this.result;
        }
        public void setStartOfPreviousPage(int startOfPreviousPage){
            this.startOfPreviousPage = startOfPreviousPage;
        }
        public int getStartOfPreviousPage(){
            return this.startOfPreviousPage;
        }
    }

    public class Result {
        private String persnName;

        private String address;

        private String regNo;

        private String checkDate;

        private String correctDate;

        private String organName;

        private String changeDate;

        private String entityName;

        private String organTradeName;

        private String entityId;

        public void setPersnName(String persnName){
            this.persnName = persnName;
        }
        public String getPersnName(){
            return this.persnName;
        }
        public void setAddress(String address){
            this.address = address;
        }
        public String getAddress(){
            return this.address;
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
        public void setCorrectDate(String correctDate){
            this.correctDate = correctDate;
        }
        public String getCorrectDate(){
            return this.correctDate;
        }
        public void setOrganName(String organName){
            this.organName = organName;
        }
        public String getOrganName(){
            return this.organName;
        }
        public void setChangeDate(String changeDate){
            this.changeDate = changeDate;
        }
        public String getChangeDate(){
            return this.changeDate;
        }
        public void setEntityName(String entityName){
            this.entityName = entityName;
        }
        public String getEntityName(){
            return this.entityName;
        }
        public void setOrganTradeName(String organTradeName){
            this.organTradeName = organTradeName;
        }
        public String getOrganTradeName(){
            return this.organTradeName;
        }
        public void setEntityId(String entityId){
            this.entityId = entityId;
        }
        public String getEntityId(){
            return this.entityId;
        }

    }

}
