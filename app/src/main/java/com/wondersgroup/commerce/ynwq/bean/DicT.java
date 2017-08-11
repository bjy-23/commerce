package com.wondersgroup.commerce.ynwq.bean;

import com.google.gson.annotations.SerializedName;
import com.wondersgroup.commerce.model.ccjc.DicItem;
import com.wondersgroup.commerce.model.ccjc.TreeItem;

import java.util.List;

/**
 * Created by 薛定猫 on 2016/1/12.
 */
public class DicT {
    private List<DicItem> dicAppType;
    @SerializedName("flowStatusAndToDoNameMap")
    private List<DicItem> flowStatus;
    private List<DicItem> dicDeptTypeAll;
    private List<DicItem> runSpaceType;
    private List<DicItem> industryMap;
    private List<TreeItem> jsonReceiveOrganTree;
    private String version;

    public List<DicItem> getDicAppType() {
        return dicAppType;
    }

    public void setDicAppType(List<DicItem> dicAppType) {
        this.dicAppType = dicAppType;
    }

    public List<DicItem> getFlowStatus() {
        return flowStatus;
    }

    public void setFlowStatus(List<DicItem> flowStatus) {
        this.flowStatus = flowStatus;
    }

    public List<DicItem> getDicDeptTypeAll() {
        return dicDeptTypeAll;
    }

    public void setDicDeptTypeAll(List<DicItem> dicDeptTypeAll) {
        this.dicDeptTypeAll = dicDeptTypeAll;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<DicItem> getRunSpaceType() {
        return runSpaceType;
    }

    public void setRunSpaceType(List<DicItem> runSpaceType) {
        this.runSpaceType = runSpaceType;
    }

    public List<DicItem> getIndustryMap() {
        return industryMap;
    }

    public void setIndustryMap(List<DicItem> industryMap) {
        this.industryMap = industryMap;
    }

    public List<TreeItem> getJsonReceiveOrganTree() {
        return jsonReceiveOrganTree;
    }

    public void setJsonReceiveOrganTree(List<TreeItem> jsonReceiveOrganTree) {
        this.jsonReceiveOrganTree = jsonReceiveOrganTree;
    }
}
