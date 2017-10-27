package com.wondersgroup.commerce.service;

import android.app.AlertDialog;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.Connection;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;
import com.wondersgroup.commerce.BuildConfig;
import com.wondersgroup.commerce.adapter.NullStringToEmptyAdapterFactory;
import com.wondersgroup.commerce.application.RootAppcation;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.okhttp.LoggingInterceptor;

import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by kangrenhui on 2016/1/27.
 */
public class ApiManager {
    /*注意TJAPI修改；
    注意修改公示信息js配置;
    注意版本更新;
    应用名称；*/
    private static final String BASE_URL_1 = "http://172.28.129.17/";//云南内网测试
    private static final String BASE_URL_2 = "http://172.28.129.42/";//云南内网正式
    private static final String BASE_URL_3 ="http://182.131.3.114/";//四川正式

    private static final String SHY_URL_1 = "http://172.28.129.29:8041/shy/services/mobile/";//云南市场监督管理局行政执法系统 测试
    private static final String SHY_URL_2 = "http://172.28.129.18:8031/shy/services/mobile/";//云南市场监督管理局行政执法系统 正式

    //版本更新
    public static final String VERSION_URL_1 = "http://172.28.129.17/zfMobileService/";//测试
    public static final String VERSION_URL_2 = "http://172.28.129.42/zfMobileService/";//正式
    public static String VERSION_URL ;
    private String API_CASE;//案件
    private String API_OA;
    private String API_HB_ROOT;
    private String API_YN_ROOT;
    private String CCJC_ROOT;
    private String API_CONSUMERW;
    private String API_SHY;
    public static String API_RE_ROOT;
    public String API_FGDJ_ROOT;
    public String API_LAW_ROOT;
    private String API_TJ;
    private String API_AD;

    private static final String API_CASE_1 = "http://182.131.3.99:8006/";
    private static final String API_CASE_2 = "http://10.1.192.40:8006/";
    private static final String API_CASE_3 = "http://182.131.3.99:8006/";
    private static final String API_OA_1 = "http://182.131.3.99:8013/oa/";
    private static final String API_OA_2 = "http://10.1.192.40:8013/oa/";
    private static final String API_HB_ROOT_1 = "http://182.131.3.99:8001/";
    private static final String API_HB_ROOT_2 = "http://10.1.192.40:8001/";
    private static final String API_HB_ROOT_4 = "http://10.2.14.102:8080/";
    private static final String API_TJ_1 = "http://182.131.3.99:8028/mds/";
    private static final String API_TJ_2 = "http://10.1.192.40:8028/mds/";
    private static final String API_YN_ROOT_1 = "http://10.1.8.130:8010/";
    private static final String API_YN_ROOT_2 = "http://10.2.18.108:8080/";
    private static final String CCJC_ROOT_1 = "http://10.1.8.133:8012/";
    private static final String CCJC_ROOT_2 = "http://10.2.14.158:8080/";
    public static final String API_RE_ROOT_1 = "http://10.1.8.130:8009/";
    public static final String API_RE_ROOT_2 = "http://10.2.103.41:8080/";
    public static final String API_FGDJ_ROOT_1 = "http://220.163.27.42:8024/";
    public static final String API_LAW_ROOT_1 = "http://10.1.8.130:8006/";
    public static final String API_AD_1 = "http://10.1.192.40:8008/ad/";//广告
    public static final String API_COMSUMER_1 = "http://10.1.192.40:8010/consumerw/";
    public static final String API_COMSUMER_2 = "http://10.2.103.208:8080/consumerw/";

    private static final String API_TEST = "http://10.1.192.40:8006/case/";
    private static final String TRADEMARK_API_TEST = "http://10.1.192.40:8008/tm/";
    private static final String API_SH_ROOT = "";

    public static String RESULT_SUCCESS = "200";    //code="200"表示请求执行成功
    private volatile static ApiManager instance;
    public static ShyApi shyApi;
    public static TjApi tjApi;
    public static AdApi adApi;
    public static CaseApi caseApi;
    public static OaApi oaApi;
    public static HbApi hbApi;
    public static ShApi shApi;
    public static YnApi ynApi;
    public static HNApi hnApi;
    public static YnWqApi ynWqApi;
    public static CCJCApi ccjcApi;
    public static ConsumerwApi consumerwApi;
    public static TradeMarkApi tradeMarkApi;
    public static FGDJApi fgdjApi;
    public static LawApi lawApi;
    private static Retrofit retrofit;
    public static OkHttpClient httpClient;
    private static String token = "";
    public static int caseType = 1;//1:工商行政执法系统;2:市场监督管理局行政执法系统
    private Gson gson;

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

