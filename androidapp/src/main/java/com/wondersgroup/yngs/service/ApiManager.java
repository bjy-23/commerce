package com.wondersgroup.yngs.service;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import java.io.IOException;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by 薛定猫 on 2015/12/9.
 */
public class ApiManager {
    public static final String API_ROOT="http://gsxt.ynaic.gov.cn/netme/services/mobile/";//外网正式
//    public static final String API_ROOT="http://10.1.8.130:8007/netme/services/mobile/";//外网测试
//    private static final String API_ROOT="http://101.231.159.181/deliver/services/ws/me/services/mobile/";
//    public static final String API_ROOT2="http://10.10.16.94:8080/netme/";
    public static final String API_ROOT2="http://gsxt.ynaic.gov.cn/netme/";
//    public static final String API_ROOT2="http://10.1.41.87:80/netme/";
//    public static final String API_ROOT2="http://220.163.27.42:8022/netme/";
    private static boolean isReRoot=false;
//    public static final String API_RE_ROOT="http://gsxt.ynaic.gov.cn/me/services/mobile/";//内网正式
    public static final String API_RE_ROOT="http://172.28.129.42/me/services/mobile/";//内网正式
    public static final String API_RE_ROOT2 ="http://gsxt.ynaic.gov.cn/me/";
//    public static final String API_RE_ROOT2 ="http://10.1.41.87:80/deliver/services/ws/me/";
//    public static final String API_RE_ROOT2 ="http://220.163.27.42:89/me/";
    private volatile static ApiManager instance;
    public static YunNanApi yunNanApi;
    private static Retrofit retrofit;
    public static OkHttpClient httpClient;
    private static String token ="";
    private static final String wsCodeReq="01100001";

    public static boolean isReRoot() {
        return isReRoot;
    }

    public static ApiManager getInstance(){
        if(instance ==null){
            synchronized (ApiManager.class) {
                if (instance == null) {
                    instance = new ApiManager();
                }
            }
        }
        return instance;
    }

    public static String getApiRoot() {
        return API_ROOT;
    }

    public static void setToken(String token){
        getInstance().token =token;
    }
    public static String getToken(){
        return token;
    }

    public static String getWsCodeReq() {
        return wsCodeReq;
    }

    public static void clearToken(){
        getInstance().token ="";
    }

    public synchronized void init(String token,boolean isReRoot){
        getInstance().token =token;
        String baseUrl;
        getInstance().isReRoot=isReRoot;
        if(isReRoot){
            baseUrl=API_RE_ROOT;
        }else {
            baseUrl=API_ROOT;
        }
        httpClient = new OkHttpClient();
        httpClient.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();

                Request request = original.newBuilder()
                        .header("User-Agent", "YNWQ-Android")
                        .header("token", getInstance().token)
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        });
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.interceptors().add(interceptor);
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();
        yunNanApi=retrofit.create(YunNanApi.class);
    }
}
