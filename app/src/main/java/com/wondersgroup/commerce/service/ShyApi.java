package com.wondersgroup.commerce.service;

import com.wondersgroup.commerce.model.AttachBean;
import com.wondersgroup.commerce.model.AttachResultObject;
import com.wondersgroup.commerce.model.BackResultObject;
import com.wondersgroup.commerce.model.CaseInvestigateDetail;
import com.wondersgroup.commerce.model.CaseInvestigateListBean;
import com.wondersgroup.commerce.model.CaseQueryDic;
import com.wondersgroup.commerce.model.CaseQueryResult;
import com.wondersgroup.commerce.model.DynamicComponentObject;
import com.wondersgroup.commerce.model.NoteRecordBean;
import com.wondersgroup.commerce.teamwork.casedeal.bean.CaseQueryBean;
import com.wondersgroup.commerce.teamwork.casedeal.bean.LitigtBean;

import java.util.List;
import java.util.Map;

import retrofit.Call;
import retrofit.http.FieldMap;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;
import retrofit.http.Url;

/**
 * Created by bjy on 2017/9/29.
 */

public interface ShyApi {

    @FormUrlEncoded
    @POST
    public Call<CaseInvestigateListBean> getMyCaseList(@Url String url, @FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST
    public Call<CaseInvestigateDetail> getCaseRegDetail(@Url String url, @FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST
    public Call<NoteRecordBean> queryInvestigateList(@Url String url, @FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST
    public Call<DynamicComponentObject> toInspectEdit(@Url String url, @FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST
    public Call<BackResultObject> deleteInspect(@Url String url, @FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST
    public Call<BackResultObject> saveInspect(@Url String url, @FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST
    public Call<DynamicComponentObject> toEnquireEdit(@Url String url, @FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST
    public Call<BackResultObject> saveEnquire(@Url String url, @FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST
    public Call<BackResultObject> deleteEnquire(@Url String url, @FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST
    public Call<DynamicComponentObject> toQueryCase(@Url String url, @FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST
    public Call<CaseQueryResult> queryMyCase(@Url String url, @FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST
    public Call<DynamicComponentObject> getCaseGeneralDetail(@Url String url, @FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST
    public Call<AttachResultObject> downLoadAttchment(@Url String url, @FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST
    public Call<Result<List<LitigtBean>>> getLitigtDetail(@Url String url, @FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST
    public Call<Result<CaseQueryDic>> caseQueryList(@Url String url, @FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST
    public Call<Result<List<CaseQueryBean>>> toCaseQuery(@Url String url, @FieldMap Map<String, String> map);

    @POST
    @FormUrlEncoded
    public Call<Result<AttachBean>> downloadDoc(@Url String url, @FieldMap Map<String, String> param);
}
