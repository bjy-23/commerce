package com.wondersgroup.commerce.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kangrenhui on 2015/12/2.
 */
public class Address {
    private Result result;

    /**
     *
     * @return
     * The result
     */
    public Result getResult() {
        return result;
    }

    /**
     *
     * @param result
     * The result
     */
    public void setResult(Result result) {
        this.result = result;
    }


    public class AddlistPersonalInfo implements Serializable{

        private String addlistId;
        private String cellphone;
        private String dept;
        private String name;
        private String tel;
        private String unit;
        private String sortLetters;

        /**
         *
         * @return
         * The addlistId
         */
        public String getAddlistId() {
            return addlistId;
        }

        /**
         *
         * @param addlistId
         * The addlistId
         */
        public void setAddlistId(String addlistId) {
            this.addlistId = addlistId;
        }

        /**
         *
         * @return
         * The cellphone
         */
        public String getCellphone() {
            return cellphone;
        }

        public String getSortLetters() {
            return sortLetters;
        }

        public void setSortLetters(String sortLetters) {
            this.sortLetters = sortLetters;
        }

        /**
         *
         * @param cellphone
         * The cellphone
         */
        public void setCellphone(String cellphone) {
            this.cellphone = cellphone;
        }

        /**
         *
         * @return
         * The dept
         */
        public String getDept() {
            return dept;
        }

        /**
         *
         * @param dept
         * The dept
         */
        public void setDept(String dept) {
            this.dept = dept;
        }

        /**
         *
         * @return
         * The name
         */
        public String getName() {
            return name;
        }

        /**
         *
         * @param name
         * The name
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         *
         * @return
         * The tel
         */
        public String getTel() {
            return tel;
        }

        /**
         *
         * @param tel
         * The tel
         */
        public void setTel(String tel) {
            this.tel = tel;
        }

        /**
         *
         * @return
         * The unit
         */
        public String getUnit() {
            return unit;
        }

        /**
         *
         * @param unit
         * The unit
         */
        public void setUnit(String unit) {
            this.unit = unit;
        }

    }

    public class Result {

        private List<AddlistPersonalInfo> addlistPersonalInfo = new ArrayList<AddlistPersonalInfo>();

        /**
         *
         * @return
         * The addlistPersonalInfo
         */
        public List<AddlistPersonalInfo> getAddlistPersonalInfo() {
            return addlistPersonalInfo;
        }

        /**
         *
         * @param addlistPersonalInfo
         * The addlistPersonalInfo
         */
        public void setAddlistPersonalInfo(List<AddlistPersonalInfo> addlistPersonalInfo) {
            this.addlistPersonalInfo = addlistPersonalInfo;
        }

    }

}


