package com.wondersgroup.commerce.teamwork.dailycheck;

import com.wondersgroup.commerce.application.RootAppcation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MapToListUtil {
    private Map<String, String> map;
    private RootAppcation application = RootAppcation.getInstance();

    public MapToListUtil(Map<String, String> map) {
        super();
        this.map = map;
    }

    public List<String> mapToKeyList() {
        List<String> list = new ArrayList<String>();
        Iterator<String> it = map.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next().toString();
            list.add(key);
        }

        return list;

    }

    public List<EtpsInfoBean> mapToEtpsInfoS() {
        List<EtpsInfoBean> list = new ArrayList<EtpsInfoBean>();
        Iterator<String> it = map.keySet().iterator();
        LinkedHashMap<String, String> etpsDic = new LinkedHashMap<String, String>();
        if (application.getBigBean().getEtpsDic().size() == 0) {
            etpsDic = application.getInfoBean().getEtpsDic();
        } else {
            etpsDic = application.getBigBean().getEtpsDic();
        }
        while (it.hasNext()) {
            EtpsInfoBean etpsInfoBean = new EtpsInfoBean();
            String key = it.next().toString();
            String value = map.get(key);
            etpsInfoBean.setKey(key);
            etpsInfoBean.setValue(value);

            String string = etpsDic.get(key);
            etpsInfoBean.setName(string);
            list.add(etpsInfoBean);
        }

        return list;

    }

    public List<EtpsInfoChangeBean> mapToEtpsChangeInfo() {
        List<EtpsInfoChangeBean> list = new ArrayList<EtpsInfoChangeBean>();
        Iterator<String> it = map.keySet().iterator();
        while (it.hasNext()) {
            EtpsInfoChangeBean etpsInfoChangeBean = new EtpsInfoChangeBean();
            String key = it.next().toString();
            String value = map.get(key);
            etpsInfoChangeBean.setKey(key);
            etpsInfoChangeBean.setValue(value);
            etpsInfoChangeBean.setName(application.getInfoBean().getEtpsDic()
                    .get(key));
            etpsInfoChangeBean.setChange(application.getInfoBean()
                    .getAppEntInfoVo().get(key));
            list.add(etpsInfoChangeBean);
        }

        return list;

    }

    public List<KeyValue> mapToKeyValues() {
        List<KeyValue> list = new ArrayList<KeyValue>();
        if (map != null){
            Iterator<String> it = map.keySet().iterator();
            while (it.hasNext()) {
                KeyValue keyValue = new KeyValue();
                String key = it.next().toString();
                String value = map.get(key);
                keyValue.setKey(key);
                keyValue.setValue(value);
                list.add(keyValue);
            }
        }
        return list;

    }

    public List<String> mapToValueList() {
        List<String> list = new ArrayList<String>();
        Iterator<String> it = map.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next().toString();
            list.add(map.get(key));
        }

        return list;

    }
}
