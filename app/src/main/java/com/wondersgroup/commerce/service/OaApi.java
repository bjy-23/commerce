package com.wondersgroup.commerce.service;

import com.wondersgroup.commerce.model.Address;
import com.wondersgroup.commerce.model.AddressDetail;
import com.wondersgroup.commerce.model.BacklogListBean;
import com.wondersgroup.commerce.model.BaseRes;
import com.wondersgroup.commerce.model.FileBean;
import com.wondersgroup.commerce.model.FwTypeBean;
import com.wondersgroup.commerce.model.GgDetails;
import com.wondersgroup.commerce.model.Ggcx;
import com.wondersgroup.commerce.model.GwNx;
import com.wondersgroup.commerce.model.GwjsBean;
import com.wondersgroup.commerce.model.NextPeoListBean;
import com.wondersgroup.commerce.model.ReceiveDetailBean;
import com.wondersgroup.commerce.model.SendDetailBean;
import com.wondersgroup.commerce.model.SwTypeBean;

import java.util.Map;

import retrofit.Call;
import retrofit.http.FieldMap;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by bjy on 2017/7/12.
 */

public interface OaApi {
    //收发文
    public static final String URL_1 = "services/mobileDoc/";
    //其他
    public static final String URL_3 = "services/mobile/";
    //附件
    public static final String URL_ATTACHMENT = "services/esf/backlogWebService/";

    /**
     * 最新10条公文
     */
    String API_NUXDOCLIST = URL_1 + "newestDocList";
    /**
     * 获取收发文待办待阅列表
     *
     * @param userId
     * @param deptId
     * @param businessType           1待办, 2待阅, 0全部
     * @param pageNo                 页号
     * @param pageSize               每页的数据条数
     */
    String API_BACKLOGLIST = URL_1 + "getBacklogListByPost";
    /**
     * 待办列表点击办理进入发文办理页面
     *
     * @param userId
     * @param flowUuid               流程ID（32位）
     * @param activityUuid           待办ID（32位）
     * @param activityId             环节编号（10位）
     */
    String API_HANDLESEND = URL_1 + "toDocSendTransactByPost";
    /**
     * 待办列表点击办理进入收文办理页面
     *
     * @param flowUuid
     * @param activityUuid
     * @param activityId
     */
    String API_HANDLERECEIVE = URL_1 + "toDocReceiveTransactByPost";
    /**
     * 流程操作
     * @param wsCodeReq
     * @param userId
     * @param deptId
     * @param areaCode
     * @param organCode
     * @param authority
     * @param authId
     * @param userflag
     * @param includeDept
     */
    public final String API_NEXTPEOLIST = URL_1 + "selectNextTransactListByPost";
    /**
     * 发文保存
     */
    public final String API_SENDSAVE = URL_1 + "saveDocSendTransactByPost";
    /**
     * 收文保存
     */
    public final String API_RECEIVESAVE = URL_1 + "saveDocReceiveTransactByPost";
    /**
     * 附件下载
     */
    String API_DOWNLOAD = URL_1 + "downloadFileByPost";
    /**
     * 公文检索选项
     */
    public final String API_QUERY = URL_1 + "toQuery";
    /**
     * 公文检索查询
     */
    public final String API_GETDOCLIST = URL_1 + "getDocList";
    /**
     * 公文检索发文详情
     */
    public final String API_GETDOCSENDDETAILBYPOST = URL_1 + "getDocSendDetailByPost";
    /**
     * 公文检索发文详情
     */
    public final String API_GETDOCRECEIVEDETAILBYPOST = URL_1 + "getDocReceiveDetailByPost";


    String API_ADDRESS = URL_3 + "getAddListPersonnal";
    String API_ADDRESS_DEPT_DETAIL = URL_3 + "getAddListPersonnalDetail";
    String GET_BULLETIN_LIST =  URL_3 + "getBulletinList";           //获取公告列表
    String GET_BULLETIN_DETAIL =  URL_3 + "getBulletinDetail";        //公告详情获取

    String DOWNLOAD_ATTCHMENT = URL_ATTACHMENT + "downloadFileByPost";           //附件下载


    @FormUrlEncoded
    @POST(API_NUXDOCLIST)
    public Call<GwNx> apiGetNuxGw(@FieldMap Map<String, String> body);
    //附件下载
    @FormUrlEncoded
    @POST(API_DOWNLOAD)
    public Call<FileBean> apiDownload(@FieldMap Map<String, String> body);

    /**
     * 公文批阅
     * ——————————————————————————————————————————————————————————————————————————————
     */
    @FormUrlEncoded
    @POST(API_BACKLOGLIST)
    public Call<BacklogListBean> apiBacklogList(@FieldMap Map<String, String> body);
    @FormUrlEncoded
    @POST(API_HANDLESEND)
    public Call<SendDetailBean> apiHandleSend(@FieldMap Map<String, String> body);
    @FormUrlEncoded
    @POST(API_HANDLERECEIVE)
    public Call<ReceiveDetailBean> apiHandleReceive(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST(API_NEXTPEOLIST)
    public Call<NextPeoListBean> apiNextPeoList(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST(API_SENDSAVE)
    public Call<BaseRes> apiSendSave(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST(API_RECEIVESAVE)
    public Call<BaseRes> apiReceiveSave(@FieldMap Map<String, String> body);

    //————————————————————————————————————————————————————————————————————————————————

    /**
     * 公文检索
     * ——————————————————————————————————————————————————————————————————————————————
     */
    @FormUrlEncoded
    @POST(API_QUERY)
    public Call<SwTypeBean> apiQuerySw(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST(API_QUERY)
    public Call<FwTypeBean> apiQueryFw(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST(API_GETDOCLIST)
    public Call<GwjsBean> apiGetDocList(@FieldMap Map<String, String> body);


    @FormUrlEncoded
    @POST(API_GETDOCSENDDETAILBYPOST)
    public Call<SendDetailBean> apiGetDocSendDetail(@FieldMap Map<String, String> body);


    @FormUrlEncoded
    @POST(API_GETDOCRECEIVEDETAILBYPOST)
    public Call<ReceiveDetailBean> apiGetDocReceiveDetail(@FieldMap Map<String, String> body);


    //获取通讯录人员
    @FormUrlEncoded
    @POST(API_ADDRESS)
    public Call<Address> address(@FieldMap Map<String, String> body);

    //获取个人通讯录详情
    @FormUrlEncoded
    @POST(API_ADDRESS_DEPT_DETAIL)
    public Call<AddressDetail> deptOrganDetail(@FieldMap Map<String, String> body);


    /**
     * 通知通告
     * ——————————————————————————————————————————————————————————————————————————————
     */

    @FormUrlEncoded
    @POST(GET_BULLETIN_LIST)
    public Call<Ggcx> getBulletinList(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST(GET_BULLETIN_DETAIL)
    public Call<GgDetails> getBulletinDetail(@FieldMap Map<String, String> body);

    //附件下载
    @FormUrlEncoded
    @POST(DOWNLOAD_ATTCHMENT)
    public Call<FileBean> downloadAttachment(@FieldMap Map<String, String> body);
}
