package com.wondersgroup.commerce.service;

import com.squareup.okhttp.ResponseBody;
import com.wondersgroup.commerce.model.UploadResBean;
import com.wondersgroup.commerce.model.yn.CaseBean;
import com.wondersgroup.commerce.model.yn.CaseInfo;
import com.wondersgroup.commerce.model.yn.DealInfoBean;
import com.wondersgroup.commerce.model.yn.DicBean;
import com.wondersgroup.commerce.model.yn.RequestLoginBean;
import com.wondersgroup.commerce.model.yn.ToDoBean;
import com.wondersgroup.commerce.model.yn.YnLoginBean;
import com.wondersgroup.commerce.teamwork.statistics.bean.BaLiResult;
import com.wondersgroup.commerce.teamwork.statistics.bean.BanJie;

import java.util.List;
import java.util.Map;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.FieldMap;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by kangrenhui on 2016/3/14.
 */
public interface YnApi {
    String API_LOGIN = "gsLogin";
    String API_TODO = "todo/{infoType}/{userId}/{organId}/{deptId}/{pageNo}/{pageSize}";
    String API_GETCASEINFO = "getCaseInfo/{caseId}/{actId}";
    String API_SEARCHCASE = "search/{userId}/{infoType}/{basicName}/{accuseName}/{caseId}/{pageNo}/{pageSize}";
    String API_SAVEDEALINFO = "saveDealInfo";
    String API_ALLDIC = "getDic/allDic";

    @POST(API_LOGIN)
    public Call<YnLoginBean> login(@Body RequestLoginBean body);

    @GET(API_TODO)
    Call<ToDoBean> getTodo(@Path("infoType") String infoType, @Path("userId") String userId, @Path("organId") String organId, @Path("deptId") String deptId, @Path("pageNo") String pageNo, @Path("pageSize") String pageSize);

    @GET(API_GETCASEINFO)
    Call<CaseBean> getCaseInfo(@Path("caseId") String caseId, @Path("actId") String actId);

    @GET(API_SEARCHCASE)
    Call<Result<List<CaseInfo>>> searchCase(@Path(value = "userId", encoded = false) String userId, @Path(value = "infoType", encoded = false) String infoType, @Path(value = "basicName", encoded = false) String basicName, @Path(value = "accuseName", encoded = false) String accuseName, @Path(value = "caseId", encoded = false) String caseId, @Path("pageNo") String pageNo, @Path(value = "pageSize", encoded = false) String pageSize);

    @POST(API_SAVEDEALINFO)
    Call<UploadResBean> saveDealInfo(@Body DealInfoBean body);

    @GET(API_ALLDIC)
    Call<DicBean> getAllDic();


}
