package com.wondersgroup.commerce.service;

import com.wondersgroup.commerce.model.AttachResultObject;
import com.wondersgroup.commerce.model.BackResultObject;
import com.wondersgroup.commerce.model.CaseInvestigateDetail;
import com.wondersgroup.commerce.model.CaseInvestigateListBean;
import com.wondersgroup.commerce.model.CaseQueryResult;
import com.wondersgroup.commerce.model.DynamicComponentObject;
import com.wondersgroup.commerce.model.NoteRecordBean;
import com.wondersgroup.commerce.model.ProcedureCaseAICRequiryComResultObject;
import com.wondersgroup.commerce.model.ProcedureCaseAICRequiryResultObject;
import com.wondersgroup.commerce.model.ProcedureCaseAttachResultObject;
import com.wondersgroup.commerce.model.ProcedureCaseDetail;
import com.wondersgroup.commerce.model.ProcedureCaseIllegalCodeResultObject;
import com.wondersgroup.commerce.model.ProcedureCaseIllegalSmalTypeResultObject;
import com.wondersgroup.commerce.model.ProcedureCaseItemListBean;
import com.wondersgroup.commerce.model.ProcedureCaseLegalBasisResult;
import com.wondersgroup.commerce.model.ProcedureCaseQueryCompanySelectedResultObject;
import com.wondersgroup.commerce.model.ProcedureCaseQueryPersonSelectedResultObject;
import com.wondersgroup.commerce.model.ProcedureCaseQueryResult;
import com.wondersgroup.commerce.model.SubmitRouteReqResult;


import java.util.Map;

import retrofit.Call;
import retrofit.http.FieldMap;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by Lee on 2016/2/1.
 */
public interface CaseModule {
    //案件
//    public static final String URL_3 = "case/services/mobile/";
    public static final String URL_3 = "services/mobile/";
    String INVESTIGATE_CASE_LIST = "myCaseToInvestigate";       //获取待调查案件列表
    String INVESTIGATE_CASE_DETAIL = "getCaseRegDetail";        //案件详情获取
    String NOTE_RECORD_LIST = "queryInvestigateList";           //获取现场笔录和询问笔录列表
    String CASE_INSPECT_EDIT = "toInspectEdit";                 //现场检查（新增、修改）
    String CASE_INSPECT_SAVE = "saveInspect";                   //保存现场检查
    String CASE_INSPECT_DELETE = "removeInspect";               //删除现场检查
    String CASE_ENQUIRE_EDIT = "toEnquireEdit";                 //询问笔录(新增、修改)
    String CASE_ENQUIRE_SAVE = "saveEnquire";                   //保存询问笔录
    String CASE_ENQUIRE_DELETE = "removeEnquire";               //删除询问笔录
    String CASE_QUERY_CONDITION = "toQueryCase";                //案件查询页面
    String CASE_QUERY_MY_CASE = "queryMyCase";                  //案件条件查询
    String CASE_GENERAL_DETAIL = "getCaseGeneralDetail";        //案件总览
    String DOWNLAODER_ATTACH = "downloadAttachFileByPost";      //附件下载
    String PROCEDURE_CASE_LIST = URL_3 + "querySimpleCaseList";       //获取简易程序案件列表
    String PROCEDURE_CASE_DETAIL = URL_3 + "toSimpleCaseEdit";       //获取简易程序案件详情
    String PROCEDURE_CASE_DISTRICT_LIST = URL_3 + "caseSpotQuery";       //案发地（区）列表
    String PROCEDURE_CASE_ILLEGAL_TYPE = URL_3 + "querySubMap";       //违法行为小类查询
    String PROCEDURE_CASE_ILLEGAL_CODE = URL_3 + "queryActMap";       //违法行为代码查询
    String PROCEDURE_CASE_ILLEGAL_LAW = URL_3 + "queryDicLawIlglActnList";       //违法行为定性和处罚依据查询
    String PROCEDURE_CASE_SUBMIT = URL_3 + "toSimpleCaseEdit";       //简易程序案件提交/新增时获得字典列表
    String PROCEDURE_CASE_SAVE = URL_3 + "toSimpleCaseSave";       //简易程序案件新增保存和修改保存
    String PROCEDURE_CASE_LEGAL_BASIS = URL_3 + "getLegalBasisList";     //定性/处罚依据（G）查询
    String PROCEDURE_CASE_AIC_INQUIRY_PERSON = URL_3 + "queryPersonList";     //查询个体工商户登记信息(5.3.3. 个体工商户查询)
    String PROCEDURE_CASE_AIC_INQUIRY_COMPANY = URL_3 + "queryEtpsList";     //查询个体工商户登记信息(5.3.6. 企业选择)
    String PROCEDURE_CASE_QUERY_PERSON_SELECT = URL_3 + "queryPersonSelected";     //5.3.4. 个体工商户选择
    String PROCEDURE_CASE_QUERY_COMPANY_SELECT = URL_3 + "queryEtpsSelected ";     //5.3.6. 企业选择
    String PROCEDURE_CASE_QUERY = URL_3 + "";     //获取简易程序案件条件查询
    String PROCEDURE_CASE_DOWNLAODER_ATTACH = URL_3 + "downloadAttachFileByPost";      //附件下载

