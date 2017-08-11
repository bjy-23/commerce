package com.wondersgroup.commerce.retrofit;

import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

import retrofit.Converter;

/**
 * Created by bjy on 2017/5/22.
 */

public class StringConverter implements Converter<ResponseBody,String> {
    public static StringConverter instance = new StringConverter();

    @Override
    public String convert(ResponseBody value) throws IOException {
        return value.string();
    }
}
