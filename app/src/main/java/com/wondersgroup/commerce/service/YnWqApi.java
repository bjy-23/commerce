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
    String BSAE = "services/mobile/";
    String API_LOGIN=BSAE + "loginInfo";
    String API_USER_INFO=BSAE + "getUserInfo";
    String API_GET_TODO=BSAE + "getToDo";
    String API_GET_DIC=BSAE + "getDicLoad";
    String API_GET_TODO_LIST=BSAE + "getToDoList";
    String API_GET_OPFLOW=BSAE + "getToDoInfo";
    String API_GET_ENTINFO=BSAE + "getEtpsInfo";
    String API_GET_SUPPORT_INFO=BSAE + "getMeSupportInfo";
    String API_SUMITMEEXPLORATION=BSAE + "submitMeExploration";
    String API_GET_MEINFO=BSAE + "getMeInfo";
    String API_GET_PROGRESS=BSAE + "getEtpsInfoList";
    String API_GET_SUPPORT_INFO_LIST =BSAE + "getMeEntityInfoList";
    String API_GET_SUPPORT=BSAE + "getMeSupport";
    String API_GET_SUPPORT_LIST=BSAE + "getMeSupportList";
    String API_DEPTSTAT=BSAE + "meDeptStat";//统计，部门
    String API_INDUSTAT=BSAE + "meIndustryStat";
    String API_PROCSTAT=BSAE + "meProcessStat";
    String API_GETCHILDORGAN=BSAE + "getOrganByParent";
    String API_AREASTAT=BSAE + "meAreaStat";//统计，区域
    String API_STATLIST=BSAE + "getStatList";

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
