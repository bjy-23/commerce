package com.wondersgroup.commerce.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lee on 2016/2/2.
 */
public class DataVolume {

    String key;
    String name;
    List<DataVolume> remark = new ArrayList<DataVolume>();
    String required;
    String type;
    String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DataVolume> getRemark() {
        return remark;
    }

    public void setRemark(List<DataVolume> remark) {
        this.remark = remark;
    }

    public String getRequired() {
        return required;
    }

    public void setRequired(String required) {
        this.required = required;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
