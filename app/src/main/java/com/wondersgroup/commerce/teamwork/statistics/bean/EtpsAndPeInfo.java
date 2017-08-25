package com.wondersgroup.commerce.teamwork.statistics.bean;

/**
 * Created by root on 8/22/17.
 */

public class EtpsAndPeInfo {
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

    public EtpsAndPeInfoResult getResult() {
        return result;
    }

    public void setResult(EtpsAndPeInfoResult result) {
        this.result = result;
    }

    private int code;
    private String message;
    private EtpsAndPeInfoResult result;

    public static class EtpsAndPeInfoItem {
        private String fst;
        private String snd;
        private String trd;

        public String getFst() {
            return fst;
        }

        public void setFst(String fst) {
            this.fst = fst;
        }

        public String getSnd() {
            return snd;
        }

        public void setSnd(String snd) {
            this.snd = snd;
        }

        public String getTrd() {
            return trd;
        }

        public void setTrd(String trd) {
            this.trd = trd;
        }
    }

    public class EtpsAndPeInfoResult {
        private String QY_LIVE_COUNT;//存续的企业数量
        private String NZ_LIVE_COUNT;//存续的农专数量
        private String PE_LIVE_COUNT;//存续的个体数量
        private String TOTAL_LIVE_COUNT;//存续的总数
        private String QY_REPEAL_COUNT;//吊销未注销的企业数量
        private String NZ_REPEAL_COUNT;//吊销未注销的农专数量
        private String PE_REPEAL_COUNT;//吊销未注销的个体数量
        private String TOTAL_REPEAL_COUNT;//吊销未注销的总数
        private String QY_MOVE_COUNT;//迁出的企业数量
        private String NZ_MOVE_COUNT;//迁出的农专数量
        private String PE_MOVE_COUNT;//迁出的个体数量
        private String TOTAL_MOVE_COUNT;//迁出的总数

        public String getQY_LIVE_COUNT() {
            return QY_LIVE_COUNT;
        }

        public void setQY_LIVE_COUNT(String QY_LIVE_COUNT) {
            this.QY_LIVE_COUNT = QY_LIVE_COUNT;
        }

        public String getNZ_LIVE_COUNT() {
            return NZ_LIVE_COUNT;
        }

        public void setNZ_LIVE_COUNT(String NZ_LIVE_COUNT) {
            this.NZ_LIVE_COUNT = NZ_LIVE_COUNT;
        }

        public String getPE_LIVE_COUNT() {
            return PE_LIVE_COUNT;
        }

        public void setPE_LIVE_COUNT(String PE_LIVE_COUNT) {
            this.PE_LIVE_COUNT = PE_LIVE_COUNT;
        }

        public String getTOTAL_LIVE_COUNT() {
            return TOTAL_LIVE_COUNT;
        }

        public void setTOTAL_LIVE_COUNT(String TOTAL_LIVE_COUNT) {
            this.TOTAL_LIVE_COUNT = TOTAL_LIVE_COUNT;
        }

        public String getQY_REPEAL_COUNT() {
            return QY_REPEAL_COUNT;
        }

        public void setQY_REPEAL_COUNT(String QY_REPEAL_COUNT) {
            this.QY_REPEAL_COUNT = QY_REPEAL_COUNT;
        }

        public String getNZ_REPEAL_COUNT() {
            return NZ_REPEAL_COUNT;
        }

        public void setNZ_REPEAL_COUNT(String NZ_REPEAL_COUNT) {
            this.NZ_REPEAL_COUNT = NZ_REPEAL_COUNT;
        }

        public String getPE_REPEAL_COUNT() {
            return PE_REPEAL_COUNT;
        }

        public void setPE_REPEAL_COUNT(String PE_REPEAL_COUNT) {
            this.PE_REPEAL_COUNT = PE_REPEAL_COUNT;
        }

        public String getTOTAL_REPEAL_COUNT() {
            return TOTAL_REPEAL_COUNT;
        }

        public void setTOTAL_REPEAL_COUNT(String TOTAL_REPEAL_COUNT) {
            this.TOTAL_REPEAL_COUNT = TOTAL_REPEAL_COUNT;
        }

        public String getQY_MOVE_COUNT() {
            return QY_MOVE_COUNT;
        }

        public void setQY_MOVE_COUNT(String QY_MOVE_COUNT) {
            this.QY_MOVE_COUNT = QY_MOVE_COUNT;
        }

        public String getNZ_MOVE_COUNT() {
            return NZ_MOVE_COUNT;
        }

        public void setNZ_MOVE_COUNT(String NZ_MOVE_COUNT) {
            this.NZ_MOVE_COUNT = NZ_MOVE_COUNT;
        }

        public String getPE_MOVE_COUNT() {
            return PE_MOVE_COUNT;
        }

        public void setPE_MOVE_COUNT(String PE_MOVE_COUNT) {
            this.PE_MOVE_COUNT = PE_MOVE_COUNT;
        }

        public String getTOTAL_MOVE_COUNT() {
            return TOTAL_MOVE_COUNT;
        }

        public void setTOTAL_MOVE_COUNT(String TOTAL_MOVE_COUNT) {
            this.TOTAL_MOVE_COUNT = TOTAL_MOVE_COUNT;
        }
    }
}
