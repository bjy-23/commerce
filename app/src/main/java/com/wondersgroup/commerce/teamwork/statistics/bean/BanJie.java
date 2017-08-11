package com.wondersgroup.commerce.teamwork.statistics.bean;

/**
 * Created by root on 7/22/17.
 */

public class BanJie {
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

    public BanJieResult getResult() {
        return result;
    }

    public void setResult(BanJieResult result) {
        this.result = result;
    }

    private String code;
    private String message;
    private BanJieResult result;

    public  class BanJieResult {
        private String TS_REG;//登记的投诉数量
        private String JB_REG;//登记的举报数量
        private String TS_ASS;//上级分送的投诉数量
        private String JB_ASS;//上级分送的举报数量
        private String TOTAL;//总业务量
        private String TS_Y_DEAL;//办结的投诉数量
        private String JB_Y_DEAL;//办结的举报数量
        private String TS_N_DEAL;//未办结的投诉数量
        private String JB_N_DEAL;//未办结的举报数量
        private String TS_END_PERCENT;//投诉办结率
        private String JB_END_PERCENT;//举报办结率

        public String getTS_REG() {
            return TS_REG;
        }

        public void setTS_REG(String TS_REG) {
            this.TS_REG = TS_REG;
        }

        public String getJB_REG() {
            return JB_REG;
        }

        public void setJB_REG(String JB_REG) {
            this.JB_REG = JB_REG;
        }

        public String getTS_ASS() {
            return TS_ASS;
        }

        public void setTS_ASS(String TS_ASS) {
            this.TS_ASS = TS_ASS;
        }

        public String getJB_ASS() {
            return JB_ASS;
        }

        public void setJB_ASS(String JB_ASS) {
            this.JB_ASS = JB_ASS;
        }

        public String getTOTAL() {
            return TOTAL;
        }

        public void setTOTAL(String TOTAL) {
            this.TOTAL = TOTAL;
        }

        public String getTS_Y_DEAL() {
            return TS_Y_DEAL;
        }

        public void setTS_Y_DEAL(String TS_Y_DEAL) {
            this.TS_Y_DEAL = TS_Y_DEAL;
        }

        public String getJB_Y_DEAL() {
            return JB_Y_DEAL;
        }

        public void setJB_Y_DEAL(String JB_Y_DEAL) {
            this.JB_Y_DEAL = JB_Y_DEAL;
        }

        public String getTS_N_DEAL() {
            return TS_N_DEAL;
        }

        public void setTS_N_DEAL(String TS_N_DEAL) {
            this.TS_N_DEAL = TS_N_DEAL;
        }

        public String getJB_N_DEAL() {
            return JB_N_DEAL;
        }

        public void setJB_N_DEAL(String JB_N_DEAL) {
            this.JB_N_DEAL = JB_N_DEAL;
        }

        public String getTS_END_PERCENT() {
            return TS_END_PERCENT;
        }

        public void setTS_END_PERCENT(String TS_END_PERCENT) {
            this.TS_END_PERCENT = TS_END_PERCENT;
        }

        public String getJB_END_PERCENT() {
            return JB_END_PERCENT;
        }

        public void setJB_END_PERCENT(String JB_END_PERCENT) {
            this.JB_END_PERCENT = JB_END_PERCENT;
        }
    }
}
