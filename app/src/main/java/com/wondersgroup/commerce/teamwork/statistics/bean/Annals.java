package com.wondersgroup.commerce.teamwork.statistics.bean;

/**
 * Created by root on 8/22/17.
 */

public class Annals {
    private int code;
    private String message;
    private AnnalsItem result;

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

    public AnnalsItem getResult() {
        return result;
    }

    public void setResult(AnnalsItem result) {
        this.result = result;
    }

    public class AnnalsItem {
        private String NZ_NOT_ANNL_COUNT;//内资未年报
        private String NZ_YES_ANNL_COUNT;//内资已年报
        private String NZ_NEED_ANNL_COUNT;//内资应年报
        private String NZ_ANNL_PERCENT;//内资年报率
        private String WZ_NOT_ANNL_COUNT;//外资未年报
        private String WZ_YES_ANNL_COUNT;//外资已年报
        private String WZ_NEED_ANNL_COUNT;//外资应年报
        private String WZ_ANNL_PERCENT;//外资年报率
        private String NOZ_NOT_ANNL_COUNT;//农资未年报
        private String NOZ_YES_ANNL_COUNT;//农资已年报
        private String NOZ_NEED_ANNL_COUNT;//农资应年报
        private String NOZ_ANNL_PERCENT;//农资年报率
        private String GT_NOT_ANNL_COUNT;//个体未年报
        private String GT_YES_ANNL_COUNT;//个体已年报
        private String GT_NEED_ANNL_COUNT;//个体应年报
        private String GT_ANNL_PERCENT;//个体年报率
        private String TOTAL_NOT_ANNL_COUNT;//总计未年报
        private String TOTAL_YES_ANNL_COUNT;//总计已年报

        public String getNZ_NOT_ANNL_COUNT() {
            return NZ_NOT_ANNL_COUNT;
        }

        public void setNZ_NOT_ANNL_COUNT(String NZ_NOT_ANNL_COUNT) {
            this.NZ_NOT_ANNL_COUNT = NZ_NOT_ANNL_COUNT;
        }

        public String getNZ_YES_ANNL_COUNT() {
            return NZ_YES_ANNL_COUNT;
        }

        public void setNZ_YES_ANNL_COUNT(String NZ_YES_ANNL_COUNT) {
            this.NZ_YES_ANNL_COUNT = NZ_YES_ANNL_COUNT;
        }

        public String getNZ_NEED_ANNL_COUNT() {
            return NZ_NEED_ANNL_COUNT;
        }

        public void setNZ_NEED_ANNL_COUNT(String NZ_NEED_ANNL_COUNT) {
            this.NZ_NEED_ANNL_COUNT = NZ_NEED_ANNL_COUNT;
        }

        public String getNZ_ANNL_PERCENT() {
            return NZ_ANNL_PERCENT;
        }

        public void setNZ_ANNL_PERCENT(String NZ_ANNL_PERCENT) {
            this.NZ_ANNL_PERCENT = NZ_ANNL_PERCENT;
        }

        public String getWZ_NOT_ANNL_COUNT() {
            return WZ_NOT_ANNL_COUNT;
        }

        public void setWZ_NOT_ANNL_COUNT(String WZ_NOT_ANNL_COUNT) {
            this.WZ_NOT_ANNL_COUNT = WZ_NOT_ANNL_COUNT;
        }

        public String getWZ_YES_ANNL_COUNT() {
            return WZ_YES_ANNL_COUNT;
        }

        public void setWZ_YES_ANNL_COUNT(String WZ_YES_ANNL_COUNT) {
            this.WZ_YES_ANNL_COUNT = WZ_YES_ANNL_COUNT;
        }

        public String getWZ_NEED_ANNL_COUNT() {
            return WZ_NEED_ANNL_COUNT;
        }

        public void setWZ_NEED_ANNL_COUNT(String WZ_NEED_ANNL_COUNT) {
            this.WZ_NEED_ANNL_COUNT = WZ_NEED_ANNL_COUNT;
        }

        public String getWZ_ANNL_PERCENT() {
            return WZ_ANNL_PERCENT;
        }

