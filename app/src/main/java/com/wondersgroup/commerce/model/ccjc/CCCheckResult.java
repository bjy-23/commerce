package com.wondersgroup.commerce.model.ccjc;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by 薛定猫 on 16-5-10.
 */
public class CCCheckResult {

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
    private Result result;
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
        //书面审查
        private CheckWayDoc checkWayDoc;
        //实地核查
        private CheckWayLocal checkWayLocal;
        //网络核查
        private CheckWayWeb checkWayWeb;

        public CheckWayDoc getCheckWayDoc() {
            return checkWayDoc;
        }

        public void setCheckWayDoc(CheckWayDoc checkWayDoc) {
            this.checkWayDoc = checkWayDoc;
        }

        public CheckWayLocal getCheckWayLocal() {
            return checkWayLocal;
        }

        public void setCheckWayLocal(CheckWayLocal checkWayLocal) {
            this.checkWayLocal = checkWayLocal;
        }

        public CheckWayWeb getCheckWayWeb() {
            return checkWayWeb;
        }

        public void setCheckWayWeb(CheckWayWeb checkWayWeb) {
            this.checkWayWeb = checkWayWeb;
        }
    }

    public class CheckWayDoc{
        private String checkBatchName;
        private String checkOrganName;
        private String checkPerson;
        private String checkDate;
        private String checkResult;
        private String orderNoticeContent;
        private String orderNoticeNo;
        private String orderNoticeDate;
        private String checkAppendixList;
        private String checkMemo;

        public String getCheckBatchName() {
            return checkBatchName;
        }

        public void setCheckBatchName(String checkBatchName) {
            this.checkBatchName = checkBatchName;
        }

        public String getCheckOrganName() {
            return checkOrganName;
        }

        public void setCheckOrganName(String checkOrganName) {
            this.checkOrganName = checkOrganName;
        }

        public String getCheckPerson() {
            return checkPerson;
        }

        public void setCheckPerson(String checkPerson) {
            this.checkPerson = checkPerson;
        }

        public String getCheckDate() {
            return checkDate;
        }

        public void setCheckDate(String checkDate) {
            this.checkDate = checkDate;
        }

        public String getCheckResult() {
            return checkResult;
        }

        public void setCheckResult(String checkResult) {
            this.checkResult = checkResult;
        }

        public String getOrderNoticeContent() {
            return orderNoticeContent;
        }

        public void setOrderNoticeContent(String orderNoticeContent) {
            this.orderNoticeContent = orderNoticeContent;
        }

        public String getOrderNoticeNo() {
            return orderNoticeNo;
        }

        public void setOrderNoticeNo(String orderNoticeNo) {
            this.orderNoticeNo = orderNoticeNo;
        }

        public String getOrderNoticeDate() {
            return orderNoticeDate;
        }

        public void setOrderNoticeDate(String orderNoticeDate) {
            this.orderNoticeDate = orderNoticeDate;
        }

        public String getCheckAppendixList() {
            return checkAppendixList;
        }

        public void setCheckAppendixList(String checkAppendixList) {
            this.checkAppendixList = checkAppendixList;
        }

        public String getCheckMemo() {
            return checkMemo;
        }

        public void setCheckMemo(String checkMemo) {
            this.checkMemo = checkMemo;
        }
    }

    public class CheckWayLocal{
        private String checkBatchName;
        private String checkOrganName;//核查实施机关
        private String checkPerson;//核查人(deprecated)
        private String personName;//核查人
        private String checkDate;//核查时间
        private String checkOtherPerson;//实地核查见证人
        private String checkResult;//核查情况
        private String orderNoticeContent;
        private String orderNoticeNo;
        private String orderNoticeDate;
        private String checkAppendixList;//材料清单
        private String checkMemo;//备注

        public String getPersonName() {
            return personName;
        }

        public void setPersonName(String personName) {
            this.personName = personName;
        }

        public String getCheckBatchName() {
            return checkBatchName;
        }

        public void setCheckBatchName(String checkBatchName) {
            this.checkBatchName = checkBatchName;
        }

        public String getCheckOrganName() {
            return checkOrganName;
        }

        public void setCheckOrganName(String checkOrganName) {
            this.checkOrganName = checkOrganName;
        }

        public String getCheckPerson() {
            return checkPerson;
        }

        public void setCheckPerson(String checkPerson) {
            this.checkPerson = checkPerson;
        }

        public String getCheckDate() {
            return checkDate;
        }

        public void setCheckDate(String checkDate) {
            this.checkDate = checkDate;
        }

        public String getCheckOtherPerson() {
            return checkOtherPerson;
        }

        public void setCheckOtherPerson(String checkOtherPerson) {
            this.checkOtherPerson = checkOtherPerson;
        }

        public String getCheckResult() {
            return checkResult;
        }

        public void setCheckResult(String checkResult) {
            this.checkResult = checkResult;
        }

        public String getOrderNoticeContent() {
            return orderNoticeContent;
        }

        public void setOrderNoticeContent(String orderNoticeContent) {
            this.orderNoticeContent = orderNoticeContent;
        }

        public String getOrderNoticeNo() {
            return orderNoticeNo;
        }

        public void setOrderNoticeNo(String orderNoticeNo) {
            this.orderNoticeNo = orderNoticeNo;
        }

        public String getOrderNoticeDate() {
            return orderNoticeDate;
        }

        public void setOrderNoticeDate(String orderNoticeDate) {
            this.orderNoticeDate = orderNoticeDate;
        }

        public String getCheckAppendixList() {
            return checkAppendixList;
        }

        public void setCheckAppendixList(String checkAppendixList) {
            this.checkAppendixList = checkAppendixList;
        }

        public String getCheckMemo() {
            return checkMemo;
        }

        public void setCheckMemo(String checkMemo) {
            this.checkMemo = checkMemo;
        }
    }

   public class CheckWayWeb{
        private String checkBatchName;
        private String checkOrganName;
        private String checkPerson;
        private String checkDate;
        private String checkResultMemo;
        private String checkResult;
        private String checkMemo;

        public String getCheckBatchName() {
            return checkBatchName;
        }

        public void setCheckBatchName(String checkBatchName) {
            this.checkBatchName = checkBatchName;
        }

        public String getCheckOrganName() {
            return checkOrganName;
        }

        public void setCheckOrganName(String checkOrganName) {
            this.checkOrganName = checkOrganName;
        }

        public String getCheckPerson() {
            return checkPerson;
        }

        public void setCheckPerson(String checkPerson) {
            this.checkPerson = checkPerson;
        }

        public String getCheckDate() {
            return checkDate;
        }

        public void setCheckDate(String checkDate) {
            this.checkDate = checkDate;
        }

        public String getCheckResultMemo() {
            return checkResultMemo;
        }

        public void setCheckResultMemo(String checkResultMemo) {
            this.checkResultMemo = checkResultMemo;
        }

        public String getCheckResult() {
            return checkResult;
        }

        public void setCheckResult(String checkResult) {
            this.checkResult = checkResult;
        }

        public String getCheckMemo() {
            return checkMemo;
        }

        public void setCheckMemo(String checkMemo) {
            this.checkMemo = checkMemo;
        }
    }
}
