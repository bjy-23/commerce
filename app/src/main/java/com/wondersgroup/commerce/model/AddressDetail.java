package com.wondersgroup.commerce.model;

/**
 * Created by kangrenhui on 2015/12/7.
 */
public class AddressDetail {


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

    public class Result {

        private String address;
        private String cellphone;
        private String dept;
        private String duty;
        private String fax;
        private String housetel;
        private String name;
        private String officetel;
        private String postalcode;
        private String tel;
        private String unit;

        /**
         * @return The address
         */
        public String getAddress() {
            return address;
        }

        /**
         * @param address The address
         */
        public void setAddress(String address) {
            this.address = address;
        }

        /**
         * @return The cellphone
         */
        public String getCellphone() {
            return cellphone;
        }

        /**
         * @param cellphone The cellphone
         */
        public void setCellphone(String cellphone) {
            this.cellphone = cellphone;
        }

        /**
         * @return The dept
         */
        public String getDept() {
            return dept;
        }

        /**
         * @param dept The dept
         */
        public void setDept(String dept) {
            this.dept = dept;
        }

        /**
         * @return The duty
         */
        public String getDuty() {
            return duty;
        }

        /**
         * @param duty The duty
         */
        public void setDuty(String duty) {
            this.duty = duty;
        }

        /**
         * @return The fax
         */
        public String getFax() {
            return fax;
        }

        /**
         * @param fax The fax
         */
        public void setFax(String fax) {
            this.fax = fax;
        }

        /**
         * @return The housetel
         */
        public String getHousetel() {
            return housetel;
        }

        /**
         * @param housetel The housetel
         */
        public void setHousetel(String housetel) {
            this.housetel = housetel;
        }

        /**
         * @return The name
         */
        public String getName() {
            return name;
        }

        /**
         * @param name The name
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * @return The officetel
         */
        public String getOfficetel() {
            return officetel;
        }

        /**
         * @param officetel The officetel
         */
        public void setOfficetel(String officetel) {
            this.officetel = officetel;
        }

        /**
         * @return The postalcode
         */
        public String getPostalcode() {
            return postalcode;
        }

        /**
         * @param postalcode The postalcode
         */
        public void setPostalcode(String postalcode) {
            this.postalcode = postalcode;
        }

        /**
         * @return The tel
         */
        public String getTel() {
            return tel;
        }

        /**
         * @param tel The tel
         */
        public void setTel(String tel) {
            this.tel = tel;
        }

        /**
         * @return The unit
         */
        public String getUnit() {
            return unit;
        }

        /**
         * @param unit The unit
         */
        public void setUnit(String unit) {
            this.unit = unit;
        }

    }
}
