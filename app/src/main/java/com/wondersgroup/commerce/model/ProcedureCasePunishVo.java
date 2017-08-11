package com.wondersgroup.commerce.model;

import java.util.Map;

/**
 * Created by admin on 2017/3/21.
 */

public class ProcedureCasePunishVo {


    private String ilglActId;           //	违法行为代码
    private String illegTypeCon;        //	违法行为种类(G)
    private String referenceLawCon;     //	定性依据(G)显示
    private String quabasis;            //	定性依据(G)代码
    private String punishLawCon;        //	处罚依据(G)显示
    private String penbasis;            //	处罚依据(G)代码
    private String penam;               //	罚款金额
    private String punishType;          //	警告（罚款）
    private String caseFidate;          //	处罚日期
    private String penDecWriteNo;       //	当场处罚决定书文号

    public String getIlglActId() {
        return ilglActId;
    }

    public void setIlglActId(String ilglActId) {
        this.ilglActId = ilglActId;
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

    public String getPenam() {
        return penam;
    }

    public void setPenam(String penam) {
        this.penam = penam;
    }

    public String getPunishType() {
        return punishType;
    }

    public void setPunishType(String punishType) {
        this.punishType = punishType;
    }

    public String getCaseFidate() {
        return caseFidate;
    }

    public void setCaseFidate(String caseFidate) {
        this.caseFidate = caseFidate;
    }

    public String getPenDecWriteNo() {
        return penDecWriteNo;
    }

    public void setPenDecWriteNo(String penDecWriteNo) {
        this.penDecWriteNo = penDecWriteNo;
    }

}
