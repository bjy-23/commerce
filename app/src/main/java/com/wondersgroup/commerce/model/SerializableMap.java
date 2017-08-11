package com.wondersgroup.commerce.model;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by Lee on 2016/3/1.
 * 用于activity之间传递的map参数
 */
public class SerializableMap implements Serializable {

    private Map<String,String> map;
    public Map<String,String> getMap()
    {
        return map;
    }
    public void setMap(Map<String,String> map)
    {
        this.map=map;
    }

}
