package com.wondersgroup.commerce.teamwork.wywork.ydjyxz.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/5/9.
 */
public class YDJYAddBean {

    public class Result {
        private int startNum;

        private String establishDate;

        private String neighborhoodId;

        private String existStatus;

        private String subObjId;

        private String hotTrdGb;

        private String checkDate;

        private String manaRoleId;

        private String hotTrd;

        private String etpsName;

        private String checkNoPlace;

        private String manageLevel;

        private float cptlTotal;

        private String road;

        private String industryTrdGb;

        private String acceptOrganId;

        private String tradeEndDate;

        private String specialMark;

        private String industryTrd;

        private int endNum;

        private String cptlCrncyId;

        private String tradeStartDate;

        private String etpsId;

        private String addressSort;

        private String manageDate;

        private String leaderName;

        private String address;

        private String regNo;

        private String oldManageLevel;

        private String hotArea;

        private String areaOrganId;

        public void setStartNum(int startNum){
            this.startNum = startNum;
        }
        public int getStartNum(){
            return this.startNum;
        }
        public void setEstablishDate(String establishDate){
            this.establishDate = establishDate;
        }
        public String getEstablishDate(){
            return this.establishDate;
        }
        public void setNeighborhoodId(String neighborhoodId){
            this.neighborhoodId = neighborhoodId;
        }
        public String getNeighborhoodId(){
            return this.neighborhoodId;
        }
        public void setExistStatus(String existStatus){
            this.existStatus = existStatus;
        }
        public String getExistStatus(){
            return this.existStatus;
        }
        public void setSubObjId(String subObjId){
            this.subObjId = subObjId;
        }
        public String getSubObjId(){
            return this.subObjId;
        }
        public void setHotTrdGb(String hotTrdGb){
            this.hotTrdGb = hotTrdGb;
        }
        public String getHotTrdGb(){
            return this.hotTrdGb;
        }
        public void setCheckDate(String checkDate){
            this.checkDate = checkDate;
        }
        public String getCheckDate(){
            return this.checkDate;
        }
        public void setManaRoleId(String manaRoleId){
            this.manaRoleId = manaRoleId;
        }
        public String getManaRoleId(){
            return this.manaRoleId;
        }
        public void setHotTrd(String hotTrd){
            this.hotTrd = hotTrd;
        }
        public String getHotTrd(){
            return this.hotTrd;
        }
        public void setEtpsName(String etpsName){
            this.etpsName = etpsName;
        }
        public String getEtpsName(){
            return this.etpsName;
        }
        public void setCheckNoPlace(String checkNoPlace){
            this.checkNoPlace = checkNoPlace;
        }
        public String getCheckNoPlace(){
            return this.checkNoPlace;
        }
        public void setManageLevel(String manageLevel){
            this.manageLevel = manageLevel;
        }
        public String getManageLevel(){
            return this.manageLevel;
        }
        public void setCptlTotal(int cptlTotal){
            this.cptlTotal = cptlTotal;
        }
        public float getCptlTotal(){
            return this.cptlTotal;
        }
        public void setRoad(String road){
            this.road = road;
        }
        public String getRoad(){
            return this.road;
        }
        public void setIndustryTrdGb(String industryTrdGb){
            this.industryTrdGb = industryTrdGb;
        }
        public String getIndustryTrdGb(){
            return this.industryTrdGb;
        }
        public void setAcceptOrganId(String acceptOrganId){
            this.acceptOrganId = acceptOrganId;
        }
        public String getAcceptOrganId(){
            return this.acceptOrganId;
        }
        public void setTradeEndDate(String tradeEndDate){
            this.tradeEndDate = tradeEndDate;
        }
        public String getTradeEndDate(){
            return this.tradeEndDate;
        }
        public void setSpecialMark(String specialMark){
            this.specialMark = specialMark;
        }
        public String getSpecialMark(){
            return this.specialMark;
        }
        public void setIndustryTrd(String industryTrd){
            this.industryTrd = industryTrd;
        }
        public String getIndustryTrd(){
            return this.industryTrd;
        }
        public void setEndNum(int endNum){
            this.endNum = endNum;
        }
        public int getEndNum(){
            return this.endNum;
        }
        public void setCptlCrncyId(String cptlCrncyId){
            this.cptlCrncyId = cptlCrncyId;
        }
        public String getCptlCrncyId(){
            return this.cptlCrncyId;
        }
        public void setTradeStartDate(String tradeStartDate){
            this.tradeStartDate = tradeStartDate;
        }
        public String getTradeStartDate(){
            return this.tradeStartDate;
        }
        public void setEtpsId(String etpsId){
            this.etpsId = etpsId;
        }
        public String getEtpsId(){
            return this.etpsId;
        }
        public void setAddressSort(String addressSort){
            this.addressSort = addressSort;
        }
        public String getAddressSort(){
            return this.addressSort;
        }
        public void setManageDate(String manageDate){
            this.manageDate = manageDate;
        }
        public String getManageDate(){
            return this.manageDate;
        }
        public void setLeaderName(String leaderName){
            this.leaderName = leaderName;
        }
        public String getLeaderName(){
            return this.leaderName;
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
        public void setOldManageLevel(String oldManageLevel){
            this.oldManageLevel = oldManageLevel;
        }
        public String getOldManageLevel(){
            return this.oldManageLevel;
        }
        public void setHotArea(String hotArea){
            this.hotArea = hotArea;
        }
        public String getHotArea(){
            return this.hotArea;
        }
        public void setAreaOrganId(String areaOrganId){
            this.areaOrganId = areaOrganId;
        }
        public String getAreaOrganId(){
            return this.areaOrganId;
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
