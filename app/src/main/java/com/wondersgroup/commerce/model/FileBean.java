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
        private AttachFile attachFile;


        /**
         * @return The attachFile
         */
        public AttachFile getAttachFile() {
            return attachFile;
        }

        /**
         * @param attachFile The attachFile
         */
        public void setAttachFile(AttachFile attachFile) {
            this.attachFile = attachFile;
        }

        public class AttachFile {

            private String attachFileStr;
            private String attachId;
            private String attachName;

            /**
             * @return The attachFileStr
             */
            public String getAttachFileStr() {
                return attachFileStr;
            }

            /**
             * @param attachFileStr The attachFileStr
             */
            public void setAttachFileStr(String attachFileStr) {
                this.attachFileStr = attachFileStr;
            }

            /**
             * @return The attachId
             */
            public String getAttachId() {
                return attachId;
            }

            /**
             * @param attachId The attachId
             */
            public void setAttachId(String attachId) {
                this.attachId = attachId;
            }

            /**
             * @return The attachName
             */
            public String getAttachName() {
                return attachName;
            }

            /**
             * @param attachName The attachName
             */
            public void setAttachName(String attachName) {
                this.attachName = attachName;
            }
        }
    }
}

