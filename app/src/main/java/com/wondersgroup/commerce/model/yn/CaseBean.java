package com.wondersgroup.commerce.model.yn;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 薛定猫 on 2016/3/16.
 */
public class CaseBean {

    @SerializedName("code")
    @Expose
    private Integer code;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("result")
    @Expose
    private Result result;

    /**
     *
     * @return
     * The code
     */
    public Integer getCode() {
        return code;
    }

    /**
     *
     * @param code
     * The code
     */
    public void setCode(Integer code) {
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

    public class Result {

        @SerializedName("cnAccuse")
        @Expose
        private CnAccuse cnAccuse;

        @SerializedName("cnBasic")
        @Expose
        private CnBasic cnBasic;

        @SerializedName("cnContent")
        @Expose
        private CnContent cnContent;

        @SerializedName("cnOpinions")
        @Expose
        private List<CnOpinion> cnOpinions = new ArrayList<CnOpinion>();

        @SerializedName("cnProcess")
        @Expose
        private CnProcess cnProcess;

        @SerializedName("cnUploads")
        @Expose
        private List<CnUpload> cnUploads;

        @SerializedName("flowEndFlag")
        @Expose
        private String flowEndFlag;

        /**
         *
         * @return
         * The cnAccuse
         */
        public CnAccuse getCnAccuse() {
            return cnAccuse;
        }

        /**
         *
         * @param cnAccuse
         * The cnAccuse
         */
        public void setCnAccuse(CnAccuse cnAccuse) {
            this.cnAccuse = cnAccuse;
        }

        /**
         *
         * @return
         * The cnBasic
         */
        public CnBasic getCnBasic() {
            return cnBasic;
        }

        /**
         *
         * @param cnBasic
         * The cnBasic
         */
        public void setCnBasic(CnBasic cnBasic) {
            this.cnBasic = cnBasic;
        }

        /**
         *
         * @return
         * The cnContent
         */
        public CnContent getCnContent() {
            return cnContent;
        }

        /**
         *
         * @param cnContent
         * The cnContent
         */
        public void setCnContent(CnContent cnContent) {
            this.cnContent = cnContent;
        }

        /**
         *
         * @return
         * The cnOpinions
         */
        public List<CnOpinion> getCnOpinions() {
            return cnOpinions;
        }

        /**
         *
         * @param cnOpinions
         * The cnOpinions
         */
        public void setCnOpinions(List<CnOpinion> cnOpinions) {
            this.cnOpinions = cnOpinions;
        }

        /**
         *
         * @return
         * The cnProcess
         */
        public CnProcess getCnProcess() {
            return cnProcess;
        }

        /**
         *
         * @param cnProcess
         * The cnProcess
         */
        public void setCnProcess(CnProcess cnProcess) {
            this.cnProcess = cnProcess;
        }

        public List<CnUpload> getCnUploads() {
            return cnUploads;
        }

        public void setCnUploads(List<CnUpload> cnUploads) {
            this.cnUploads = cnUploads;
        }

        /**
         *
         * @return
         * The flowEndFlag
         */
        public String getFlowEndFlag() {
            return flowEndFlag;
        }

        /**
         *
         * @param flowEndFlag
         * The flowEndFlag
         */
        public void setFlowEndFlag(String flowEndFlag) {
            this.flowEndFlag = flowEndFlag;
        }

    }

}