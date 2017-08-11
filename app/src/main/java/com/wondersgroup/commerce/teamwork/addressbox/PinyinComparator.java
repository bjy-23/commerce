package com.wondersgroup.commerce.teamwork.addressbox;


import com.wondersgroup.commerce.model.Address;

import java.util.Comparator;

/**
 * Created by kangrenhui on 2015/12/8.
 */
public class PinyinComparator implements Comparator<Address.AddlistPersonalInfo> {
    public int compare(Address.AddlistPersonalInfo o1, Address.AddlistPersonalInfo o2) {
        //这里主要是用来对ListView里面的数据根据ABCDEFG...来排序
        if (o2.getSortLetters().equals("#")) {
            return -1;
        } else if (o1.getSortLetters().equals("#")) {
            return 1;
        } else {
            return o1.getSortLetters().compareTo(o2.getSortLetters());
        }
    }
}
