package com.wondersgroup.commerce.service;

import android.util.Log;

import com.google.gson.Gson;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.ResponseBody;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import retrofit.Converter;

/**
 * Created by kangrenhui on 2016/2/25.
 */
public class StringConerter<T> implements Converter<ResponseBody, String> {

    @Override
    public String convert(ResponseBody value) throws IOException {
        String response = value.string();

        return response;
    }
}
