package com.wondersgroup.commerce.model;

import com.google.gson.annotations.SerializedName;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by bjy on 2017/9/19.
 */

public class CaseQueryDic {
    @SerializedName("orgListJsonArray")
    private List<TreeBean> orgList;//立案机关

    @SerializedName("queryTypeMap")
    private LinkedHashMap<String, String> queryTypeMap;//查询类型

    @SerializedName("deptList")
    private List<TreeBean> deptList;//办案机构

    @SerializedName("appProcedureMap")
    private LinkedHashMap<String, String> appProcedureMap;//适用程序

    @SerializedName("publicityMap")
    private LinkedHashMap<String, String> publicityMap;//公示情况

    @SerializedName("statusMap")
    private LinkedHashMap<String, String> statusMap;//案件阶段选择1

    @SerializedName("statusMap2")
    private LinkedHashMap<String, String> statusMap2;//案件阶段选择2--正处于

    @SerializedName("statusMap3")
    private LinkedHashMap<String, String> statusMap3;//案件阶段选择2--已经过

    public List<TreeBean> getOrgList() {
        return orgList;
    }

    public void setOrgList(List<TreeBean> orgList) {
        this.orgList = orgList;
    }

    public LinkedHashMap<String, String> getQueryTypeMap() {
        return queryTypeMap;
    }

    public void setQueryTypeMap(LinkedHashMap<String, String> queryTypeMap) {
        this.queryTypeMap = queryTypeMap;
    }

    public List<TreeBean> getDeptList() {
        return deptList;
    }

    public void setDeptList(List<TreeBean> deptList) {
        this.deptList = deptList;
    }

    public LinkedHashMap<String, String> getAppProcedureMap() {
        return appProcedureMap;
    }

    public void setAppProcedureMap(LinkedHashMap<String, String> appProcedureMap) {
        this.appProcedureMap = appProcedureMap;
    }

    public LinkedHashMap<String, String> getPublicityMap() {
        return publicityMap;
    }

    public void setPublicityMap(LinkedHashMap<String, String> publicityMap) {
        this.publicityMap = publicityMap;
    }

    public LinkedHashMap<String, String> getStatusMap() {
        return statusMap;
    }

    public void setStatusMap(LinkedHashMap<String, String> statusMap) {
        this.statusMap = statusMap;
    }

    public LinkedHashMap<String, String> getStatusMap2() {
        return statusMap2;
    }

    public void setStatusMap2(LinkedHashMap<String, String> statusMap2) {
        this.statusMap2 = statusMap2;
    }

    public LinkedHashMap<String, String> getStatusMap3() {
        return statusMap3;
    }

    public void setStatusMap3(LinkedHashMap<String, String> statusMap3) {
        this.statusMap3 = statusMap3;
    }
}
