package com.wondersgroup.commerce.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kangrenhui on 2015/12/8.
 */
public class DeptOne {

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


    public class DeptInfo {


        private String deptId;
        private String deptName;
        private Integer displayOrder;
        private String organId;
        private String parentDeptId;
        private String valid;

        /**
         * @return The deptId
         */
        public String getDeptId() {
            return deptId;
        }

        /**
         * @param deptId The deptId
         */
        public void setDeptId(String deptId) {
            this.deptId = deptId;
        }

        /**
         * @return The deptName
         */
        public String getDeptName() {
            return deptName;
        }

        /**
         * @param deptName The deptName
         */
        public void setDeptName(String deptName) {
            this.deptName = deptName;
        }

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
         * @return The parentDeptId
         */
        public String getParentDeptId() {
            return parentDeptId;
        }

        /**
         * @param parentDeptId The parentDeptId
         */
        public void setParentDeptId(String parentDeptId) {
            this.parentDeptId = parentDeptId;
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

    public class Example {


    }

    public class Result {

        private List<DeptInfo> deptInfo = new ArrayList<DeptInfo>();

        /**
         * @return The deptInfo
         */
        public List<DeptInfo> getDeptInfo() {
            return deptInfo;
        }

        /**
         * @param deptInfo The deptInfo
         */
        public void setDeptInfo(List<DeptInfo> deptInfo) {
            this.deptInfo = deptInfo;
        }

    }

}
