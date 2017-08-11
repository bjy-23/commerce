package com.wondersgroup.commerce.service;

import com.squareup.okhttp.ResponseBody;
import com.wondersgroup.commerce.law_rule.bean.LawDetailsBean;
import com.wondersgroup.commerce.law_rule.bean.LawTypeBean;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import retrofit.Call;
import retrofit.http.FieldMap;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by bjy on 2017/5/16.
 */

public interface LawApi {
    final static String TO_LAW_TYPE_QUERY = "toLawTypeQuery";
    final static String TO_QUERY_LAW_BY_NAME = "toQueryLawByName";
    final static String TO_QUERY_LAW_CODE = "toQueryLawCode";
    final static String TO_QUERY_LAW_DETAILS = "toQueryLawDetail";

    @POST(TO_LAW_TYPE_QUERY)
    @FormUrlEncoded
    Call<Result<LinkedHashMap>> lawTypeQuery(@FieldMap Map map);

    @POST(TO_QUERY_LAW_BY_NAME)
    @FormUrlEncoded
    Call<Result<List<LawTypeBean>>> lawNameQuery(@FieldMap Map map);

    @POST(TO_QUERY_LAW_CODE)
    @FormUrlEncoded
    Call<Result<List<LawTypeBean>>> lawQuery(@FieldMap Map map);

    @POST(TO_QUERY_LAW_DETAILS)
    @FormUrlEncoded
    Call<Result<List<LawDetailsBean>>> detailsQuery(@FieldMap Map map);
}
