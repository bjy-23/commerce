package com.wondersgroup.commerce.service;

import com.wondersgroup.commerce.model.ShLoginBean;
import com.wondersgroup.commerce.model.ShLoginDeptBean;
import com.wondersgroup.commerce.teamwork.myhwggdq.HwggdqBean;
import com.wondersgroup.commerce.teamwork.myhwggdq.HwggdqDetailBean;
import com.wondersgroup.commerce.teamwork.myrcxc.RCXCCompanyBean;
import com.wondersgroup.commerce.teamwork.myrcxc.RCXCPersonBean;
import com.wondersgroup.commerce.teamwork.myxqzgdq.CheckJGJLBean;
import com.wondersgroup.commerce.teamwork.myxqzgdq.CompanyJBXXBean;
import com.wondersgroup.commerce.teamwork.myxqzgdq.CompanyPersonBean;
import com.wondersgroup.commerce.teamwork.myxqzgdq.PersonJBXXBean;
import com.wondersgroup.commerce.teamwork.myxqzgdq.XqzgdqGLXXBean;
import com.wondersgroup.commerce.teamwork.wywork.jcjgno.bean.JcjgbzcList;
import com.wondersgroup.commerce.teamwork.wywork.jyqx.Bean.JyqxdqBean;
import com.wondersgroup.commerce.teamwork.wywork.jyqx.Bean.JyqxdqGlxxBean;
import com.wondersgroup.commerce.teamwork.wywork.jyqx.Bean.JyqxdqJbxxBean;
import com.wondersgroup.commerce.teamwork.wywork.ydjyxz.bean.YDJYAddBean;
import com.wondersgroup.commerce.teamwork.wywork.ydjyxz.bean.YDJYTreeBean;

import java.util.Map;

import retrofit.Call;
import retrofit.http.FieldMap;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by kangrenhui on 2016/1/27.
 */
public interface ShApi {
    String API_LOGIN = "login!logonForMobile.action";
    String API_LOGIN_DEPT_CHOOSE = "dic!queryAllRootOrganListForMobile.action";
    String API_XQZGDQ_COMPANY="countLimit!doQueryForMobile.action?unitType=02";
    String API_XQZGDQ_PERSON="countLimit!doQueryForMobile.action?unitType=06";
    String API_XQZGDQ_COMPANY_JBXI="detail!toQueryForMobile.action?entityTypeId=02";
    String API_XQZGDQ_COMPANY_GLXX="CMInfo!toMangeInfoForMobile.action?entityTypeId=02";

    String API_XQZGDQ_PERSON_JBXX="detail!toQueryForMobile.action?entityTypeId=06";
    String API_XQZGDQ_PERSON_GLXX="CMInfo!toMangeInfoForMobile.action?entityTypeId=06";
    String API_XQZGDQ_GLXX_JGJL="check!toEditCheckLogForMobile.action";

    String API_JYQXDQ_INDEX   =   "preRegister!doEndDateQueryForMobile.action?unitType=02";
    String API_JYQXDQ_GLXX  =   "CMInfo!toMangeInfoForMobile.action?entityTypeId=02";
    String API_JYQXDQ_JBXX  =   "detail!toQueryForMobile.action?entityTypeId=02";
    String API_HWGGDQ_EXPIRE  =   "ad!doQueryExpireForMobile.action";
    String API_HWGGDQ_DETAIL  =   "ad!doQueryDetailForMobile.action";
    String API_JCJGBZC_QY  =   "preRegister!doQueryCheckResultForMobile.action?unitType=02";
    String API_JCJGBZC_GR  =   "preRegister!doQueryCheckResultForMobile.action?unitType=06";
    String API_YDJY_Query_QY  =   "query!queryEtpsForMobile.action?method=query";
    String API_YDJY_Query_GR  =   "preRegister!doQueryCheckResultForMobile.action?unitType=06";
    String API_YDJY_TREE  =   "ednDiffPlc!getDoorType.action";

    String API_RCXC_COMPANY  =   "query!queryEtpsForMobile.action?unitType=02&operType=05";
    String API_RCXC_PERSON  =   "query!queryEtpsForMobile.action?unitType=06&operType=05";


    @FormUrlEncoded
    @POST(API_LOGIN_DEPT_CHOOSE)
    public Call<ShLoginDeptBean> dept(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST(API_LOGIN)
    public Call<ShLoginBean> login(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST(API_XQZGDQ_COMPANY)
    public Call<CompanyPersonBean> queryXQZGDQCompany(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST(API_XQZGDQ_PERSON)
    public Call<CompanyPersonBean> queryXQZGDQPerson(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST(API_XQZGDQ_COMPANY_JBXI)
    public Call<CompanyJBXXBean> queryCompanyJBXX(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST(API_XQZGDQ_COMPANY_GLXX)
    public Call<XqzgdqGLXXBean> queryCompanyGLXX(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST(API_XQZGDQ_PERSON_JBXX)
    public Call<PersonJBXXBean> queryPersonJBXX(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST(API_XQZGDQ_PERSON_GLXX)
    public Call<XqzgdqGLXXBean> queryPersonGLXX(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST(API_XQZGDQ_GLXX_JGJL)
    public Call<CheckJGJLBean> queryCheckJGJLDetail(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST(API_JYQXDQ_INDEX)
    public Call<JyqxdqBean> jyqxdq_index(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST(API_JYQXDQ_GLXX)
    public Call<JyqxdqGlxxBean> jyqxdq_glxx(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST(API_JYQXDQ_JBXX)
    public Call<JyqxdqJbxxBean> jyqxdq_jbxx(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST(API_HWGGDQ_EXPIRE)
    public Call<HwggdqBean> queryHWGGDQExpire(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST(API_HWGGDQ_DETAIL)
    public Call<HwggdqDetailBean> queryHWGGDQDetail(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST(API_JCJGBZC_QY)
    public Call<JcjgbzcList> queryJCJGBZCqy(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST(API_JCJGBZC_GR)
    public Call<JcjgbzcList> queryJCJGBZCgr(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST(API_YDJY_Query_QY)
    public Call<YDJYAddBean> queryYDJYqy(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST(API_YDJY_TREE)
    public Call<YDJYTreeBean> queryYDJYTree(@FieldMap Map<String, String> body);


    @FormUrlEncoded
    @POST(API_RCXC_COMPANY)
    public Call<RCXCCompanyBean> queryRCXCCompany(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST(API_RCXC_PERSON)
    public Call<RCXCPersonBean> queryRCXCPerson(@FieldMap Map<String, String> body);

 }
