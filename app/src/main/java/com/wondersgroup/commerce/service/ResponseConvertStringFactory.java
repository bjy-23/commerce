package com.wondersgroup.commerce.service;

import com.squareup.okhttp.ResponseBody;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import retrofit.Converter;

/**
 * Created by kangrenhui on 2016/2/25.
 */
public class ResponseConvertStringFactory extends Converter.Factory {

    @Override
    public Converter<ResponseBody, ?> fromResponseBody(Type type, Annotation[] annotations) {
        return new StringConerter<>();
    }


}
