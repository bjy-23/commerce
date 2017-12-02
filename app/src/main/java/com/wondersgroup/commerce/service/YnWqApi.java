package com.wondersgroup.commerce.service;

import com.wondersgroup.commerce.ynwq.bean.BurNingResult;
import com.wondersgroup.commerce.ynwq.bean.ChildOrganResult;
import com.wondersgroup.commerce.ynwq.bean.DicResult;
import com.wondersgroup.commerce.ynwq.bean.FuchiResult;
import com.wondersgroup.commerce.ynwq.bean.GeneralResult;
import com.wondersgroup.commerce.ynwq.bean.JinduResult;
import com.wondersgroup.commerce.ynwq.bean.JingBanResult;
import com.wondersgroup.commerce.ynwq.bean.LoginResult;
import com.wondersgroup.commerce.ynwq.bean.MeInfoResult;
import com.wondersgroup.commerce.ynwq.bean.StatResult;
import com.wondersgroup.commerce.ynwq.bean.TJProcessResult;
import com.wondersgroup.commerce.ynwq.bean.TJResult;
import com.wondersgroup.commerce.ynwq.bean.ToDoResult;
import com.wondersgroup.commerce.ynwq.bean.UserResult;
import com.wondersgroup.commerce.ynwq.bean.XiaoWeiResult;
import com.wondersgroup.commerce.ynwq.bean.XwComResult;

import java.util.Map;

import retrofit.Call;
import retrofit.http.FieldMap;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by bjy on 2017/3/17.
 */

public interface YnWqApi {
    String PREFIX = "me/services/mobile/";
    String API_LOGIN=PREFIX + "loginInfo";
    String API_USER_INFO=PREFIX + "getUserInfo";
    String API_GET_TODO=PREFIX + "getToDo";
    String API_GET_DIC=PREFIX + "getDicLoad";
    String API_GET_TODO_LIST=PREFIX + "getToDoList";
    String API_GET_OPFLOW=PREFIX + "getToDoInfo";
    String API_GET_ENTINFO=PREFIX + "getEtpsInfo";
    String API_GET_SUPPORT_INFO=PREFIX + "getMeSupportInfo";
    String API_SUMITMEEXPLORATION=PREFIX + "submitMeExploration";
    String API_GET_MEINFO=PREFIX + "getMeInfo";
    String API_GET_PROGRESS=PREFIX + "getEtpsInfoList";
    String API_GET_SUPPORT_INFO_LIST =PREFIX + "getMeEntityInfoList";
    String API_GET_SUPPORT=PREFIX + "getMeSupport";
    String API_GET_SUPPORT_LIST=PREFIX + "getMeSupportList";
    String API_DEPTSTAT=PREFIX + "meDeptStat";//统计，部门
    String API_INDUSTAT=PREFIX + "meIndustryStat";
    String API_PROCSTAT=PREFIX + "meProcessStat";
    String API_GETCHILDORGAN=PREFIX + "getOrganByParent";
    String API_AREASTAT=PREFIX + "meAreaStat";//统计，区域
    String API_STATLIST=PREFIX + "getStatList";
    String API_MOBILE_UPLOAD_FILE = "mobileUploadFile.do";

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
    @POST(API_GET_DIC)
    Call<DicResult> getDic(@FieldMap Map<String,String> body);

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
    @POST(API_GET_SUPPORT_INFO)
    Call<XiaoWeiResult> getSupportInfo(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST(API_SUMITMEEXPLORATION)
    Call<GeneralResult> submitMeExploration(@FieldMap Map<String,String> body);

    @FormUrlEncoded
    @POST(API_GET_MEINFO)
    Call<MeInfoResult> getMeInfo(@FieldMap Map<String,String> body);

    @FormUrlEncoded
    @POST(API_GET_PROGRESS)
    Call<JinduResult> getProgress(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST(API_GET_SUPPORT_INFO_LIST)
    Call<XwComResult> getSupportInfoList(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST(API_GET_SUPPORT)
    Call<XiaoWeiResult> getSupport(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST(API_GET_SUPPORT_LIST)
    Call<FuchiResult> getSupportList(@FieldMap Map<String,String> body);

    @FormUrlEncoded
    @POST(API_DEPTSTAT)
    Call<TJResult> getDeptStat(@FieldMap Map<String,String> body);

    @FormUrlEncoded
    @POST(API_INDUSTAT)
    Call<TJResult> getInduStat(@FieldMap Map<String,String> body);

    @FormUrlEncoded
    @POST(API_PROCSTAT)
    Call<TJProcessResult> getProcStat(@FieldMap Map<String,String> body);

    @FormUrlEncoded
    @POST(API_GETCHILDORGAN)
    Call<ChildOrganResult> getChildOrgan(@FieldMap Map<String,String> body);

    @FormUrlEncoded
    @POST(API_AREASTAT)
    Call<TJResult> getAreaStat(@FieldMap Map<String,String> body);

    @FormUrlEncoded
    @POST(API_STATLIST)
    Call<StatResult> getStatList(@FieldMap Map<String,String> body);
}
