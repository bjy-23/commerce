package com.wondersgroup.commerce;

import org.apache.commons.codec.binary.Base64;
import android.util.Log;

import com.wondersgroup.commerce.model.TreeBean;
import com.wondersgroup.commerce.teamwork.email.EmailBean;

import java.lang.reflect.Field;
import java.net.URLEncoder;

/**
 * Created by bjy on 2017/8/10.
 */

public class Main {
    public static void main(String[] args){
//        TreeBean bean1 = new TreeBean();
//        bean1.setSelected(true);
//        bean1.setpId("1000");
//        bean1.setName("bjy");
//        bean1.setId("66");
//
//        try {
//            TreeBean bean2 = (TreeBean) bean1.clone();
//            System.out.println(bean1.getpId());
//            System.out.println(bean2.getpId());
//            System.out.println(bean1.getId());
//            System.out.println(bean2.getId());
//            System.out.println(bean1.getName());
//            System.out.println(bean2.getName());
//
//        } catch (CloneNotSupportedException e) {
//            e.printStackTrace();
//        }
        int i = 0;
        while(i --> 10){
            System.out.println(i + "");
        }
    }
}
