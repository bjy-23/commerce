package com.wondersgroup.yngs.entity;

import java.util.ArrayList;

/**
 * Created by 1229 on 2016/1/5.
 */
public class InverstorBean {
    private String inyTypeInterpreted;
    private String invName;
    private String bLicTypeInterpreted;
    private String bLicNo;
    private String cetfTypeInterpreted;
    private String cetfId;
    private String hasDetail;
    private String entInvtSet;
    private String uuid;
    private String rje;
    private String sje;

    private ArrayList<EntInvtSet> entinvSet = new ArrayList<EntInvtSet>();
    private ArrayList<EntInvtActlSet> entInvtActlSet = new ArrayList<EntInvtActlSet>();

    public String getInyTypeInterpreted() {
        return inyTypeInterpreted;
    }

    public void setInyTypeInterpreted(String inyTypeInterpreted) {
        this.inyTypeInterpreted = inyTypeInterpreted;
    }

    public String getInvName() {
        return invName;
    }

    public void setInvName(String invName) {
        this.invName = invName;
    }

    public String getbLicTypeInterpreted() {
        return bLicTypeInterpreted;
    }

    public void setbLicTypeInterpreted(String bLicTypeInterpreted) {
        this.bLicTypeInterpreted = bLicTypeInterpreted;
    }

    public String getbLicNo() {
        return bLicNo;
    }

    public void setbLicNo(String bLicNo) {
        this.bLicNo = bLicNo;
    }

    public String getCetfTypeInterpreted() {
        return cetfTypeInterpreted;
    }

    public void setCetfTypeInterpreted(String cetfTypeInterpreted) {
        this.cetfTypeInterpreted = cetfTypeInterpreted;
    }

    public String getCetfId() {
        return cetfId;
    }

    public void setCetfId(String cetfId) {
        this.cetfId = cetfId;
    }

    public String getHasDetail() {
        return hasDetail;
    }

    public void setHasDetail(String hasDetail) {
        this.hasDetail = hasDetail;
    }

    public String getEntInvtSet() {
        return entInvtSet;
    }

    public void setEntInvtSet(String entInvtSet) {
        this.entInvtSet = entInvtSet;
    }

    public InverstorBean() {
        super();
        // TODO Auto-generated constructor stub
    }

    public ArrayList<EntInvtSet> getEntinvSet() {
        return entinvSet;
    }

    public void setEntinvSet(ArrayList<EntInvtSet> entinvSet) {
        this.entinvSet = entinvSet;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public ArrayList<EntInvtActlSet> getEntInvtActlSet() {
        return entInvtActlSet;
    }

    public void setEntInvtActlSet(ArrayList<EntInvtActlSet> entInvtActlSet) {
        this.entInvtActlSet = entInvtActlSet;
    }

    public String getRje() {
        return rje;
    }

    public void setRje(String rje) {
        this.rje = rje;
    }

    public String getSje() {
        return sje;
    }

    public void setSje(String sje) {
        this.sje = sje;
    }

}

