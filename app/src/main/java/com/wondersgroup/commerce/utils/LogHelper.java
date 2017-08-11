package com.wondersgroup.commerce.utils;

import com.orhanobut.logger.Logger;

/**
 * Created by kangrenhui on 2016/2/25.
 */
public class LogHelper {
    static boolean isOpen = true;

    public static void info(String info){
        if (isOpen){
            Logger.i(info);
        }
    }

    public static void debug(String debug){
        if (isOpen){
            Logger.d(debug);
        }
    }

    public static void json(String json){
        if (isOpen){
            Logger.json(json);
        }
    }



}
