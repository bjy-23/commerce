package com.wondersgroup.commerce.teamwork.myrcxc;

import java.util.List;

/**
 * Created by Administrator on 2016/5/5 0005.
 */
public class RCXCPersonBean {


    private String msg;

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
        private int pageSize;
        private int start;
        private int nextPageNo;
        private int totalSize;
        private int currentPageSize;
        private int currentPageNo;
        private int startOfNextPage;
        private int end;
        private int totalPageCount;
        private int startOfPreviousPage;

        private List<ResultBean> result;

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public int getNextPageNo() {
            return nextPageNo;
        }

        public void setNextPageNo(int nextPageNo) {
            this.nextPageNo = nextPageNo;
        }

        public int getTotalSize() {
            return totalSize;
        }

        public void setTotalSize(int totalSize) {
            this.totalSize = totalSize;
        }

        public int getCurrentPageSize() {
            return currentPageSize;
        }

        public void setCurrentPageSize(int currentPageSize) {
            this.currentPageSize = currentPageSize;
        }

        public int getCurrentPageNo() {
            return currentPageNo;
        }

        public void setCurrentPageNo(int currentPageNo) {
            this.currentPageNo = currentPageNo;
        }

        public int getStartOfNextPage() {
            return startOfNextPage;
        }

        public void setStartOfNextPage(int startOfNextPage) {
            this.startOfNextPage = startOfNextPage;
        }

        public int getEnd() {
            return end;
        }

        public void setEnd(int end) {
            this.end = end;
        }

        public int getTotalPageCount() {
            return totalPageCount;
        }

        public void setTotalPageCount(int totalPageCount) {
            this.totalPageCount = totalPageCount;
        }

        public int getStartOfPreviousPage() {
            return startOfPreviousPage;
        }

        public void setStartOfPreviousPage(int startOfPreviousPage) {
            this.startOfPreviousPage = startOfPreviousPage;
        }

        public List<ResultBean> getResult() {
            return result;
        }

        public void setResult(List<ResultBean> result) {
            this.result = result;
        }

        public static class ResultBean {
            private String establishDate;
            private String neighborhoodId;
            private String existStatus;
            private String peTypeId;
            private String name;
            private String manaDate;
            private String oldManaLevel;
            private String checkDate;
            private int endNumb;
            private String manaRoleId;
            private String hotTrd;
            private String checkNoPlace;
            private int startNumb;
            private float cptlTotal;
            private String road;
            private String persnName;
            private String acceptOrganId;
            private String tradeEndDate;
            private String specialMark;
            private String tradeStartDate;
            private String peId;
            private String addressSort;
            private String address;
            private String cetfNo;
            private String regNo;
            private String hotArea;
            private String manaLevel;
            private String areaOrganId;

            public String getEstablishDate() {
                return establishDate;
            }

            public void setEstablishDate(String establishDate) {
                this.establishDate = establishDate;
            }

            public String getNeighborhoodId() {
                return neighborhoodId;
            }

            public void setNeighborhoodId(String neighborhoodId) {
                this.neighborhoodId = neighborhoodId;
            }

            public String getExistStatus() {
                return existStatus;
            }

            public void setExistStatus(String existStatus) {
                this.existStatus = existStatus;
            }

            public String getPeTypeId() {
                return peTypeId;
            }

            public void setPeTypeId(String peTypeId) {
                this.peTypeId = peTypeId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getManaDate() {
                return manaDate;
            }

            public void setManaDate(String manaDate) {
                this.manaDate = manaDate;
            }

            public String getOldManaLevel() {
                return oldManaLevel;
            }

            public void setOldManaLevel(String oldManaLevel) {
                this.oldManaLevel = oldManaLevel;
            }

            public String getCheckDate() {
                return checkDate;
            }

            public void setCheckDate(String checkDate) {
                this.checkDate = checkDate;
            }

            public int getEndNumb() {
                return endNumb;
            }

            public void setEndNumb(int endNumb) {
                this.endNumb = endNumb;
            }

            public String getManaRoleId() {
                return manaRoleId;
            }

            public void setManaRoleId(String manaRoleId) {
                this.manaRoleId = manaRoleId;
            }

            public String getHotTrd() {
                return hotTrd;
            }

            public void setHotTrd(String hotTrd) {
                this.hotTrd = hotTrd;
            }

            public String getCheckNoPlace() {
                return checkNoPlace;
            }

            public void setCheckNoPlace(String checkNoPlace) {
                this.checkNoPlace = checkNoPlace;
            }

            public int getStartNumb() {
                return startNumb;
            }

            public void setStartNumb(int startNumb) {
                this.startNumb = startNumb;
            }

            public float getCptlTotal() {
                return cptlTotal;
            }

            public void setCptlTotal(float cptlTotal) {
                this.cptlTotal = cptlTotal;
            }

            public String getRoad() {
                return road;
            }

            public void setRoad(String road) {
                this.road = road;
            }

            public String getPersnName() {
                return persnName;
            }

            public void setPersnName(String persnName) {
                this.persnName = persnName;
            }

            public String getAcceptOrganId() {
                return acceptOrganId;
            }

            public void setAcceptOrganId(String acceptOrganId) {
                this.acceptOrganId = acceptOrganId;
            }

            public String getTradeEndDate() {
                return tradeEndDate;
            }

            public void setTradeEndDate(String tradeEndDate) {
                this.tradeEndDate = tradeEndDate;
            }

            public String getSpecialMark() {
                return specialMark;
            }

            public void setSpecialMark(String specialMark) {
                this.specialMark = specialMark;
            }

            public String getTradeStartDate() {
                return tradeStartDate;
            }

            public void setTradeStartDate(String tradeStartDate) {
                this.tradeStartDate = tradeStartDate;
            }

            public String getPeId() {
                return peId;
            }

            public void setPeId(String peId) {
                this.peId = peId;
            }

            public String getAddressSort() {
                return addressSort;
            }

            public void setAddressSort(String addressSort) {
                this.addressSort = addressSort;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getCetfNo() {
                return cetfNo;
            }

            public void setCetfNo(String cetfNo) {
                this.cetfNo = cetfNo;
            }

            public String getRegNo() {
                return regNo;
            }

            public void setRegNo(String regNo) {
                this.regNo = regNo;
            }

            public String getHotArea() {
                return hotArea;
            }

            public void setHotArea(String hotArea) {
                this.hotArea = hotArea;
            }

            public String getManaLevel() {
                return manaLevel;
            }

            public void setManaLevel(String manaLevel) {
                this.manaLevel = manaLevel;
            }

            public String getAreaOrganId() {
                return areaOrganId;
            }

            public void setAreaOrganId(String areaOrganId) {
                this.areaOrganId = areaOrganId;
            }
        }
    }
}
