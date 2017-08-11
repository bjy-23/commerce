package com.wondersgroup.commerce.model;

/**
 * Created by Lee on 2016/3/19.
 * 从服务器返回附件类
 */
public class ProcedureCaseAttachResultObject {
    private String message;     //状态描述
    private Result result;      //附件对象

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public class Result{
        private String attachFileStr;

        public String getAttachFileStr() {
            return attachFileStr;
        }

        public void setAttachFileStr(String attachFileStr) {
            this.attachFileStr = attachFileStr;
        }
    }
}
