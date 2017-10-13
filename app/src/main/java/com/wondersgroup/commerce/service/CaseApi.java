package com.wondersgroup.commerce.service;

import com.squareup.okhttp.ResponseBody;
import com.wondersgroup.commerce.model.AttachBean;
import com.wondersgroup.commerce.model.AttachResultObject;
import com.wondersgroup.commerce.model.BackResultObject;
import com.wondersgroup.commerce.model.CaseInvestigateDetail;
import com.wondersgroup.commerce.model.CaseInvestigateListBean;
import com.wondersgroup.commerce.model.CaseQueryDic;
import com.wondersgroup.commerce.model.CaseQueryResult;
import com.wondersgroup.commerce.model.DynamicComponentObject;
import com.wondersgroup.commerce.model.FileBean;
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
 * Created by bjy on 2017/6/21.
 */

public interface CaseApi {
    public static final String URL_CASE_1 = "case/services/mobile/";
//    public static final String URL_CASE_1 = "sccase/services/mobile/";
    public static final String URL_CASE_2 = "services/mobile/";
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
    public static final String CASE_QUERY_LIST = "caseQuery_list";
    public static final String TO_CASE_QUERY = "toCaseQuery";
    public static final String DOWNLOAD_WRIT_FILE_BY_POST = "downloadWritFileByPost";

    /*
    * 简易程序
    * */
    String PROCEDURE_CASE_LIST = URL_CASE_1 + "querySimpleCaseList";       //获取简易程序案件列表
    String PROCEDURE_CASE_DETAIL = URL_CASE_1 + "toSimpleCaseEdit";       //获取简易程序案件详情
    String PROCEDURE_CASE_DISTRICT_LIST = URL_CASE_1 + "caseSpotQuery";       //案发地（区）列表
    String PROCEDURE_CASE_ILLEGAL_TYPE = URL_CASE_1 + "querySubMap";       //违法行为小类查询
    String PROCEDURE_CASE_ILLEGAL_CODE = URL_CASE_1 + "queryActMap";       //违法行为代码查询
    String PROCEDURE_CASE_ILLEGAL_LAW = URL_CASE_1 + "queryDicLawIlglActnList";       //违法行为定性和处罚依据查询
    String PROCEDURE_CASE_SUBMIT = URL_CASE_1 + "toSimpleCaseEdit";       //简易程序案件提交/新增时获得字典列表
    String PROCEDURE_CASE_SAVE = URL_CASE_1 + "toSimpleCaseSave";       //简易程序案件新增保存和修改保存
    String PROCEDURE_CASE_LEGAL_BASIS = URL_CASE_1 + "getLegalBasisList";     //定性/处罚依据（G）查询
    String PROCEDURE_CASE_AIC_INQUIRY_PERSON = URL_CASE_1 + "queryPersonList";     //查询个体工商户登记信息(5.3.3. 个体工商户查询)
    String PROCEDURE_CASE_AIC_INQUIRY_COMPANY = URL_CASE_1 + "queryEtpsList";     //查询个体工商户登记信息(5.3.6. 企业选择)
    String PROCEDURE_CASE_QUERY_PERSON_SELECT = URL_CASE_1 + "queryPersonSelected";     //5.3.4. 个体工商户选择
    String PROCEDURE_CASE_QUERY_COMPANY_SELECT = URL_CASE_1 + "queryEtpsSelected ";     //5.3.6. 企业选择
    String PROCEDURE_CASE_DOWNLAODER_ATTACH =URL_CASE_1 + "downloadAttachFileByPost";      //附件下载
    String PROCEDURE_CASE_BRAND_TYPE = URL_CASE_1 + "queryTrademarkMap";             //商标类型二级菜单

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


    @FormUrlEncoded
    @POST
    public Call<Result<CaseQueryDic>> caseQueryList(@Url String url, @FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST
    public Call<Result<List<CaseQueryBean>>> toCaseQuery(@Url String url, @FieldMap Map<String, String> map);

    @POST
    @FormUrlEncoded
    public Call<Result<AttachBean>> downloadDoc(@Url String url, @FieldMap Map<String, String> param);

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
    @POST(PROCEDURE_CASE_BRAND_TYPE)
    public Call<ProcedureCaseIllegalSmalTypeResultObject> getBrandTypeSecondLevel(@FieldMap Map<String, String> body);

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

//    @FormUrlEncoded
//    @POST(PROCEDURE_CASE_DOWNLAODER_ATTACH)
//    public Call<ProcedureCaseAttachResultObject> procedureCaseDownLoadAttchment(@FieldMap Map<String, String> body);
    @FormUrlEncoded
    @POST(PROCEDURE_CASE_DOWNLAODER_ATTACH)
    public Call<FileBean> procedureCaseDownLoadAttchment(@FieldMap Map<String, String> body);
}
