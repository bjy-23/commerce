package com.wondersgroup.commerce.service;

import com.wondersgroup.commerce.model.yn.Version;
import com.wondersgroup.commerce.teamwork.statistics.bean.Annals;
import com.wondersgroup.commerce.teamwork.statistics.bean.BaLiResult;
import com.wondersgroup.commerce.teamwork.statistics.bean.BanJie;
import com.wondersgroup.commerce.teamwork.statistics.bean.EtpsAndPeInfo;
import com.wondersgroup.commerce.teamwork.statistics.bean.In;
import com.wondersgroup.commerce.teamwork.statistics.bean.Out;
import com.wondersgroup.commerce.teamwork.statistics.bean.Reason;

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
    String GET_CONSUMER_INFO = BASE + "getConsumerInfo";//办结情况
    String GET_CASE_INFO = BASE + "getCaseInfo ";//办理情况
    String API_VERSION = "commerceInfo";//版本更新
    String GET_ETPS_AND_PE_INFO = BASE + "getEtpsAndPeInfo";//市场主体户数统计
    String GET_ANNL_STAT_INFO = BASE + "getAnnlStatInfo";//年报情况统计
    String GET_EXCEPT_STAT_INFO = BASE + "getExceptStatInfo";//经营异常名录统计
    String GET_EXCEPT_REASON_STAT_INFO = BASE + "getExceptReasonStatInfo";//经营异常名录统计原因

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

    @POST(GET_ETPS_AND_PE_INFO)
    @FormUrlEncoded
    Call<EtpsAndPeInfo> getEtpsAndPeInfo(@FieldMap Map<String, String> params);

    @POST(GET_ANNL_STAT_INFO)
    @FormUrlEncoded
    Call<Annals> getAnnlStatInfo(@FieldMap Map<String, String> params);

    @POST(GET_EXCEPT_STAT_INFO)
    @FormUrlEncoded
    Call<In> getExceptStatInInfo(@FieldMap Map<String, String> params);

    @POST(GET_EXCEPT_STAT_INFO)
    @FormUrlEncoded
    Call<Out> getExceptStatOutInfo(@FieldMap Map<String, String> params);

    @POST(GET_EXCEPT_REASON_STAT_INFO)
    @FormUrlEncoded
    Call<Reason> getExceptReasonStatInfo(@FieldMap Map<String, String> params);
}
