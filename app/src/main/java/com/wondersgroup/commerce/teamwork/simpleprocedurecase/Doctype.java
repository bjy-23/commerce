package com.wondersgroup.commerce.teamwork.simpleprocedurecase;

/**
 * Created by admin on 2017/3/14.
 */

public class Doctype {

    private String foreignKey;
    private String key;
    private String value;

    public String getForeignKey() {
        return foreignKey;
    }

    public void setForeignKey(String foreignKey) {
        this.foreignKey = foreignKey;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