    public synchronized void init() {

        gson = new GsonBuilder().registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory<>()).create();
        RootAppcation appcation = RootAppcation.getInstance();
        switch (appcation.getVersion()) {
            case Constants.AREA_YN:
                if (Constants.VERSION.equals(Constants.VERSION_R)){
                    API_CASE = BASE_URL_2;
                    API_SHY = SHY_URL_2;
                    API_HB_ROOT = BASE_URL_2;
                    API_YN_ROOT = BASE_URL_2 + "consumer/services/ws/app/";
                    CCJC_ROOT = BASE_URL_2 + "noticemana/services/check/";
                    API_RE_ROOT = BASE_URL_2 + "me/";
                    API_FGDJ_ROOT = BASE_URL_2 + "fgdj/app/";
                    API_LAW_ROOT = BASE_URL_2 + "case/services/mobile/";
                    VERSION_URL = VERSION_URL_2;
                    API_TJ = BASE_URL_2 + "mds2/";
                }else {
                    API_CASE = BASE_URL_1;
                    API_SHY = SHY_URL_1;
                    API_HB_ROOT = BASE_URL_1;
                    API_YN_ROOT = BASE_URL_1 + "consumer/services/ws/app/";
                    CCJC_ROOT = BASE_URL_1 + "noticemana/services/check/";
                    API_RE_ROOT = BASE_URL_1 + "me/";
                    API_FGDJ_ROOT = BASE_URL_1 + "fgdj/app/";
                    API_LAW_ROOT = BASE_URL_1 + "case/services/mobile/";
                    VERSION_URL = VERSION_URL_1;
                    API_TJ = BASE_URL_1 + "mds/";
                }

                caseInit();
                ynInit();
                hbInit();
                wqInit();
                fgdjInit();
                lawInit();
                ccInit();
                tjInit();
                shyInit();
                break;
            case Constants.AREA_SC:
                if (Constants.VERSION.equals(Constants.VERSION_R)){
                    API_CASE = BASE_URL_3;
                    API_HB_ROOT = BASE_URL_3;
                    API_OA = BASE_URL_3 + "oa/";
                    API_TJ = BASE_URL_3 + "mds/";
                    API_CONSUMERW = API_COMSUMER_1;
                    API_AD = API_AD_1;
                }else {
                    API_CASE = API_CASE_2;
                    API_HB_ROOT = API_HB_ROOT_2;
                    API_OA = API_OA_2;
                    API_TJ = API_TJ_2;
                    API_CONSUMERW = API_COMSUMER_1;
                    API_AD = API_AD_1;
                }
                caseInit();
                oaInit();
                hbInit();
                tjInit();
                adInit();
                consumerwInit();
                break;
        }
    }

    public void caseInit() {
        if (caseApi == null) {
            synchronized (ApiManager.class) {
                if (caseApi == null) {
                    httpClient = new OkHttpClient();
                    httpClient.interceptors().add(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request original = chain.request();

                            Request request = original.newBuilder()
                                    .header("User-Agent", "hb-Android")
                                    .header("token", getInstance().token)
                                    .method(original.method(), original.body())
                                    .build();

                            return chain.proceed(request);
                        }
                    });
//                    Connection
//                    Proxy
                    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                    httpClient.interceptors().add(interceptor);
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(API_CASE)
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .client(httpClient)
                            .build();
                    caseApi = retrofit.create(CaseApi.class);
                }
            }
        }
    }

    public synchronized void oaInit() {
        if (oaApi == null) {
            synchronized (OaApi.class) {
                if (oaApi == null) {
                    OkHttpClient httpClient = new OkHttpClient();
                    httpClient.interceptors().add(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(API_OA)
                            .addConverterFactory(GsonConverterFactory.create())
                            .client(httpClient)
                            .build();
                    oaApi = retrofit.create(OaApi.class);
                }
            }
        }
    }

    public synchronized void ccInit() {
        if (ccjcApi == null) {
            synchronized (CCJCApi.class) {
                if (ccjcApi == null) {
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(CCJC_ROOT)
                            .addConverterFactory(GsonConverterFactory.create())
                            .client(httpClient)
                            .build();
                    ccjcApi = retrofit.create(CCJCApi.class);
                }
            }
        }
    }

    public synchronized void tjInit() {
        if (tjApi == null) {
            synchronized (ApiManager.class) {
                if (tjApi == null) {
                    httpClient.setConnectTimeout(60, TimeUnit.SECONDS);
                    httpClient.setWriteTimeout(60, TimeUnit.SECONDS);
                    httpClient.setReadTimeout(60, TimeUnit.SECONDS);
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(API_TJ)
                            .addConverterFactory(GsonConverterFactory.create())
                            .client(httpClient)
                            .build();
                    tjApi = retrofit.create(TjApi.class);
                }
            }
        }
    }

    private void shyInit(){
        if (shyApi == null) {
            synchronized (ApiManager.class) {
                if (shyApi == null) {
                    httpClient.setConnectTimeout(60, TimeUnit.SECONDS);
                    httpClient.setWriteTimeout(60, TimeUnit.SECONDS);
                    httpClient.setReadTimeout(60, TimeUnit.SECONDS);
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(API_SHY)
                            .addConverterFactory(GsonConverterFactory.create())
                            .client(httpClient)
                            .build();
                    shyApi = retrofit.create(ShyApi.class);
                }
            }
        }
    }

    /**
     * 广告
     */
    public synchronized void adInit() {
        if (adApi == null) {
            synchronized (ApiManager.class) {
                if (adApi == null) {
                    httpClient.setConnectTimeout(60, TimeUnit.SECONDS);
                    httpClient.setWriteTimeout(60, TimeUnit.SECONDS);
                    httpClient.setReadTimeout(60, TimeUnit.SECONDS);
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(API_AD)
                            .addConverterFactory(GsonConverterFactory.create())
                            .client(httpClient)
                            .build();
                    adApi = retrofit.create(AdApi.class);
                }
            }
        }
    }

    public synchronized void consumerwInit(){
        if (consumerwApi == null){
            synchronized (ConsumerwApi.class){
                httpClient.setConnectTimeout(60, TimeUnit.SECONDS);
                httpClient.setWriteTimeout(60, TimeUnit.SECONDS);
                httpClient.setReadTimeout(60, TimeUnit.SECONDS);
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(API_CONSUMERW)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(httpClient)
                        .build();
                consumerwApi = retrofit.create(ConsumerwApi.class);
            }
        }
    }

    public void ynInit() {
        if (ynApi == null) {

            OkHttpClient httpClient = new OkHttpClient();
            httpClient.interceptors().add(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();

                    Request request = original.newBuilder()
                            .header("User-Agent", "yn-Android")
                            .header("Content-Type", "application/json;charset=UTF-8")
                            .header("Accept", "application/json")
                            .method(original.method(), original.body())
                            .build();

                    return chain.proceed(request);
                }
            });
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.interceptors().add(interceptor);
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(API_YN_ROOT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient)
                    .build();

            ynApi = retrofit.create(YnApi.class);
        }
    }

    private void hbInit() {
        getInstance().token = token;
        if (hbApi == null) {
            //if (retrofit == null) {
            httpClient = new OkHttpClient();
            httpClient.interceptors().add(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();

                    Request request = original.newBuilder()
                            .header("User-Agent", "hb-Android")
                            .header("token", getInstance().token)
                            .method(original.method(), original.body())
                            .build();

                    return chain.proceed(request);
                }
            });
            httpClient.interceptors().add(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
//            LoggingInterceptor loggingInterceptor = new LoggingInterceptor();
//            httpClient.interceptors().add(loggingInterceptor);
            httpClient.setConnectTimeout(60, TimeUnit.SECONDS);
            httpClient.setWriteTimeout(60, TimeUnit.SECONDS);
            httpClient.setReadTimeout(60, TimeUnit.SECONDS);
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(API_HB_ROOT)
//                        .addConverterFactory(StringConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient)
                    .build();
            hbApi = retrofit.create(HbApi.class);
        }

        // }
    }

    private void shInit() {
        getInstance().token = token;
        if (shApi == null) {
            if (retrofit == null) {
                httpClient = new OkHttpClient();

                CookieManager cookieManager = new CookieManager();
                CookieHandler.setDefault(cookieManager);
                cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
                httpClient.setCookieHandler(cookieManager);

                httpClient.interceptors().add(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();

                        Request request = original.newBuilder()
                                .header("User-Agent", "sh-Android")
                                .header("token", getInstance().token)
                                .method(original.method(), original.body())
                                .build();

                        return chain.proceed(request);
                    }
                });
                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                httpClient.interceptors().add(interceptor);
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(API_SH_ROOT)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addConverterFactory(new ResponseConvertStringFactory())
                        .client(httpClient)
                        .build();
                shApi = retrofit.create(ShApi.class);
            }

        }
    }

    private void wqInit() {
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
                .baseUrl(API_RE_ROOT)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();
        ynWqApi = retrofit.create(YnWqApi.class);
    }

    private void fgdjInit() {
        httpClient = new OkHttpClient();
        httpClient.setConnectTimeout(30, TimeUnit.SECONDS);
        httpClient.setReadTimeout(30, TimeUnit.SECONDS);
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

    private void lawInit() {
        httpClient = new OkHttpClient();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.interceptors().add(interceptor);
        retrofit = new Retrofit.Builder()
                .baseUrl(API_LAW_ROOT)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();
        lawApi = retrofit.create(LawApi.class);
    }

    private void testServiceInit() {//调试服务器初始化
        getInstance().token = token;
//        if (caseModule == null) {
        if (tradeMarkApi == null) {
            httpClient = new OkHttpClient();
            httpClient.interceptors().add(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();

                    Request request = original.newBuilder()
                            .header("User-Agent", "hb-Android")
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
                    //.baseUrl(API_TEST)
                    .baseUrl(TRADEMARK_API_TEST)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient)
                    .build();
//        caseModule = retrofit.create(CaseModule.class);
            tradeMarkApi = retrofit.create(TradeMarkApi.class);
        }
    }

    //单独某个接口测试，临时改变仅仅此一个接口的服务器IP地址，其他接口的服务器ip不变，情况下使用
    public synchronized void unitTestInit() {
        testServiceInit();
    }


}
