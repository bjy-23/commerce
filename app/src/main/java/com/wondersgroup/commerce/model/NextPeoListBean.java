package com.wondersgroup.commerce.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by yclli on 2015/12/10.
 */

public class NextPeoListBean {

    public static String authority = "";
    public static String userflag = "";
    public static String authId = "";
    public static String areacode = "";
    public static String organcode = "";
    public static List<Map<String,String>> chooseList = null;
    public static List<Map<String,String>> chooseList2 = null;
    public static NextPeoListBean nextPeoListBean = null;


    private String message;
    private Result result;
    private String code;

    /**
     *
     * @return
     * The message
     */
    public String getMessage() {
        return message;
    }

    /**
     *
     * @param message
     * The message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     *
     * @return
     * The code
     */
    public String getCode() {
        return code;
    }

    /**
     *
     * @param code
     * The code
     */
    public void setCode(String code) {
        this.code = code;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public class Result{
        private List<Result_> result = new ArrayList<Result_>();
        private String organCode;
        private List<AreaVoList> areaVoList = new ArrayList<AreaVoList>();
        private List<OrganVoList> organVoList = new ArrayList<OrganVoList>();
        private String areaCode;

        /**
         *
         * @return
         * The result
         */
        public List<Result_> getResult() {
            return result;
        }

        /**
         *
         * @param result
         * The result
         */
        public void setResult(List<Result_> result) {
            this.result = result;
        }

        /**
         *
         * @return
         * The organCode
         */
        public String getOrganCode() {
            return organCode;
        }

        /**
         *
         * @param organCode
         * The organCode
         */
        public void setOrganCode(String organCode) {
            this.organCode = organCode;
        }

        /**
         *
         * @return
         * The areaVoList
         */
        public List<AreaVoList> getAreaVoList() {
            return areaVoList;
        }

        /**
         *
         * @param areaVoList
         * The areaVoList
         */
        public void setAreaVoList(List<AreaVoList> areaVoList) {
            this.areaVoList = areaVoList;
        }

        /**
         *
         * @return
         * The organVoList
         */
        public List<OrganVoList> getOrganVoList() {
            return organVoList;
        }

        /**
         *
         * @param organVoList
         * The organVoList
         */
        public void setOrganVoList(List<OrganVoList> organVoList) {
            this.organVoList = organVoList;
        }

        /**
         *
         * @return
         * The areaCode
         */
        public String getAreaCode() {
            return areaCode;
        }

        /**
         *
         * @param areaCode
         * The areaCode
         */
        public void setAreaCode(String areaCode) {
            this.areaCode = areaCode;
        }
    }

    public class Result_ {

        private String id;
        private String name;
        private String pId;
        private String type;

        /**
         *
         * @return
         * The id
         */
        public String getId() {
            return id;
        }

        /**
         *
         * @param id
         * The id
         */
        public void setId(String id) {
            this.id = id;
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
         * The pId
         */
        public String getPId() {
            return pId;
        }

        /**
         *
         * @param pId
         * The pId
         */
        public void setPId(String pId) {
            this.pId = pId;
        }

        /**
         *
         * @return
         * The type
         */
        public String getType() {
            return type;
        }

        /**
         *
         * @param type
         * The type
         */
        public void setType(String type) {
            this.type = type;
        }

    }

    public class OrganVoList {

        private String organId;
        private String organName;

        /**
         *
         * @return
         * The organId
         */
        public String getOrganId() {
            return organId;
        }

        /**
         *
         * @param organId
         * The organId
         */
        public void setOrganId(String organId) {
            this.organId = organId;
        }

        /**
         *
         * @return
         * The organName
         */
        public String getOrganName() {
            return organName;
        }

        /**
         *
         * @param organName
         * The organName
         */
        public void setOrganName(String organName) {
            this.organName = organName;
        }

    }

    public class AreaVoList {

        private String areaCode;
        private String areaName;

        /**
         *
         * @return
         * The areaCode
         */
        public String getAreaCode() {
            return areaCode;
        }

        /**
         *
         * @param areaCode
         * The areaCode
         */
        public void setAreaCode(String areaCode) {
            this.areaCode = areaCode;
        }

        /**
         *
         * @return
         * The areaName
         */
        public String getAreaName() {
            return areaName;
        }

        /**
         *
         * @param areaName
         * The areaName
         */
        public void setAreaName(String areaName) {
            this.areaName = areaName;
        }

    }

}




