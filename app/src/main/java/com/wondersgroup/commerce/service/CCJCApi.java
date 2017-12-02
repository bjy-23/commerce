package com.wondersgroup.commerce.service;

import com.wondersgroup.commerce.model.ccjc.CCCXResult;
import com.wondersgroup.commerce.model.ccjc.CCCheckResult;
import com.wondersgroup.commerce.model.ccjc.CCHCResult;
import com.wondersgroup.commerce.model.ccjc.CCOpnnResult;
import com.wondersgroup.commerce.model.ccjc.CCToDoResult;
import com.wondersgroup.commerce.model.ccjc.CCXQResult;
import com.wondersgroup.commerce.model.ccjc.DicResult;
import com.wondersgroup.commerce.model.ccjc.LoginResult;

import java.util.Map;

import retrofit.Call;
import retrofit.http.FieldMap;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by 薛定猫 on 16-5-10.
 */
public interface CCJCApi {
    String PREFIX = "noticemana/services/check/";
    String LOGIN= PREFIX + "loginInfo";                   //登陆
    String DIC= PREFIX + "getDicLoad";                    //字典加载
    String DBLB= PREFIX + "getToDoList";                  //代办列表
    String CCJCCX= PREFIX + "getCheckAppList";            //抽查检查查询
    String CCJGCX= PREFIX + "getCheckRecord";             //抽查结果详情
    String CCBLYJ= PREFIX + "getNoticeOpnnList";          //查询办理意见
    String DBCX= PREFIX + "getToDo";                      //查询待办--已废弃
    String CCXQ= PREFIX + "getCheckEntityInfo";           //抽查详情
    String SDHC= PREFIX + "submitLocalRecord";            //提交实地核查信息

    @FormUrlEncoded
    @POST(LOGIN)
    Call<LoginResult> ccLogin(@FieldMap Map<String,String> body);

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
}
