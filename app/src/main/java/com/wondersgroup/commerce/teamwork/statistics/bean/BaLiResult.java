package com.wondersgroup.commerce.teamwork.statistics.bean;

/**
 * Created by chan on 7/22/17.
 * 办理情况
 */

public class BaLiResult {
    private String code;

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

    public BaLiDetail getResult() {
        return result;
    }

    public void setResult(BaLiDetail result) {
        this.result = result;
    }

    private String message;
    private BaLiDetail result;

   public class BaLiDetail {
        private String REG_TOTAL_COUNT;//立案的总数
        private String NORMAL_REG_COUNT;//其中一般程序数量
        private String REG_PERCENT;//立案占各单位百分比
        private String END_TOTAL_COUNT;//结案总数
        private String END_5PEN_COUNT;//其中罚没5万以上案件数
        private String END_PERCENT;//结案数占各单位百分比
        private String PUNISH_AM_TOTAL;//罚没总额（万元）
        private String MAX_PUNISH_AM;//个案最高金额（万元）
        private String PUNISH_AM_PERCENT;//罚没数占各单位百分比

        public String getREG_TOTAL_COUNT() {
            return REG_TOTAL_COUNT;
        }

        public void setREG_TOTAL_COUNT(String REG_TOTAL_COUNT) {
            this.REG_TOTAL_COUNT = REG_TOTAL_COUNT;
        }

        public String getNORMAL_REG_COUNT() {
            return NORMAL_REG_COUNT;
        }

        public void setNORMAL_REG_COUNT(String NORMAL_REG_COUNT) {
            this.NORMAL_REG_COUNT = NORMAL_REG_COUNT;
        }

        public String getREG_PERCENT() {
            return REG_PERCENT;
        }

        public void setREG_PERCENT(String REG_PERCENT) {
            this.REG_PERCENT = REG_PERCENT;
        }

        public String getEND_TOTAL_COUNT() {
            return END_TOTAL_COUNT;
        }

        public void setEND_TOTAL_COUNT(String END_TOTAL_COUNT) {
            this.END_TOTAL_COUNT = END_TOTAL_COUNT;
        }

        public String getEND_5PEN_COUNT() {
            return END_5PEN_COUNT;
        }

        public void setEND_5PEN_COUNT(String END_5PEN_COUNT) {
            this.END_5PEN_COUNT = END_5PEN_COUNT;
        }

        public String getEND_PERCENT() {
            return END_PERCENT;
        }

        public void setEND_PERCENT(String END_PERCENT) {
            this.END_PERCENT = END_PERCENT;
        }

        public String getPUNISH_AM_TOTAL() {
            return PUNISH_AM_TOTAL;
        }

        public void setPUNISH_AM_TOTAL(String PUNISH_AM_TOTAL) {
            this.PUNISH_AM_TOTAL = PUNISH_AM_TOTAL;
        }

        public String getMAX_PUNISH_AM() {
            return MAX_PUNISH_AM;
        }

        public void setMAX_PUNISH_AM(String MAX_PUNISH_AM) {
            this.MAX_PUNISH_AM = MAX_PUNISH_AM;
        }

        public String getPUNISH_AM_PERCENT() {
            return PUNISH_AM_PERCENT;
        }

        public void setPUNISH_AM_PERCENT(String PUNISH_AM_PERCENT) {
            this.PUNISH_AM_PERCENT = PUNISH_AM_PERCENT;
        }
    }
}
