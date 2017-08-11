package com.wondersgroup.commerce.utils;

import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by kangrenhui on 2016/1/18.
 */
public class FileHelper {
    public static String getFromAssets(String fileName, Context context) {
        try {
            InputStreamReader inputReader = new InputStreamReader(context.getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            String Result = "";
            while ((line = bufReader.readLine()) != null)
                Result += line;
            return Result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "file error";
    }
}
