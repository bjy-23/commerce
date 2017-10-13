package com.wondersgroup.commerce.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.wondersgroup.commerce.interface_.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lee on 2016/2/2.
 */
public class DataVolume implements Serializable{

    private String key;
    private String name;
    private List<DataVolume> remark;
    private String required;
    private String type;
    private String value;

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
