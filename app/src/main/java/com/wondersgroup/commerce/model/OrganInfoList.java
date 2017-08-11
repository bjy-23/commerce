package com.wondersgroup.commerce.model;

import java.util.ArrayList;

/**
 * Created by kangrenhui on 2015/12/10.
 */
public class OrganInfoList {
    private Dept.OrganInfo info;

    private ArrayList<OrganInfoList> organList;

    public Dept.OrganInfo getInfo() {
        return info;
    }

    public void setInfo(Dept.OrganInfo info) {
        this.info = info;
    }

    public ArrayList<OrganInfoList> getOrganList() {
        return organList;
    }

    public void setOrganList(ArrayList<OrganInfoList> organList) {
        this.organList = organList;
    }
}
