package com.wondersgroup.commerce.teamwork.statistics.bean;

/**
 * Created by chan on 8/23/17.
 */

public class In {
    private int code;
    private String message;
    private InResult result;

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

    public InResult getResult() {
        return result;
    }

    public void setResult(InResult result) {
        this.result = result;
    }

    public static class InOutItem {
        private String name;
        private String type;//QY 企业,NM 农民,GT 个体
        private String dCount;//户数
        private String count;//条数
        private String totalDCount;//累计户数
        private String totalCount;//累计条数

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getdCount() {
            return dCount;
        }

        public void setdCount(String dCount) {
            this.dCount = dCount;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getTotalDCount() {
            return totalDCount;
        }

        public void setTotalDCount(String totalDCount) {
            this.totalDCount = totalDCount;
        }

        public String getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(String totalCount) {
            this.totalCount = totalCount;
        }
    }

    public static class InResult {
        private String QY_IN_D_COUNT;//企业移入户数
        private String QY_IN_COUNT;//企业移入条数
        private String TOTAL_QY_IN_D_COUNT;//企业累计移入户数
        private String TOTAL_QY_IN_COUNT;//企业累计移入条数
        private String NM_IN_D_COUNT;//农专移入户数
        private String NM_IN_COUNT;//农专移入条数
        private String TOTAL_NM_IN_D_COUNT;//农专累计移入户数
        private String TOTAL_NM_IN_COUNT;//农专累计移入条数
        private String GT_IN_D_COUNT;//个体移入户数
        private String GT_IN_COUNT;//个体移入条数
        private String TOTAL_GT_IN_D_COUNT;//个体累计移入户数
        private String TOTAL_GT_IN_COUNT;//个体累计移入条数

        public String getQY_IN_D_COUNT() {
            return QY_IN_D_COUNT;
        }

        public void setQY_IN_D_COUNT(String QY_IN_D_COUNT) {
            this.QY_IN_D_COUNT = QY_IN_D_COUNT;
        }

        public String getQY_IN_COUNT() {
            return QY_IN_COUNT;
        }

        public void setQY_IN_COUNT(String QY_IN_COUNT) {
            this.QY_IN_COUNT = QY_IN_COUNT;
        }

        public String getTOTAL_QY_IN_D_COUNT() {
            return TOTAL_QY_IN_D_COUNT;
        }

        public void setTOTAL_QY_IN_D_COUNT(String TOTAL_QY_IN_D_COUNT) {
            this.TOTAL_QY_IN_D_COUNT = TOTAL_QY_IN_D_COUNT;
        }

        public String getTOTAL_QY_IN_COUNT() {
            return TOTAL_QY_IN_COUNT;
        }

        public void setTOTAL_QY_IN_COUNT(String TOTAL_QY_IN_COUNT) {
            this.TOTAL_QY_IN_COUNT = TOTAL_QY_IN_COUNT;
        }

        public String getNM_IN_D_COUNT() {
            return NM_IN_D_COUNT;
        }

        public void setNM_IN_D_COUNT(String NM_IN_D_COUNT) {
            this.NM_IN_D_COUNT = NM_IN_D_COUNT;
        }

        public String getNM_IN_COUNT() {
            return NM_IN_COUNT;
        }

        public void setNM_IN_COUNT(String NM_IN_COUNT) {
            this.NM_IN_COUNT = NM_IN_COUNT;
        }

        public String getTOTAL_NM_IN_D_COUNT() {
            return TOTAL_NM_IN_D_COUNT;
        }

        public void setTOTAL_NM_IN_D_COUNT(String TOTAL_NM_IN_D_COUNT) {
            this.TOTAL_NM_IN_D_COUNT = TOTAL_NM_IN_D_COUNT;
        }

        public String getTOTAL_NM_IN_COUNT() {
            return TOTAL_NM_IN_COUNT;
        }

        public void setTOTAL_NM_IN_COUNT(String TOTAL_NM_IN_COUNT) {
            this.TOTAL_NM_IN_COUNT = TOTAL_NM_IN_COUNT;
        }

        public String getGT_IN_D_COUNT() {
            return GT_IN_D_COUNT;
        }

        public void setGT_IN_D_COUNT(String GT_IN_D_COUNT) {
            this.GT_IN_D_COUNT = GT_IN_D_COUNT;
        }

        public String getGT_IN_COUNT() {
            return GT_IN_COUNT;
        }

        public void setGT_IN_COUNT(String GT_IN_COUNT) {
            this.GT_IN_COUNT = GT_IN_COUNT;
        }

        public String getTOTAL_GT_IN_D_COUNT() {
            return TOTAL_GT_IN_D_COUNT;
        }

        public void setTOTAL_GT_IN_D_COUNT(String TOTAL_GT_IN_D_COUNT) {
            this.TOTAL_GT_IN_D_COUNT = TOTAL_GT_IN_D_COUNT;
        }

        public String getTOTAL_GT_IN_COUNT() {
            return TOTAL_GT_IN_COUNT;
        }

        public void setTOTAL_GT_IN_COUNT(String TOTAL_GT_IN_COUNT) {
            this.TOTAL_GT_IN_COUNT = TOTAL_GT_IN_COUNT;
        }


    }
}
