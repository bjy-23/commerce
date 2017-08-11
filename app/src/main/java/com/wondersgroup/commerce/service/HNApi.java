package com.wondersgroup.commerce.service;

import com.wondersgroup.commerce.model.AttachResultObject;
import com.wondersgroup.commerce.model.BackResultObject;
import com.wondersgroup.commerce.model.CaseInvestigateDetail;
import com.wondersgroup.commerce.model.CaseInvestigateListBean;
import com.wondersgroup.commerce.model.CaseQueryResult;
import com.wondersgroup.commerce.model.DynamicComponentObject;
import com.wondersgroup.commerce.model.NoteRecordBean;
import com.wondersgroup.commerce.model.ProcedureCaseItemListBean;
import com.wondersgroup.commerce.model.ProcedureCaseQueryResult;
import com.wondersgroup.commerce.model.TotalLoginBean;
import com.wondersgroup.commerce.model.UpdateBean;
import com.wondersgroup.commerce.model.ccjc.CCCXResult;
import com.wondersgroup.commerce.model.ccjc.CCCheckResult;
import com.wondersgroup.commerce.model.ccjc.CCHCResult;
import com.wondersgroup.commerce.model.ccjc.CCOpnnResult;
import com.wondersgroup.commerce.model.ccjc.CCToDoResult;
import com.wondersgroup.commerce.model.ccjc.CCXQResult;
import com.wondersgroup.commerce.model.ccjc.DicResult;
import com.wondersgroup.commerce.model.ccjc.LoginResult;
import com.wondersgroup.commerce.model.hn.LoginRes;

import java.util.Map;

import retrofit.Call;
import retrofit.http.FieldMap;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by yclli on 16-9-9.
 */
public interface HNApi {

    //现场环境----------------------------------------------------------------
    //应用支撑
    public static final String URL_1 = "gsyth/services/mobile/";
    //抽查检查
    public static final String URL_2 = "noticemana/services/check/";
    //案件
    public static final String URL_3 = "case/services/mobile/";


//    //公司测试环境--------------------------------------------------------------
//    //应用支撑
//    public static final String URL_1 = "hebgs/services/mobile/";
//    //抽查检查
//    public static final String URL_2 = "//10.1.8.133:8001/noticemana/services/check/";  //----公司环境
//    //public static final String URL_2 = "//10.1.41.107:8080/noticemana/services/check/"; //----秦雯环境
//    //public static final String URL_2 = "//10.10.16.229:8080/noticemana/services/check/"; //----秦雯环境
//    //案件http://10.1.41.107:8080/noticemana/services/ceck/getToDoList
//    public static final String URL_3 = "case/services/mobile/";
//    //public static final String URL_3 = "//10.1.41.133:8080/hbcase/services/mobile/";

    /**
     *登录
     */
    public static final String API_LOGIN = URL_1 + "getLoginInfo";

    /**
     *移动应用版本
     */
    public static final String API_VERSION = URL_1 + "getMobileVersion";

    String DIC = URL_2 + "getDicLoad";                    //字典加载
    String DBLB = URL_2 + "getToDoList";                  //代办列表
    String CCJCCX = URL_2 + "getCheckAppList";            //抽查检查查询
    String CCJGCX = URL_2 + "getCheckRecord";             //抽查结果详情
    String CCBLYJ = URL_2 + "getNoticeOpnnList";          //查询办理意见
    String DBCX = URL_2 + "getToDo";                      //查询待办--已废弃
    String CCXQ = URL_2 + "getCheckEntityInfo";           //抽查详情
    String SDHC = URL_2 + "submitLocalRecord";            //提交实地核查信息

