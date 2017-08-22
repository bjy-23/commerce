package com.example.fgdj;

import android.content.Context;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;
import java.util.concurrent.TimeUnit;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by kangrenhui on 2016/1/27.
 */
public class ApiManager {
   private static final String BASE_URL_1 = "http://172.28.129.17/";//云南内网测试
    private static final String BASE_URL_2 = "http://172.28.129.42/";//云南内网正式
    private static final String BASE_URL = BASE_URL_2;//注意TJAPI修改；注意修改公示信息js配置;注意版本更新;应用名称；
//    public String API_TJ  = BASE_URL + "mds/";//测试
    public String API_TJ = BASE_URL + "mds2/";//正式

    //版本更新
    public static String VERSION_URL_1 = "http://172.28.129.17/zfMobileService/";//测试
    public static String VERSION_URL_2 = "http://172.28.129.42/zfMobileService/";//正式
    public static String VERSION_URL = VERSION_URL_2;
    private String API_CASE;//案件
    private String API_OA;
    private String API_HB_ROOT;
    private String API_YN_ROOT;
    private String CCJC_ROOT;
    public static String API_RE_ROOT;
    public String API_FGDJ_ROOT;
    public String API_LAW_ROOT;

    private static final String API_CASE_1 = "http://10.1.8.130:8006/";//案件;130,云南、205,四川
    private static final String API_CASE_2 = "http://10.1.192.40:8006/";
    private static final String API_CASE_3 = "http://10.2.18.107:8080/";
    private static final String API_OA_1 = "http://10.1.192.40:8013/oa/";
    private static final String API_OA_2 = "http://10.1.8.205:8013/oa/";
    private static final String API_HB_ROOT_1 = "http://10.1.8.130:8001/";//工商一体化
    private static final String API_HB_ROOT_2 = "http://10.1.8.205:8001/";
    private static final String API_HB_ROOT_3 = "http://10.1.192.40:8001/";
    private static final String API_HB_ROOT_4 = "http://10.2.14.102:8080/";
    private static final String API_YN_ROOT_1 = "http://10.1.8.130:8010/";
    private static final String API_YN_ROOT_2 = "http://10.2.18.108:8080/";
    private static final String CCJC_ROOT_1="http://10.1.8.133:8012/";
    private static final String CCJC_ROOT_2="http://10.2.14.158:8080/";
    public static final String API_RE_ROOT_1="http://10.1.8.130:8009/";
    public static final String API_RE_ROOT_2="http://10.2.103.41:8080/";
    public static final String API_FGDJ_ROOT_1 = "http://220.163.27.42:8024/";
    public static final String API_LAW_ROOT_1 = "http://10.1.8.130:8006/";

    public static final String API_ROOT = "http://gsxt.ynaic.gov.cn/netme/services/mobile/";//外网正式
    //    public static final String API_ROOT="http://10.1.8.130:8007/netme/services/mobile/";//外网测试
//    public static final String API_ROOT2="http://gsxt.ynaic.gov.cn/netme/";
    public static final String API_ROOT2 = "http://10.1.8.130:8009/me/";
    //    public static final String API_ROOT2="http://10.10.16.204:8080/me/";
    private static final String API_HN_ROOT = "http://gsxt.hnaic.gov.cn:8019/deliver/services/ws/";     //----现场根地址
    //private static final String API_HN_ROOT = "http://10.1.8.133:8023/deliver/services/ws/";        //----公司根地址
    //private static final String API_HN_ROOT = "http://172.25.130.61:8023/deliver/services/ws/";
    private static final String API_TEST = "http://10.1.192.40:8006/case/";
    private static final String TRADEMARK_API_TEST = "http://10.2.14.199:8080/tm/";
    private static final String API_SH_ROOT = "";

    public static String RESULT_SUCCESS = "200";    //code="200"表示请求执行成功
    private volatile static ApiManager instance;

    public static FGDJApi fgdjApi;
    private static Retrofit retrofit;
    public static OkHttpClient httpClient;
    private static String token = "";

    public static ApiManager getInstance() {
        if (instance == null) {
            synchronized (ApiManager.class) {
                if (instance == null) {
                    instance = new ApiManager();
                }
            }
        }
        return instance;
    }

    public static void setToken(String token) {
        getInstance().token = token;
    }

    public static String getToken() {
        return token;
    }

    public static void clearToken() {
        getInstance().token = "";
    }

    public synchronized void init(String token, Context context) {


                API_FGDJ_ROOT = BASE_URL + "fgdj/app/";

                fgdjInit();
    }


    private void fgdjInit(){
        httpClient = new OkHttpClient();
        httpClient.setConnectTimeout(30, TimeUnit.SECONDS);
        httpClient.setReadTimeout(30,TimeUnit.SECONDS);
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.interceptors().add(interceptor);
        retrofit = new Retrofit.Builder()
                .baseUrl(API_FGDJ_ROOT)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();
        fgdjApi = retrofit.create(FGDJApi.class);
    }

}
