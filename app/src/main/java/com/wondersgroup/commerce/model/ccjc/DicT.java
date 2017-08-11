package com.wondersgroup.commerce.model.ccjc;

import java.util.List;

/**
 * Created by 薛定猫 on 2016/1/12.
 */
public class DicT {
    private List<DicItem> dicCheckStatusSearch;
    private List<DicItem> batchMap;
    private List<DicItem> dicEntType;
    private List<DicItem> dicCheckResult;
    private List<DicItem> dicCheckAppendixList;

    public List<DicItem> getDicCheckStatusSearch() {
        return dicCheckStatusSearch;
    }

    public void setDicCheckStatusSearch(List<DicItem> dicCheckStatusSearch) {
        this.dicCheckStatusSearch = dicCheckStatusSearch;
    }

    public List<DicItem> getBatchMap() {
        return batchMap;
    }

    public void setBatchMap(List<DicItem> batchMap) {
        this.batchMap = batchMap;
    }

    public List<DicItem> getDicEntType() {
        return dicEntType;
    }

    public void setDicEntType(List<DicItem> dicEntType) {
        this.dicEntType = dicEntType;
    }

    public List<DicItem> getDicCheckResult() {
        return dicCheckResult;
    }

    public void setDicCheckResult(List<DicItem> dicCheckResult) {
        this.dicCheckResult = dicCheckResult;
    }

    public List<DicItem> getDicCheckAppendixList() {
        return dicCheckAppendixList;
    }

    public void setDicCheckAppendixList(List<DicItem> dicCheckAppendixList) {
        this.dicCheckAppendixList = dicCheckAppendixList;
    }
}
