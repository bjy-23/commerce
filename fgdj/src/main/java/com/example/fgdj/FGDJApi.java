package com.example.fgdj;


import com.example.fgdj.bean.AreaBean;
import com.example.fgdj.bean.BaseInfoBean;
import com.example.fgdj.bean.DicBean;
import com.example.fgdj.bean.FgdjEntList;

import java.util.List;
import java.util.Map;

import retrofit.Call;
import retrofit.http.FieldMap;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.QueryMap;

/**
 * Created by bjy on 2017/4/28.
 */

public interface FGDJApi {
    String QUERY_FGDJ_ENT_LIST = "query_fgdj_ent_list.do";
    String GET_FGDJ_ENT_DETAIL = "get_fgdj_ent_detail.do";
    String GET_DICS = "get_dics.do";
    String SAVE_FGDJ_ENT = "save_fgdj_ent.do";//提交和暂存
    String QUERY_FGDJ_INFO_LIST = "query_fgdj_info_list.do";
    String CLAIM_ENT = "claim_ent.do";
    String CANCEL_CLAIM_ENT = "cancel_claim_ent.do";
    String QUERY_AREA = "query_area_code.do";
    String QUERY_PARTY_AREA = "query_party_area_code.do";
    String GET_FGDJ_ANNUAL_TEMP = "get_fgdj_annual_temp.do";//获取暂存数据

    @POST(QUERY_FGDJ_ENT_LIST)
    @FormUrlEncoded
    Call<Result<FgdjEntList>> queruFgdjEntList(@FieldMap Map data);

    @POST(GET_FGDJ_ENT_DETAIL)
    @FormUrlEncoded
    Call<Result<BaseInfoBean>> getFgdjEntDetail(@FieldMap Map data);

    @POST(GET_DICS)
    @FormUrlEncoded
    Call<Result<DicBean>> getDics(@FieldMap Map data);

    @POST(SAVE_FGDJ_ENT)
    @FormUrlEncoded
    Call<Result> saveFgdjEnt(@FieldMap Map data);

    @POST(CLAIM_ENT)
    @FormUrlEncoded
    Call<Result<String>> claimEnt(@FieldMap Map data);

    @POST(CANCEL_CLAIM_ENT)
    @FormUrlEncoded
    Call<Result<String>> cancelClaimEnt(@FieldMap Map data);

    @GET(QUERY_AREA)
    Call<Result<List<AreaBean>>> queryArea(@QueryMap Map data);

    @GET(QUERY_PARTY_AREA)
    Call<Result<List<AreaBean>>> queryPartyArea(@QueryMap Map data);

    @POST(GET_FGDJ_ANNUAL_TEMP)
    @FormUrlEncoded
    Call<Result<BaseInfoBean>> getTemp(@FieldMap Map data);

}
