package com.wondersgroup.commerce.model.ccjc;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 薛定猫 on 16-5-10.
 */
public class DicResult {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("pageCount")
    @Expose
    private Integer pageCount;
    @SerializedName("result")
    @Expose
    private DicT result;
    @SerializedName("totalRecord")
    @Expose
    private Integer totalRecord;

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
     * The pageCount
     */
    public Integer getPageCount() {
        return pageCount;
    }

    /**
     *
     * @param pageCount
     * The pageCount
     */
    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public DicT getResult() {
        return result;
    }

    public void setResult(DicT result) {
        this.result = result;
    }

    /**
     *
     * @return
     * The totalRecord
     */
    public Integer getTotalRecord() {
        return totalRecord;
    }

    /**
     *
     * @param totalRecord
     * The totalRecord
     */
    public void setTotalRecord(Integer totalRecord) {
        this.totalRecord = totalRecord;
    }

    public class Result {

        @SerializedName("dicEntType")
        @Expose
        private List<DicType> dicEntType = new ArrayList<>();
        @SerializedName("batchMap")
        @Expose
        private List<DicType> batchMap = new ArrayList<>();
        @SerializedName("dicCheckStatusSearch")
        @Expose
        private List<DicType> dicCheckStatusSearch = new ArrayList<>();
        @SerializedName("dicCheckResult")
        @Expose
        private List<DicType> dicCheckResult = new ArrayList<>();
        @SerializedName("dicCheckAppendixList")
        @Expose
        private List<DicType> dicCheckAppendixList = new ArrayList<>();

        /**
         *
         * @return
         * The dicEntType
         */
        public List<DicType> getDicEntType() {
            return dicEntType;
        }

        /**
         *
         * @param dicEntType
         * The dicEntType
         */
        public void setDicEntType(List<DicType> dicEntType) {
            this.dicEntType = dicEntType;
        }

        /**
         *
         * @return
         * The batchMap
         */
        public List<DicType> getBatchMap() {
            return batchMap;
        }

        /**
         *
         * @param batchMap
         * The batchMap
         */
        public void setBatchMap(List<DicType> batchMap) {
            this.batchMap = batchMap;
        }

        /**
         *
         * @return
         * The dicCheckStatusSearch
         */
        public List<DicType> getDicCheckStatusSearch() {
            return dicCheckStatusSearch;
        }

        /**
         *
         * @param dicCheckStatusSearch
         * The dicCheckStatusSearch
         */
        public void setDicCheckStatusSearch(List<DicType> dicCheckStatusSearch) {
            this.dicCheckStatusSearch = dicCheckStatusSearch;
        }

        /**
         *
         * @return
         * The dicCheckResult
         */
        public List<DicType> getDicCheckResult() {
            return dicCheckResult;
        }

        /**
         *
         * @param dicCheckResult
         * The dicCheckResult
         */
        public void setDicCheckResult(List<DicType> dicCheckResult) {
            this.dicCheckResult = dicCheckResult;
        }

        /**
         *
         * @return
         * The dicCheckAppendixList
         */
        public List<DicType> getDicCheckAppendixList() {
            return dicCheckAppendixList;
        }

        /**
         *
         * @param dicCheckAppendixList
         * The dicCheckAppendixList
         */
        public void setDicCheckAppendixList(List<DicType> dicCheckAppendixList) {
            this.dicCheckAppendixList = dicCheckAppendixList;
        }

    }
}