    String ROUTE_MAP_TEST_SUBMIT_ROUTE = "ad!doSaveTempRoute.action";       //提交坐标集合
    String ROUTE_MAP_TEST_GET_ROUTE = "ad!doQueryJobList.action";       //获得坐标集合
    String ROUTE_MAP_TEST_LOGIN = "login!logonForMobile.action";       //提交坐标集合


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

    /**
     * 简易程序案件
     * ——————————————————————————————————————————————————————————————————————————————
     */
    @FormUrlEncoded
    @POST(PROCEDURE_CASE_LIST)
    public Call<ProcedureCaseItemListBean> getProcedureCaseList(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST(PROCEDURE_CASE_DETAIL)
    public Call<ProcedureCaseDetail> getProcedureCaseDetail(@FieldMap Map<String, String> body);


    @FormUrlEncoded
    @POST(PROCEDURE_CASE_DISTRICT_LIST)
    public Call<ProcedureCaseIllegalSmalTypeResultObject> getCaseDistrictsType(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST(PROCEDURE_CASE_ILLEGAL_TYPE)
    public Call<ProcedureCaseIllegalSmalTypeResultObject> getProcedureCaseIllegalSmallType(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST(PROCEDURE_CASE_ILLEGAL_CODE)
    public Call<ProcedureCaseIllegalSmalTypeResultObject> getProcedureCaseIllegalCode(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST(PROCEDURE_CASE_ILLEGAL_LAW)
    public Call<ProcedureCaseIllegalCodeResultObject> getProcedureCaseIllegalLaw(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST(PROCEDURE_CASE_SAVE)
    public Call<ProcedureCaseDetail> submitProcedureCase(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST(PROCEDURE_CASE_SUBMIT)
    public Call<ProcedureCaseDetail> getProcedureCaseVols(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST(PROCEDURE_CASE_QUERY)
    public Call<ProcedureCaseQueryResult> queryProcedureCase(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST(PROCEDURE_CASE_LEGAL_BASIS)
    public Call<ProcedureCaseLegalBasisResult> queryProcedureCaseLegalBasis(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST(PROCEDURE_CASE_AIC_INQUIRY_PERSON)
    public Call<ProcedureCaseAICRequiryResultObject> queryProcedureCaseAICInquiryPerson(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST(PROCEDURE_CASE_AIC_INQUIRY_COMPANY)
    public Call<ProcedureCaseAICRequiryComResultObject> queryProcedureCaseAICInquiryCompany(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST(PROCEDURE_CASE_QUERY_PERSON_SELECT)
    public Call<ProcedureCaseQueryPersonSelectedResultObject> queryPersonSelected(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST(PROCEDURE_CASE_QUERY_COMPANY_SELECT)
    public Call<ProcedureCaseQueryCompanySelectedResultObject> queryEtpsSelected (@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST(PROCEDURE_CASE_DOWNLAODER_ATTACH)
    public Call<ProcedureCaseAttachResultObject> procedureCaseDownLoadAttchment(@FieldMap Map<String, String> body);


    



}
