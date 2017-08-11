package com.wondersgroup.commerce.model;

import java.util.Map;

/**
 * Created by admin on 2017/3/21.
 */

public class ProcedureCaseDicVo {

    Map<String, String> partyMap;               //	当事人分类
    Map<String, String> isDominationPeMap;      //	当事人个体具体分类
    Map<String, String> isDominationEtpsMap;        //	当事人企业具体分类
    Map<String, String> dicCetfMap;             //	证件类型
    Map<String, String> dicSexMap;              //	性别
    Map<String, String> districtMap;            //	所在市级
    Map<String, String> userIdMainMap;          //	办案人员
    Map<String, String> clueTypeMap;            //	案件来源
    Map<String, String> ilglCateMap;            //	违法行为大类
    Map<String, String> iOrganIdMap;            //  机关字典
    Map<String, String> positionMap;

    public Map<String, String> getPartyMap() {
        return partyMap;
    }

    public void setPartyMap(Map<String, String> partyMap) {
        this.partyMap = partyMap;
    }

    public Map<String, String> getIsDominationPeMap() {
        return isDominationPeMap;
    }

    public void setIsDominationPeMap(Map<String, String> isDominationPeMap) {
        this.isDominationPeMap = isDominationPeMap;
    }

    public Map<String, String> getIsDominationEtpsMap() {
        return isDominationEtpsMap;
    }

    public void setIsDominationEtpsMap(Map<String, String> isDominationEtpsMap) {
        this.isDominationEtpsMap = isDominationEtpsMap;
    }

    public Map<String, String> getDicCetfMap() {
        return dicCetfMap;
    }

    public void setDicCetfMap(Map<String, String> dicCetfMap) {
        this.dicCetfMap = dicCetfMap;
    }

    public Map<String, String> getDicSexMap() {
        return dicSexMap;
    }

    public void setDicSexMap(Map<String, String> dicSexMap) {
        this.dicSexMap = dicSexMap;
    }

    public Map<String, String> getDistrictMap() {
        return districtMap;
    }

    public void setDistrictMap(Map<String, String> districtMap) {
        this.districtMap = districtMap;
    }

    public Map<String, String> getUserIdMainMap() {
        return userIdMainMap;
    }

    public void setUserIdMainMap(Map<String, String> userIdMainMap) {
        this.userIdMainMap = userIdMainMap;
    }

    public Map<String, String> getClueTypeMap() {
        return clueTypeMap;
    }

    public void setClueTypeMap(Map<String, String> clueTypeMap) {
        this.clueTypeMap = clueTypeMap;
    }

    public Map<String, String> getIlglCateMap() {
        return ilglCateMap;
    }

    public void setIlglCateMap(Map<String, String> ilglCateMap) {
        this.ilglCateMap = ilglCateMap;
    }

    public Map<String, String> getPositionMap() {
        return positionMap;
    }

    public void setPositionMap(Map<String, String> positionMap) {
        this.positionMap = positionMap;
    }

    public Map<String, String> getiOrganIdMap() {
        return iOrganIdMap;
    }

    public void setiOrganIdMap(Map<String, String> iOrganIdMap) {
        this.iOrganIdMap = iOrganIdMap;
    }
}
