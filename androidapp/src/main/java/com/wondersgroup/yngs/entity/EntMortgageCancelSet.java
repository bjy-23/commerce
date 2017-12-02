package com.wondersgroup.yngs.entity;

/**
 * Created by 1229 on 2016/1/5.
 */
public class EntMortgageCancelSet {
    private String entMortgageId;
    private String uuid;
    private String canDate;
    private String morCanRea;

    public String getEntMortgageId() {
        return entMortgageId;
    }

    public void setEntMortgageId(String entMortgageId) {
        this.entMortgageId = entMortgageId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public EntMortgageCancelSet() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getCanDate() {
        return canDate;
    }

    public void setCanDate(String canDate) {
        this.canDate = canDate;
    }

    public String getMorCanRea() {
        return morCanRea;
    }

    public void setMorCanRea(String morCanRea) {
        this.morCanRea = morCanRea;
    }

}
