package com.wondersgroup.commerce.service;

import com.squareup.okhttp.ResponseBody;
import com.wondersgroup.commerce.model.Address;
import com.wondersgroup.commerce.model.AddressDetail;
import com.wondersgroup.commerce.model.AttachResultObject;
import com.wondersgroup.commerce.model.BackResultObject;
import com.wondersgroup.commerce.model.BacklogListBean;
import com.wondersgroup.commerce.model.BaseRes;
import com.wondersgroup.commerce.model.CaseInvestigateDetail;
import com.wondersgroup.commerce.model.CaseInvestigateListBean;
import com.wondersgroup.commerce.model.CaseQueryResult;
import com.wondersgroup.commerce.model.Dept;
import com.wondersgroup.commerce.model.DeptOne;
import com.wondersgroup.commerce.model.DeptTwo;
import com.wondersgroup.commerce.model.DynamicComponentObject;
import com.wondersgroup.commerce.model.FileBean;
import com.wondersgroup.commerce.model.FwTypeBean;
import com.wondersgroup.commerce.model.GgDetails;
import com.wondersgroup.commerce.model.Ggcx;
import com.wondersgroup.commerce.model.GwNx;
import com.wondersgroup.commerce.model.GwjsBean;
import com.wondersgroup.commerce.model.Menu;
import com.wondersgroup.commerce.model.MenuBean;
import com.wondersgroup.commerce.model.ProcedureCaseItemListBean;
import com.wondersgroup.commerce.model.ProcedureCaseQueryResult;
import com.wondersgroup.commerce.model.TotalLoginBean;
import com.wondersgroup.commerce.model.NextPeoListBean;
import com.wondersgroup.commerce.model.NoteRecordBean;
import com.wondersgroup.commerce.model.ReceiveDetailBean;
import com.wondersgroup.commerce.model.ReqResult;
import com.wondersgroup.commerce.model.SendDetailBean;
import com.wondersgroup.commerce.model.SwTypeBean;
import com.wondersgroup.commerce.model.TodoMessageBean;
import com.wondersgroup.commerce.model.UpdateBean;

import java.util.List;
import java.util.Map;

import retrofit.Call;
import retrofit.http.FieldMap;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by yclli on 2016/3/3.
 */
public interface HbApi {
    //登录&消息
    public static final String URL_2 = "gsyth/services/mobile/";
    //案件
    public static final String URL_CASE = "case/services/mobile/";

    String API_LOGIN = URL_2 + "getLoginInfo";
//    String API_LOGIN = "services/mobile/getLoginInfo";
    String GET_MENU_BY_USE_RID = URL_2 + "getMenuByUserId";
//    String GET_MENU_BY_USE_RID = "services/mobile/getMenuByUserId";

    String API_ADDRESS_DEPT_ALL = URL_2 + "getAllOrgan";
    String API_ADDRESS_DEPT_ORGANID = URL_2 + "getChildByOrganId";
    String API_ADDRESS_DEPT_ORGAN = URL_2 + "getOrganByDept";


    String PROCEDURE_CASE_LIST = URL_CASE + "";       //获取简易程序案件列表
    String PROCEDURE_CASE_QUERY = URL_CASE + "";     //获取简易程序案件条件查询

    /**
     *移动应用版本
     */
    public static final String API_VERSION = URL_2 + "getMobileVersion";


    /**
     * 待办消息提醒列表
     */
    String API_TODOMSGLIST = URL_2 + "getTodoMessageList";
    /**
     * 待办消息设置已读
     */
    String API_TODOMSGDONED = URL_2 + "getTodoMessageDoned";





    @FormUrlEncoded
    @POST(API_LOGIN)
    public Call<TotalLoginBean> login(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST(GET_MENU_BY_USE_RID)
    public Call<Result<Menu>> getMenu(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST(API_VERSION)
    public Call<UpdateBean> apiUpdate(@FieldMap Map<String, String> body);


    @FormUrlEncoded
    @POST(API_TODOMSGLIST)
    public Call<TodoMessageBean> apiTodoMsgList(@FieldMap Map<String, String> body);



    @FormUrlEncoded
    @POST(API_TODOMSGDONED)
    public Call<ReqResult> apiTodoMsgDoned(@FieldMap Map<String, String> body);




    /**
     * 通讯录模块
     * ——————————————————————————————————————————————————————————————————————————————
     */



    //获取部门
    @FormUrlEncoded
    @POST(API_ADDRESS_DEPT_ALL)
    public Call<Dept> deptAll(@FieldMap Map<String, String> body);

    //获取部门
    @FormUrlEncoded
    @POST(API_ADDRESS_DEPT_ORGANID)
    public Call<DeptTwo> deptOrganId(@FieldMap Map<String, String> body);

    //获取部门
    @FormUrlEncoded
    @POST(API_ADDRESS_DEPT_ORGAN)
    public Call<DeptOne> deptOrganDept(@FieldMap Map<String, String> body);



    //————————————————————————————————————————————————————————————————————————————————





    //————————————————————————————————————————————————————————————————————————————————





    /**
     * 简易程序案件
     * ——————————————————————————————————————————————————————————————————————————————
     */
    @FormUrlEncoded
    @POST(PROCEDURE_CASE_LIST)
    public Call<ProcedureCaseItemListBean> getProcedureCaseList(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST(PROCEDURE_CASE_QUERY)
    public Call<ProcedureCaseQueryResult> queryProcedureCase(@FieldMap Map<String, String> body);

}
