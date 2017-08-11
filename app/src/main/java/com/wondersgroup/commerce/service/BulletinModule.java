package com.wondersgroup.commerce.service;

import com.wondersgroup.commerce.model.AttachResultObject;
import com.wondersgroup.commerce.model.BackResultObject;
import com.wondersgroup.commerce.model.CaseInvestigateDetail;
import com.wondersgroup.commerce.model.CaseInvestigateListBean;
import com.wondersgroup.commerce.model.CaseQueryResult;
import com.wondersgroup.commerce.model.DynamicComponentObject;
import com.wondersgroup.commerce.model.GgDetails;
import com.wondersgroup.commerce.model.Ggcx;
import com.wondersgroup.commerce.model.NoteRecordBean;

import java.util.Map;

import retrofit.Call;
import retrofit.http.FieldMap;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by Lee on 2016/3/14
 */
public interface BulletinModule {
    String GET_BULLETIN_LIST = "getBulletinList";           //获取公告列表
    String GET_BULLETIN_DETAIL = "getBulletinDetail";        //公告详情获取


    @FormUrlEncoded
    @POST(GET_BULLETIN_LIST)
    public Call<Ggcx> getMyCaseList(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST(GET_BULLETIN_DETAIL)
    public Call<GgDetails> getCaseRegDetail(@FieldMap Map<String, String> body);



}
