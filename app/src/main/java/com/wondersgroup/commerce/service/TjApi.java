package com.wondersgroup.commerce.service;

import com.wondersgroup.commerce.model.yn.Version;
import com.wondersgroup.commerce.teamwork.statistics.bean.BaLiResult;
import com.wondersgroup.commerce.teamwork.statistics.bean.BanJie;

import java.util.Map;

import retrofit.Call;
import retrofit.http.FieldMap;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by bjy on 2017/7/25.
 */

public interface TjApi {
    String BASE = "services/mdsWebService/app/";

    //统计
    String GET_CONSUMER_INFO = BASE +"getConsumerInfo";//办结情况
    String GET_CASE_INFO = BASE + "getCaseInfo ";//办理情况
    String API_VERSION = "commerceInfo";//版本更新

    //统计
    @POST(GET_CONSUMER_INFO)
    @FormUrlEncoded
    Call<BanJie> getConsumerInfo(@FieldMap Map<String, String> params);

    //统计
    @POST(GET_CASE_INFO)
    @FormUrlEncoded
    Call<BaLiResult> getBanLiInfo(@FieldMap Map<String, String> params);

    @GET(API_VERSION)
    Call<Version> apiUpdate();
}
