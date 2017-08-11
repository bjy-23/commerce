package com.wondersgroup.commerce.model;

/**
 * Created by yclli on 2015/12/2.
 */
public class UpdateBean {

    private Result result;

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

        private String path;
        private String version;
        private String size;

        /**
         *
         * @return
         * The path
         */
        public String getPath() {
            return path;
        }

        /**
         *
         * @param path
         * The path
         */
        public void setPath(String path) {
            this.path = path;
        }

        /**
         *
         * @return
         * The version
         */
        public String getVersion() {
            return version;
        }

        /**
         *
         * @param version
         * The version
         */
        public void setVersion(String version) {
            this.version = version;
        }


        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }
    }

}