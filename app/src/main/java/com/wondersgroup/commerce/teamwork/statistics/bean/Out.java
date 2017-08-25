package com.wondersgroup.commerce.teamwork.statistics.bean;

/**
 * Created by chan on 8/23/17.
 */

public class Out {
    private int code;
    private String message;
    private OutResult result;

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

    public OutResult getResult() {
        return result;
    }

    public void setResult(OutResult result) {
        this.result = result;
    }

    public static class OutResult {
        private String QY_OUT_D_COUNT;//企业移出户数
        private String QY_OUT_COUNT;//企业移出条数
        private String TOTAL_QY_OUT_D_COUNT;//企业累计移出户数
        private String TOTAL_QY_OUT_COUNT;//企业累计移出条数
        private String NM_OUT_D_COUNT;//农专移出户数
        private String NM_OUT_COUNT;//农专移出条数
        private String TOTAL_NM_OUT_D_COUNT;//农专累计移出户数
        private String TOTAL_NM_OUT_COUNT;//农专累计移出条数
        private String GT_OUT_D_COUNT;//个体移出户数
        private String GT_OUT_COUNT;//个体移出条数
        private String TOTAL_GT_OUT_D_COUNT;//个体累计移出户数
        private String TOTAL_GT_OUT_COUNT;//个体累计移出条数

        public String getQY_OUT_D_COUNT() {
            return QY_OUT_D_COUNT;
        }

        public void setQY_OUT_D_COUNT(String QY_OUT_D_COUNT) {
            this.QY_OUT_D_COUNT = QY_OUT_D_COUNT;
        }

        public String getQY_OUT_COUNT() {
            return QY_OUT_COUNT;
        }

        public void setQY_OUT_COUNT(String QY_OUT_COUNT) {
            this.QY_OUT_COUNT = QY_OUT_COUNT;
        }

        public String getTOTAL_QY_OUT_D_COUNT() {
            return TOTAL_QY_OUT_D_COUNT;
        }

        public void setTOTAL_QY_OUT_D_COUNT(String TOTAL_QY_OUT_D_COUNT) {
            this.TOTAL_QY_OUT_D_COUNT = TOTAL_QY_OUT_D_COUNT;
        }

        public String getTOTAL_QY_OUT_COUNT() {
            return TOTAL_QY_OUT_COUNT;
        }

        public void setTOTAL_QY_OUT_COUNT(String TOTAL_QY_OUT_COUNT) {
            this.TOTAL_QY_OUT_COUNT = TOTAL_QY_OUT_COUNT;
        }

        public String getNM_OUT_D_COUNT() {
            return NM_OUT_D_COUNT;
        }

        public void setNM_OUT_D_COUNT(String NM_OUT_D_COUNT) {
            this.NM_OUT_D_COUNT = NM_OUT_D_COUNT;
        }

        public String getNM_OUT_COUNT() {
            return NM_OUT_COUNT;
        }

        public void setNM_OUT_COUNT(String NM_OUT_COUNT) {
            this.NM_OUT_COUNT = NM_OUT_COUNT;
        }

        public String getTOTAL_NM_OUT_D_COUNT() {
            return TOTAL_NM_OUT_D_COUNT;
        }

        public void setTOTAL_NM_OUT_D_COUNT(String TOTAL_NM_OUT_D_COUNT) {
            this.TOTAL_NM_OUT_D_COUNT = TOTAL_NM_OUT_D_COUNT;
        }

        public String getTOTAL_NM_OUT_COUNT() {
            return TOTAL_NM_OUT_COUNT;
        }

        public void setTOTAL_NM_OUT_COUNT(String TOTAL_NM_OUT_COUNT) {
            this.TOTAL_NM_OUT_COUNT = TOTAL_NM_OUT_COUNT;
        }

        public String getGT_OUT_D_COUNT() {
            return GT_OUT_D_COUNT;
        }

        public void setGT_OUT_D_COUNT(String GT_OUT_D_COUNT) {
            this.GT_OUT_D_COUNT = GT_OUT_D_COUNT;
        }

        public String getGT_OUT_COUNT() {
            return GT_OUT_COUNT;
        }

        public void setGT_OUT_COUNT(String GT_OUT_COUNT) {
            this.GT_OUT_COUNT = GT_OUT_COUNT;
        }

        public String getTOTAL_GT_OUT_D_COUNT() {
            return TOTAL_GT_OUT_D_COUNT;
        }

        public void setTOTAL_GT_OUT_D_COUNT(String TOTAL_GT_OUT_D_COUNT) {
            this.TOTAL_GT_OUT_D_COUNT = TOTAL_GT_OUT_D_COUNT;
        }

        public String getTOTAL_GT_OUT_COUNT() {
            return TOTAL_GT_OUT_COUNT;
        }

        public void setTOTAL_GT_OUT_COUNT(String TOTAL_GT_OUT_COUNT) {
            this.TOTAL_GT_OUT_COUNT = TOTAL_GT_OUT_COUNT;
        }
    }
}
