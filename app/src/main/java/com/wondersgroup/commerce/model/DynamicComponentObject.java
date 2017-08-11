package com.wondersgroup.commerce.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lee on 2016/2/17.
 * 网络请求返回动态控件的集合对象
 * 用于页面：现场检查
 */
public class DynamicComponentObject {

    private String code;
    private String message;
    List<DataVolume> result = new ArrayList<>();//控件列表

    public List<DataVolume> getResult() {
        return result;
    }

    public void setResult(List<DataVolume> result) {
        this.result = result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
