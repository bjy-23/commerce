package com.wondersgroup.commerce.model.ccjc;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by 薛定猫 on 16-5-10.
 */
public class CCHCResult {

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
    private String result;
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
    public String getResult() {
        return result;
    }

    /**
     *
     * @param result
     * The result
     */
    public void setResult(String result) {
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

}
