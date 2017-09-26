package com.wondersgroup.commerce.model;

/**
 * Created by kangrenhui on 2015/12/9.
 */
public class FileBean {
    private String message;
    private String code;
    private Result result;

    /**
     * @return The message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message The message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return The code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code The code
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

    public class Result {
        private AttachBean attachFile;

        public AttachBean getAttachFile() {
            return attachFile;
        }

        public void setAttachFile(AttachBean attachFile) {
            this.attachFile = attachFile;
        }
    }
}

