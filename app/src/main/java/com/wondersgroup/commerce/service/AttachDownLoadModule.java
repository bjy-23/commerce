package com.wondersgroup.commerce.service;

import com.wondersgroup.commerce.model.FileBean;
import com.wondersgroup.commerce.model.GgDetails;
import com.wondersgroup.commerce.model.Ggcx;

import java.util.Map;

import retrofit.Call;
import retrofit.http.FieldMap;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by Lee on 2016/3/14
 */
public interface AttachDownLoadModule {
    String DOWNLOAD_ATTCHMENT = "downloadFileByPost";           //附件下载


    @FormUrlEncoded
    @POST(DOWNLOAD_ATTCHMENT)
    public Call<FileBean> downloadAttachment(@FieldMap Map<String, String> body);

}
