package com.wondersgroup.commerce.service;


import com.wondersgroup.commerce.model.ad.AdQuery;
import com.wondersgroup.commerce.model.ad.AdView;

import java.util.Map;

import retrofit.Call;
import retrofit.http.FieldMap;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by chen on 2017/9/21.
 */
public interface AdApi {

    //广告
    String URL_AD = "services/adAppService/adAppService/";
    String AD_QUERY = URL_AD + "adQuery";//广告查询
    String AD_VIEW = URL_AD + "adView";//广告详情

    @FormUrlEncoded
    @POST(AD_QUERY)
    Call<AdQuery> adQuery(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST(AD_VIEW)
    Call<AdView> adView(@FieldMap Map<String, String> body);

}