        public void setWZ_ANNL_PERCENT(String WZ_ANNL_PERCENT) {
            this.WZ_ANNL_PERCENT = WZ_ANNL_PERCENT;
        }

        public String getNOZ_NOT_ANNL_COUNT() {
            return NOZ_NOT_ANNL_COUNT;
        }

        public void setNOZ_NOT_ANNL_COUNT(String NOZ_NOT_ANNL_COUNT) {
            this.NOZ_NOT_ANNL_COUNT = NOZ_NOT_ANNL_COUNT;
        }

        public String getNOZ_YES_ANNL_COUNT() {
            return NOZ_YES_ANNL_COUNT;
        }

        public void setNOZ_YES_ANNL_COUNT(String NOZ_YES_ANNL_COUNT) {
            this.NOZ_YES_ANNL_COUNT = NOZ_YES_ANNL_COUNT;
        }

        public String getNOZ_NEED_ANNL_COUNT() {
            return NOZ_NEED_ANNL_COUNT;
        }

        public void setNOZ_NEED_ANNL_COUNT(String NOZ_NEED_ANNL_COUNT) {
            this.NOZ_NEED_ANNL_COUNT = NOZ_NEED_ANNL_COUNT;
        }

        public String getNOZ_ANNL_PERCENT() {
            return NOZ_ANNL_PERCENT;
        }

        public void setNOZ_ANNL_PERCENT(String NOZ_ANNL_PERCENT) {
            this.NOZ_ANNL_PERCENT = NOZ_ANNL_PERCENT;
        }

        public String getGT_NOT_ANNL_COUNT() {
            return GT_NOT_ANNL_COUNT;
        }

        public void setGT_NOT_ANNL_COUNT(String GT_NOT_ANNL_COUNT) {
            this.GT_NOT_ANNL_COUNT = GT_NOT_ANNL_COUNT;
        }

        public String getGT_YES_ANNL_COUNT() {
            return GT_YES_ANNL_COUNT;
        }

        public void setGT_YES_ANNL_COUNT(String GT_YES_ANNL_COUNT) {
            this.GT_YES_ANNL_COUNT = GT_YES_ANNL_COUNT;
        }

        public String getGT_NEED_ANNL_COUNT() {
            return GT_NEED_ANNL_COUNT;
        }

        public void setGT_NEED_ANNL_COUNT(String GT_NEED_ANNL_COUNT) {
            this.GT_NEED_ANNL_COUNT = GT_NEED_ANNL_COUNT;
        }

        public String getGT_ANNL_PERCENT() {
            return GT_ANNL_PERCENT;
        }

        public void setGT_ANNL_PERCENT(String GT_ANNL_PERCENT) {
            this.GT_ANNL_PERCENT = GT_ANNL_PERCENT;
        }

        public String getTOTAL_NOT_ANNL_COUNT() {
            return TOTAL_NOT_ANNL_COUNT;
        }

        public void setTOTAL_NOT_ANNL_COUNT(String TOTAL_NOT_ANNL_COUNT) {
            this.TOTAL_NOT_ANNL_COUNT = TOTAL_NOT_ANNL_COUNT;
        }

        public String getTOTAL_YES_ANNL_COUNT() {
            return TOTAL_YES_ANNL_COUNT;
        }

        public void setTOTAL_YES_ANNL_COUNT(String TOTAL_YES_ANNL_COUNT) {
            this.TOTAL_YES_ANNL_COUNT = TOTAL_YES_ANNL_COUNT;
        }
    }

    public static class AnnalsResult {
        private String name;
        private String wnb;//未年报
        private String ygnb;//应年报
        private String ynb;//已年报

        public String getNbl() {
            return nbl;
        }

        public void setNbl(String nbl) {
            this.nbl = nbl;
        }

        private String nbl;//年报率

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getWnb() {
            return wnb;
        }

        public void setWnb(String wnb) {
            this.wnb = wnb;
        }

        public String getYgnb() {
            return ygnb;
        }

        public void setYgnb(String ygnb) {
            this.ygnb = ygnb;
        }

        public String getYnb() {
            return ynb;
        }

        public void setYnb(String ynb) {
            this.ynb = ynb;
        }
    }
}
