package com.wondersgroup.commerce.service;

import com.squareup.okhttp.ResponseBody;
import com.wondersgroup.commerce.teamwork.dailycheck.EtpsFirstBean;

import java.util.Map;

import retrofit.Call;
import retrofit.http.FieldMap;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Url;

/**
 * Created by bjy on 2017/9/27.
 */

public interface ConsumerwApi {
    public final static String PREFIX_1 = "ws/app_qj/";

    public final static String SEARCH_LIST = PREFIX_1 + "searchList";
    public final static String GET_ETPS_LIST = PREFIX_1 + "getEtpsList/";
    public final static String DO_CHECK = PREFIX_1 + "doCheck/%20/4/";
    public final static String GET_RECORD_INFO = PREFIX_1 + "getRecordInfo/";
    public final static String FETCH_IMG = PREFIX_1 + "fetchImg/";

    @Headers({"Content-Type: application/json;charset=UTF-8","Accept: application/json"})
    @POST(SEARCH_LIST)
    @FormUrlEncoded
    Call<EtpsFirstBean> searchList(@FieldMap Map<String, String> param);

    @Headers("Content-Type: text/html;charset=UTF-8")
    @GET(GET_ETPS_LIST + "{organId}/{keyWord}")
    Call<ResponseBody> getEtpsList(@Path("organId") String organId, @Path("keyWord") String keyWord);

    @Headers("Content-Type: text/html;charset=UTF-8")
    @GET(DO_CHECK + "{detpId}")
    Call<ResponseBody> doCheck(@Path("detpId") String detpId);

    @Headers("Content-Type: text/html;charset=UTF-8")
    @GET(DO_CHECK + "{detpId}/{recordId}")
    Call<ResponseBody> getRecordInfo(@Path("detpId") String detpId, @Path("recordId") String recordId);

    @Headers("Content-Type: text/html;charset=UTF-8")
    @GET(DO_CHECK + "{recordId}")
    Call<ResponseBody> fetchImg(@Path("recordId") String recordId);

    @GET(GET_RECORD_INFO)
    Call<ResponseBody> getRecordInfo();
}
