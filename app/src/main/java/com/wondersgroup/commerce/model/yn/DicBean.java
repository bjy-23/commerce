package com.wondersgroup.commerce.model.yn;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.wondersgroup.commerce.teamwork.mysupervision.AllDic;

/**
 * Created by bjy on 2017/3/7.
 */

public class DicBean {
    @SerializedName("code")
    @Expose
    private int code;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("result")
    @Expose
    private AllDic reslut;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AllDic getReslut() {
        return reslut;
    }

    public void setReslut(AllDic reslut) {
        this.reslut = reslut;
    }
}
