package com.wondersgroup.commerce.model;

/**
 * Created by Lee on 2016/3/19.
 * 从服务器返回附件类
 */
public class AttachResultObject {
    private String code;        //状态码
    private String message;     //状态描述
    private AttachmentDTO result;      //附件对象

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AttachmentDTO getResult() {
        return result;
    }

    public void setResult(String AttachmentDTO) {
        this.result = result;
    }
}
