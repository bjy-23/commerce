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
import com.wondersgroup.commerce.model.TradeMarkDetail;
import com.wondersgroup.commerce.model.TradeMarkDetailBean;
import com.wondersgroup.commerce.model.TradeMarkDetailObjectListBean;
import com.wondersgroup.commerce.model.TradeMarkDictTypeListBean;
import com.wondersgroup.commerce.model.TradeMarkItemListBean;
import com.wondersgroup.commerce.model.TradeMarkVoList;

import java.util.Map;

import retrofit.Call;
import retrofit.http.FieldMap;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by Lee on 2016/2/1.
 */
public interface TradeMarkApi {
    //案件
    public static final String URL_BASE = "services/mobile/";
    String GET_TRADE_MARKS_LISET = URL_BASE+"getTmRegInfoList";       //获取商标列表
    String GET_TRADE_MARKS_DICT_TYPE = URL_BASE+"getDicLoad";           //获取选择字典
    String GET_TRADE_MARK_DETAIL = URL_BASE+"getTmRegInfoDetail";

    @FormUrlEncoded
    @POST(GET_TRADE_MARKS_LISET)
    public Call<TradeMarkItemListBean> getTmRegInfoList(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST(GET_TRADE_MARKS_DICT_TYPE)
    public Call<TradeMarkDictTypeListBean> getDicLoad(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST(GET_TRADE_MARK_DETAIL)
    public Call<TradeMarkDetailObjectListBean> getTradeMarkDetail(@FieldMap Map<String, String> body);

}
