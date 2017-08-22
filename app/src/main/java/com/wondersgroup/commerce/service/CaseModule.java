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


    String ROUTE_MAP_TEST_SUBMIT_ROUTE = "ad!doSaveTempRoute.action";       //提交坐标集合
    String ROUTE_MAP_TEST_GET_ROUTE = "ad!doQueryJobList.action";       //获得坐标集合
    String ROUTE_MAP_TEST_LOGIN = "login!logonForMobile.action";       //提交坐标集合

    /**
     * 简易程序案件
     * ——————————————————————————————————————————————————————————————————————————————
     */



    



}
