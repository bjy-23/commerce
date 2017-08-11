package com.wondersgroup.commerce.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kangrenhui on 2015/12/8.
 */
public class DeptFirst {


    private Result result;

    /**
     * @return The result
     */
    public Result getResult() {
        return result;
    }

    /**
     * @param result The result
     */
    public void setResult(Result result) {
        this.result = result;
    }


    public class OrganInfo {

        private Integer displayOrder;
        private String gbCode;
        private String organId;
        private String organName;
        private String parentOrganId;
        private String rank;
        private String valid;

        /**
         * @return The displayOrder
         */
        public Integer getDisplayOrder() {
            return displayOrder;
        }

        /**
         * @param displayOrder The displayOrder
         */
        public void setDisplayOrder(Integer displayOrder) {
            this.displayOrder = displayOrder;
        }

        /**
         * @return The gbCode
         */
        public String getGbCode() {
            return gbCode;
        }

        /**
         * @param gbCode The gbCode
         */
        public void setGbCode(String gbCode) {
            this.gbCode = gbCode;
        }

        /**
         * @return The organId
         */
        public String getOrganId() {
            return organId;
        }

        /**
         * @param organId The organId
         */
        public void setOrganId(String organId) {
            this.organId = organId;
        }

        /**
         * @return The organName
         */
        public String getOrganName() {
            return organName;
        }

        /**
         * @param organName The organName
         */
        public void setOrganName(String organName) {
            this.organName = organName;
        }

        /**
         * @return The parentOrganId
         */
        public String getParentOrganId() {
            return parentOrganId;
        }

        /**
         * @param parentOrganId The parentOrganId
         */
        public void setParentOrganId(String parentOrganId) {
            this.parentOrganId = parentOrganId;
        }

        /**
         * @return The rank
         */
        public String getRank() {
            return rank;
        }

        /**
         * @param rank The rank
         */
        public void setRank(String rank) {
            this.rank = rank;
        }

        /**
         * @return The valid
         */
        public String getValid() {
            return valid;
        }

        /**
         * @param valid The valid
         */
        public void setValid(String valid) {
            this.valid = valid;
        }

    }

    public class Result {

        private List<OrganInfo> organInfo = new ArrayList<OrganInfo>();

        /**
         * @return The organInfo
         */
        public List<OrganInfo> getOrganInfo() {
            return organInfo;
        }

        /**
         * @param organInfo The organInfo
         */
        public void setOrganInfo(List<OrganInfo> organInfo) {
            this.organInfo = organInfo;
        }

    }
}
