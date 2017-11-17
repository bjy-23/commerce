package com.wondersgroup.commerce.service;

import com.wondersgroup.commerce.model.yn.Version;

import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by bjy on 2017/11/7.
 */

public interface CommonApi {
    String API_VERSION = "commerceInfo";//版本更新

    @GET(API_VERSION)
    Call<Version> apiUpdate();
}