    String INVESTIGATE_CASE_LIST = URL_3 + "myCaseToInvestigate";       //获取待调查案件列表
    String INVESTIGATE_CASE_DETAIL = URL_3 + "getCaseRegDetail";        //案件详情获取
    String NOTE_RECORD_LIST = URL_3 + "queryInvestigateList";           //获取现场笔录和询问笔录列表
    String CASE_INSPECT_EDIT = URL_3 + "toInspectEdit";                 //现场检查（新增、修改）
    String CASE_INSPECT_SAVE = URL_3 + "saveInspect";                   //保存现场检查
    String CASE_INSPECT_DELETE = URL_3 + "removeInspect";               //删除现场检查
    String CASE_ENQUIRE_EDIT = URL_3 + "toEnquireEdit";                 //询问笔录(新增、修改)
    String CASE_ENQUIRE_SAVE = URL_3 + "saveEnquire";                   //保存询问笔录
    String CASE_ENQUIRE_DELETE = URL_3 + "removeEnquire";               //删除询问笔录
    String CASE_QUERY_CONDITION = URL_3 + "toQueryCase";                //案件查询页面
    String CASE_QUERY_MY_CASE = URL_3 + "queryMyCase";                  //案件条件查询
    String CASE_GENERAL_DETAIL = URL_3 + "getCaseGeneralDetail";        //案件总览
    String DOWNLAODER_ATTACH = URL_3 + "downloadAttachFileByPost";      //附件下载

    String PROCEDURE_CASE_LIST = URL_3 + "querySimpleCaseList";       //获取简易程序案件列表
    String PROCEDURE_CASE_QUERY = URL_3 + "";     //获取简易程序案件条件查询

    @FormUrlEncoded
    @POST(API_LOGIN)
    public Call<TotalLoginBean> login(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST(API_VERSION)
    public Call<UpdateBean> apiUpdate(@FieldMap Map<String, String> body);

    /**
    * 抽查检查
    * ——————————————————————————————————————————————————————————————————————————————
    */
    @FormUrlEncoded
    @POST(DBLB)
    Call<CCToDoResult> ccTodo(@FieldMap Map<String,String> body);

    @FormUrlEncoded
    @POST(DIC)
    Call<DicResult> ccDic(@FieldMap Map<String,String> body);

    @FormUrlEncoded
    @POST(CCJCCX)
    Call<CCCXResult> ccCX(@FieldMap Map<String,String> body);

    @FormUrlEncoded
    @POST(CCJGCX)
    Call<CCCheckResult> ccJG(@FieldMap Map<String,String> body);

    @FormUrlEncoded
    @POST(CCBLYJ)
    Call<CCOpnnResult> ccOpnn(@FieldMap Map<String,String> body);

    @FormUrlEncoded
    @POST(CCXQ)
    Call<CCXQResult> ccXQ(@FieldMap Map<String,String> body);

    @FormUrlEncoded
    @POST(SDHC)
    Call<CCHCResult> ccSDHC(@FieldMap Map<String,String> body);
    //————————————————————————————————————————————————————————————————————————————————

    /**
     * 案件调查
     * ——————————————————————————————————————————————————————————————————————————————
     */
    @FormUrlEncoded
    @POST(INVESTIGATE_CASE_LIST)
    public Call<CaseInvestigateListBean> getMyCaseList(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST(INVESTIGATE_CASE_DETAIL)
    public Call<CaseInvestigateDetail> getCaseRegDetail(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST(NOTE_RECORD_LIST)
    public Call<NoteRecordBean> queryInvestigateList(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST(CASE_INSPECT_EDIT)
    public Call<DynamicComponentObject> toInspectEdit(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST(CASE_INSPECT_DELETE)
    public Call<BackResultObject> deleteInspect(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST(CASE_INSPECT_SAVE)
    public Call<BackResultObject> saveInspect(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST(CASE_ENQUIRE_EDIT)
    public Call<DynamicComponentObject> toEnquireEdit(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST(CASE_ENQUIRE_SAVE)
    public Call<BackResultObject> saveEnquire(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST(CASE_ENQUIRE_DELETE)
    public Call<BackResultObject> deleteEnquire(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST(CASE_QUERY_CONDITION)
    public Call<DynamicComponentObject> toQueryCase(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST(CASE_QUERY_MY_CASE)
    public Call<CaseQueryResult> queryMyCase(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST(CASE_GENERAL_DETAIL)
    public Call<DynamicComponentObject> getCaseGeneralDetail(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST(DOWNLAODER_ATTACH)
    public Call<AttachResultObject> downLoadAttchment(@FieldMap Map<String, String> body);

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
