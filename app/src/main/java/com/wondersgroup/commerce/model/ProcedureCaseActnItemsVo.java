package com.wondersgroup.commerce.model;

/**
 * Created by admin on 2017/3/22.
 * 5.3.8. 违法行为代码
 */

public class ProcedureCaseActnItemsVo {

    private String ilglActId;       //	违法行为代码	String
    private String illegactType;        //	（总局）违法行为代码	String
    private String illegTypeCon;        //	违法行为种类(G)	String
    private String referenceLawCon;     //	定性依据(G)（只有一条）	String
    private String quabasis;            //	定性依据(G)代码（只有一条）	String
    private String punishLawCon;        //	处罚依据（G）（只有一条）	String
    private String penbasis;            //	处罚依据（G）代码（只有一条）	String

    public String getBasisType() {
        return basisType;
    }

    public void setBasisType(String basisType) {
        this.basisType = basisType;
    }

    private String basisType;           //  1--处罚依据，0--定性依据

    public String getIlglActId() {
        return ilglActId;
    }

    public void setIlglActId(String ilglActId) {
        this.ilglActId = ilglActId;
    }

    public String getIllegactType() {
        return illegactType;
    }

    public void setIllegactType(String illegactType) {
        this.illegactType = illegactType;
    }

    public String getIllegTypeCon() {
        return illegTypeCon;
    }

    public void setIllegTypeCon(String illegTypeCon) {
        this.illegTypeCon = illegTypeCon;
    }

    public String getReferenceLawCon() {
        return referenceLawCon;
    }

    public void setReferenceLawCon(String referenceLawCon) {
        this.referenceLawCon = referenceLawCon;
    }

    public String getQuabasis() {
        return quabasis;
    }

    public void setQuabasis(String quabasis) {
        this.quabasis = quabasis;
    }

    public String getPunishLawCon() {
        return punishLawCon;
    }

    public void setPunishLawCon(String punishLawCon) {
        this.punishLawCon = punishLawCon;
    }

    public String getPenbasis() {
        return penbasis;
    }

    public void setPenbasis(String penbasis) {
        this.penbasis = penbasis;
    }
}
