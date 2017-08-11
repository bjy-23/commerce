package com.wondersgroup.commerce.utils;


import com.google.gson.internal.LinkedTreeMap;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by yclli on 17/1/11.
 */

public class DataUtils {

    /**
     * 将json转换为有序Map
     * @param str
     * @return
     */
    public static Map<String, String> makeDicFromJson(Object str){
        Map<String, String> map = new LinkedTreeMap<>();
        if(str!=null && !"".equals(str)){
            map = (LinkedTreeMap<String, String>) str;
        }
        return map;
    }

    /**
     * 将json转换为字段名列表
     * @param str
     * @param needDefault
     * @return
     */
    public static List<String> makeDicList(Object str, boolean needDefault){
        List<String> list = new ArrayList<>();
        if(str!=null && !"".equals(str)){
            Map<String, String> map = (LinkedTreeMap<String, String>) str;
            if(needDefault) list.add("请选择");
            for(String key : map.keySet()){
                String temp = map.get(key);
                list.add(temp);
            }
        }
        return list;
    }

    /**
     * 由值获取map中的key
     * @param map
     * @param value
     * @return
     */
    public static String getKey(Map<String, String> map, String value){
        String str = "";
        if(value!=null && map!=null){
            for(String key: map.keySet()){
                if(value.equals(map.get(key)))
                    str = key;
            }
        }
        return str;
    }
}
