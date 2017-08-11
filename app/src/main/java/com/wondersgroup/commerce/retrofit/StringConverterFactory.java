package com.wondersgroup.commerce.retrofit;

import com.squareup.okhttp.ResponseBody;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import retrofit.Converter;

/**
 * Created by bjy on 2017/5/22.
 */

public class StringConverterFactory extends Converter.Factory {
    public final static StringConverterFactory instance = new StringConverterFactory();

    public static StringConverterFactory create(){

        return instance;
    }

    @Override
    public Converter<ResponseBody, ?> fromResponseBody(Type type, Annotation[] annotations) {
        if (type == String.class){
            return StringConverter.instance;
        }

        return null;
    }
}
