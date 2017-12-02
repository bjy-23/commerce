package com.wondersgroup.yngs.service;

import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.ResponseBody;
import com.wondersgroup.yngs.entity.BurNingResult;
import com.wondersgroup.yngs.entity.ChildOrganResult;
import com.wondersgroup.yngs.entity.DicResult;
import com.wondersgroup.yngs.entity.FuchiResult;
import com.wondersgroup.yngs.entity.GeneralResult;
import com.wondersgroup.yngs.entity.JinduResult;
import com.wondersgroup.yngs.entity.JingBanResult;
import com.wondersgroup.yngs.entity.LoginResult;
import com.wondersgroup.yngs.entity.MeInfoResult;
import com.wondersgroup.yngs.entity.MsgResult;
import com.wondersgroup.yngs.entity.StatResult;
import com.wondersgroup.yngs.entity.TJProcessResult;
import com.wondersgroup.yngs.entity.TJResult;
import com.wondersgroup.yngs.entity.ToDoResult;
import com.wondersgroup.yngs.entity.UpgradeResult;
import com.wondersgroup.yngs.entity.UserResult;
import com.wondersgroup.yngs.entity.XiaoWeiResult;
import com.wondersgroup.yngs.entity.XwComResult;

import java.util.Map;

import retrofit.Call;
import retrofit.http.FieldMap;
import retrofit.http.FormUrlEncoded;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.PartMap;

/**
 * Created by 薛定猫 on 2015/12/9.
 */
public interface YunNanApi {
    String API_LOGIN="loginInfo";
    String API_USER_INFO="getUserInfo";
    String API_GET_TODO="getToDo";
    String API_GET_TODO_LIST="getToDoList";
    String API_GET_OPFLOW="getToDoInfo";
    String API_GET_ENTINFO="getEtpsInfo";
    String API_GET_PROGRESS="getEtpsInfoList";
    String API_GET_SUPPORT_INFO_LIST ="getMeEntityInfoList";
    String API_GET_SUPPORT_INFO="getMeSupportInfo";
    String API_GET_SUPPORT_LIST="getMeSupportList";
    String API_GET_SUPPORT="getMeSupport";
    String API_UPGRADE="upgrade";
    String API_GET_TODO_COUNT="getToDoCount";
    String API_GET_DIC="getDicLoad";
    String API_GET_MEINFO="getMeInfo";
    String API_SUMITMEEXPLORATION="submitMeExploration";
    String API_GETCHILDORGAN="getOrganByParent";
    String API_DEPTSTAT="meDeptStat";//统计，部门
    String API_AREASTAT="meAreaStat";//统计，区域
    String API_INDUSTAT="meIndustryStat";
    String API_PROCSTAT="meProcessStat";
    String API_STATLIST="getStatList";
    public String API_UPLOAD_IMG="mobileUploadFile.do";

    @FormUrlEncoded
    @POST(API_LOGIN)
    Call<LoginResult> login(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST(API_USER_INFO)
    Call<UserResult> getUserInfo(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST(API_GET_TODO)
    Call<BurNingResult> getToDo(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST(API_GET_TODO_LIST)
    Call<ToDoResult> getToDoList(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST(API_GET_OPFLOW)
    Call<JingBanResult> getOpFlow(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST(API_GET_ENTINFO)
    Call<XiaoWeiResult> getEntInfo(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST(API_GET_PROGRESS)
    Call<JinduResult> getProgress(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST(API_GET_SUPPORT_INFO_LIST)
    Call<XwComResult> getSupportInfoList(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST(API_GET_SUPPORT_INFO)
    Call<XiaoWeiResult> getSupportInfo(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST(API_GET_SUPPORT_LIST)
    Call<FuchiResult> getSupportList(@FieldMap Map<String,String> body);

    @FormUrlEncoded
    @POST(API_GET_SUPPORT)
    Call<XiaoWeiResult> getSupport(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST(API_GET_TODO_COUNT)
    Call<MsgResult> getTodoCount(@FieldMap Map<String,String> body);

    @FormUrlEncoded
    @POST(API_GET_DIC)
    Call<DicResult> getDic(@FieldMap Map<String,String> body);

    @FormUrlEncoded
    @POST(API_UPGRADE)
    Call<UpgradeResult> upgrade(@FieldMap Map<String,String> body);

    @FormUrlEncoded
    @POST(API_GET_MEINFO)
    Call<MeInfoResult> getMeInfo(@FieldMap Map<String,String> body);

    @FormUrlEncoded
    @POST(API_SUMITMEEXPLORATION)
    Call<GeneralResult> submitMeExploration(@FieldMap Map<String,String> body);

    @FormUrlEncoded
    @POST(API_GETCHILDORGAN)
    Call<ChildOrganResult> getChildOrgan(@FieldMap Map<String,String> body);

    @FormUrlEncoded
    @POST(API_DEPTSTAT)
    Call<TJResult> getDeptStat(@FieldMap Map<String,String> body);

    @FormUrlEncoded
    @POST(API_AREASTAT)
    Call<TJResult> getAreaStat(@FieldMap Map<String,String> body);

    @FormUrlEncoded
    @POST(API_INDUSTAT)
    Call<TJResult> getInduStat(@FieldMap Map<String,String> body);

    @FormUrlEncoded
    @POST(API_PROCSTAT)
    Call<TJProcessResult> getProcStat(@FieldMap Map<String,String> body);

    @FormUrlEncoded
    @POST(API_STATLIST)
    Call<StatResult> getStatList(@FieldMap Map<String,String> body);

    @Multipart
    @POST(API_UPLOAD_IMG)
    Call<ResponseBody> uploadImage(@Part("fileName") String fileName
            ,@Part("file\";filename=\"1229.jpg") RequestBody file);

    @Multipart
    @POST
    Call<ResponseBody> uploadImages(@PartMap Map<String,RequestBody> files);

}
