package com.wondersgroup.commerce;

import org.apache.commons.codec.binary.Base64;
import android.util.Log;

/**
 * Created by bjy on 2017/8/10.
 */

public class Main {
    public static void main(String[] args){
        String s1 = "NTEwMDAwMTcwNzI2MDAwMDE";
        byte[] bytes = Base64.decodeBase64(s1);
        String s2 = new String(bytes);
        System.out.print("base64" + s2);
    }
}
