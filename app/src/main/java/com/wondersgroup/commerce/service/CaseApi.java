package com.wondersgroup.commerce.service;

import com.squareup.okhttp.ResponseBody;
import com.wondersgroup.commerce.model.AttachResultObject;
import com.wondersgroup.commerce.model.BackResultObject;
import com.wondersgroup.commerce.model.CaseInvestigateDetail;
import com.wondersgroup.commerce.model.CaseInvestigateListBean;
import com.wondersgroup.commerce.model.CaseQueryResult;
import com.wondersgroup.commerce.model.DynamicComponentObject;
import com.wondersgroup.commerce.model.NoteRecordBean;

import java.util.Map;

import retrofit.Call;
import retrofit.http.FieldMap;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;
import retrofit.http.Url;

/**
 * Created by bjy on 2017/6/21.
 */

public interface CaseApi {
    public static final String URL_CASE_1 = "case/services/mobile/";
    public static final String URL_CASE_2 = "casen_yn/services/mobile/";
    public static final String INVESTIGATE_CASE_LIST = "myCaseToInvestigate";       //案件调查列表
    public static final String INVESTIGATE_CASE_DETAIL = "getCaseRegDetail";        //立案信息
    public static final String NOTE_RECORD_LIST = "queryInvestigateList";           //现场笔录和询问笔录列表
    public static final String CASE_INSPECT_EDIT = "toInspectEdit";                 //现场检查详情
    public static final String CASE_INSPECT_SAVE = "saveInspect";                   //现场检查提交
    public static final String CASE_INSPECT_DELETE = "removeInspect";               //现场检查删除
    public static final String CASE_ENQUIRE_EDIT = "toEnquireEdit";                 //询问笔录详情
    public static final String CASE_ENQUIRE_SAVE = "saveEnquire";                   //询问笔录提交
    public static final String CASE_ENQUIRE_DELETE = "removeEnquire";               //询问笔录删除
    public static final String CASE_QUERY_CONDITION = "toQueryCase";                //案件查询页面
    public static final String CASE_QUERY_MY_CASE = "queryMyCase";                  //案件条件查询
    public static final String CASE_GENERAL_DETAIL = "getCaseGeneralDetail";        //案件总览
    public static final String DOWNLAODER_ATTACH = "downloadAttachFileByPost";      //附件下载
    public static final String GET_LITIGT_DETAIL = "getLitigtDetailByLitigtIdAndAssort";//当事人

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
    public Call<CaseQueryResult> queryMyCase(@Url String url,@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST
    public Call<DynamicComponentObject> getCaseGeneralDetail(@Url String url, @FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST
    public Call<AttachResultObject> downLoadAttchment(@Url String url, @FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST
    public Call<ResponseBody> getLitigtDetail(@Url String url, @FieldMap Map<String,String> map);
}
