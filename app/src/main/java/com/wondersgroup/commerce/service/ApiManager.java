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
    public static final String ENVIRONMENT_PRODUCT_FORMAL = "PRODUCT_FORMAL";//现场正式
    public static final String ENVIRONMENT_PRODUCT_TEST = "PRODUCT_TEST";//现场测试
    public static final String ENVIRONMENT_COMPANY = "COMPANY";//公司环境
    public static final String ENVIRONMENT = BuildConfig.ENVIRONMENT;

    private static final String BASE_URL_1 = "http://172.28.129.17/";//云南内网测试
    private static final String BASE_URL_2 = "http://172.28.129.42/";//云南内网正式
    private static final String BASE_URL_3 ="http://182.131.3.114/";//四川正式

    private static final String SHY_URL_1 = "http://172.28.129.29:8041/shy/services/mobile/";//云南市场监督管理局行政执法系统 测试
    private static final String SHY_URL_2 = "http://172.28.129.18:8031/shy/services/mobile/";//云南市场监督管理局行政执法系统 正式

    //Sc
    public static final String  VERSION_URL_3 = "http://10.1.192.40:8001/zfMobileService/";//公司
    public static final String VERSION_URL_4 = "http://182.131.3.110:8001/";//现场测试
    public static final String VERSION_URL_5 = "http://182.131.3.112:8001/";//现场正式

    private static final String API_CASE_1 = "http://182.131.3.99:8006/";
    private static final String API_CASE_2 = "http://10.1.192.40:8006/";
    private static final String API_OA_1 = "http://182.131.3.99:8013/";
    private static final String API_OA_2 = "http://10.1.192.40:8013/";
    private static final String API_HB_ROOT_1 = "http://182.131.3.99:8001/";
    private static final String API_HB_ROOT_2 = "http://10.1.192.40:8001/";
    private static final String API_TJ_1 = "http://182.131.3.99:8028/mds/";
    private static final String API_TJ_2 = "http://10.1.192.40:8028/mds/";
    public static final String API_AD_1 = "http://10.1.192.40:8008/ad/";//广告
    public static final String API_COMSUMER_1 = "http://10.1.192.40:8010/consumerw/";
    public static final String API_COMSUMER_2 = "http://10.2.103.208:8080/consumerw/";
    public static final String TRADEMARK_API_TEST = "http://10.1.192.40:8008/tm/";

    private String VERSION_URL ;
    private String API_CASE;//案件
    private String API_OA;
    private String API_HB_ROOT;
    private String API_YN_ROOT;
    private String CCJC_ROOT;
    private String API_CONSUMERW;
    private String API_SHY;
    public static String API_RE_ROOT;
    public String API_FGDJ_ROOT;
    private String API_TJ;
    private String API_AD;

    public static String RESULT_SUCCESS = "200";    //code="200"表示请求执行成功
    private volatile static ApiManager instance;
    private Retrofit.Builder retrofitBuilder;
    public static CommonApi commonApi;
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
    private static Retrofit retrofit;
    public OkHttpClient okHttpClient;
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

    public synchronized void init() {
        gson = new GsonBuilder().registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory<>()).create();
        RootAppcation appcation = RootAppcation.getInstance();

        okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(60, TimeUnit.SECONDS);
        okHttpClient.setWriteTimeout(60, TimeUnit.SECONDS);
        okHttpClient.setReadTimeout(60, TimeUnit.SECONDS);
        okHttpClient.interceptors().add(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));

        retrofitBuilder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient);

        switch (appcation.getVersion()) {
            case Constants.AREA_YN:
                switch (ENVIRONMENT){
                    case ENVIRONMENT_PRODUCT_FORMAL:
                        API_CASE = BASE_URL_2;
                        API_SHY = SHY_URL_2;
                        API_HB_ROOT = BASE_URL_2;
                        API_YN_ROOT = BASE_URL_2;
                        CCJC_ROOT = BASE_URL_2;
                        API_RE_ROOT = BASE_URL_2;
                        API_FGDJ_ROOT = BASE_URL_2;
                        VERSION_URL = BASE_URL_2;
                        API_TJ = BASE_URL_2 + "mds2/";
                        break;
                    case ENVIRONMENT_PRODUCT_TEST:
                        API_CASE = BASE_URL_1;
                        API_SHY = SHY_URL_1;
                        API_HB_ROOT = BASE_URL_1;
                        API_YN_ROOT = BASE_URL_1;
                        CCJC_ROOT = BASE_URL_1;
                        API_RE_ROOT = BASE_URL_1;
                        API_FGDJ_ROOT = BASE_URL_1;
                        VERSION_URL = BASE_URL_1;
                        API_TJ = BASE_URL_1 + "mds/";
                        break;
                }
                commonInit();
                caseInit();
                ynInit();
                hbInit();
                wqInit();
                fgdjInit();
                ccInit();
                tjInit();
                shyInit();
                break;
            case Constants.AREA_SC:
                switch (ENVIRONMENT){
                    case ENVIRONMENT_PRODUCT_FORMAL:
                        API_CASE = BASE_URL_3;
                        API_HB_ROOT = BASE_URL_3;
                        API_OA = BASE_URL_3;
                        API_TJ = BASE_URL_3 + "mds/";
                        API_CONSUMERW = API_COMSUMER_1;
                        API_AD = API_AD_1;
                        VERSION_URL = VERSION_URL_5;
                        break;
                    case ENVIRONMENT_PRODUCT_TEST:
                        API_CASE = API_CASE_1;
                        API_HB_ROOT = API_HB_ROOT_1;
                        API_OA = API_OA_1;
                        API_TJ = API_TJ_1;
                        API_CONSUMERW = API_COMSUMER_1;
                        API_AD = API_AD_1;
                        VERSION_URL = VERSION_URL_4;
                        break;
                    case ENVIRONMENT_COMPANY:
                        API_CASE = API_CASE_2;
                        API_HB_ROOT = API_HB_ROOT_2;
                        API_OA = API_OA_2;
                        API_TJ = API_TJ_2;
                        API_CONSUMERW = API_COMSUMER_1;
                        API_AD = API_AD_1;
                        VERSION_URL = VERSION_URL_3;
                        break;
                }
                commonInit();
                caseInit();
                oaInit();
                hbInit();
                tjInit();
                adInit();
                consumerwInit();
                tradeMarkInit();
                break;
        }
    }

    public void tradeMarkInit() {//调试服务器初始化
        if (tradeMarkApi == null) {
            OkHttpClient okHttpClient = new OkHttpClient();
            okHttpClient.interceptors().add(new Interceptor() {
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
            okHttpClient.interceptors().add(interceptor);
            retrofit = retrofitBuilder
                    .baseUrl(TRADEMARK_API_TEST)
                    .client(okHttpClient)
                    .build();
            tradeMarkApi = retrofit.create(TradeMarkApi.class);
        }
    }

    public void commonInit(){
        if (commonApi == null){
            synchronized (ApiManager.class){
                if (commonApi == null){
                    OkHttpClient okHttpClient = new OkHttpClient();
                    okHttpClient.setConnectTimeout(10, TimeUnit.SECONDS);
                    okHttpClient.interceptors().add(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
                    retrofitBuilder.client(okHttpClient);
                    Retrofit retrofit = retrofitBuilder
                            .baseUrl(VERSION_URL)
                            .build();
                    commonApi = retrofit.create(CommonApi.class);
                }
            }
        }
    }

    public void caseInit() {
        if (caseApi == null) {
            synchronized (ApiManager.class) {
                if (caseApi == null) {
                    OkHttpClient okHttpClient = new OkHttpClient();
                    okHttpClient.interceptors().add(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
                    okHttpClient.interceptors().add(new Interceptor() {
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
                    Retrofit retrofit = retrofitBuilder
                            .baseUrl(API_CASE)
                            .client(okHttpClient)
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
                    Retrofit retrofit = retrofitBuilder
                            .baseUrl(API_OA)
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
                    Retrofit retrofit = retrofitBuilder
                            .baseUrl(CCJC_ROOT)
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
                    Retrofit retrofit = retrofitBuilder
                            .baseUrl(API_TJ)
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
                    Retrofit retrofit = retrofitBuilder
                            .baseUrl(API_SHY)
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
                    Retrofit retrofit =retrofitBuilder
                            .baseUrl(API_AD)
                            .build();
                    adApi = retrofit.create(AdApi.class);
                }
            }
        }
    }

    public synchronized void consumerwInit(){
        if (consumerwApi == null){
            synchronized (ConsumerwApi.class){
                Retrofit retrofit = retrofitBuilder
                        .baseUrl(API_CONSUMERW)
                        .build();
                consumerwApi = retrofit.create(ConsumerwApi.class);
            }
        }
    }

    public void ynInit() {
        if (ynApi == null) {
            OkHttpClient okHttpClient = new OkHttpClient();
            okHttpClient.interceptors().add(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
            okHttpClient.interceptors().add(new Interceptor() {
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
            Retrofit retrofit = retrofitBuilder
                    .baseUrl(API_YN_ROOT)
                    .client(okHttpClient)
                    .build();

            ynApi = retrofit.create(YnApi.class);
        }
    }

    private void hbInit() {
        getInstance().token = token;
        if (hbApi == null) {
            OkHttpClient okHttpClient = new OkHttpClient();
            okHttpClient.interceptors().add(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
            okHttpClient.interceptors().add(new Interceptor() {
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

            Retrofit retrofit = retrofitBuilder
                    .baseUrl(API_HB_ROOT)
                    .client(okHttpClient)
                    .build();
            hbApi = retrofit.create(HbApi.class);
        }

        // }
    }

    private void wqInit() {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.interceptors().add(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        okHttpClient.interceptors().add(new Interceptor() {
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

        retrofit = retrofitBuilder
                .baseUrl(API_RE_ROOT)
                .client(okHttpClient)
                .build();
        ynWqApi = retrofit.create(YnWqApi.class);
    }

    private void fgdjInit() {
        retrofit = retrofitBuilder
                .baseUrl(API_FGDJ_ROOT)
                .build();
        fgdjApi = retrofit.create(FGDJApi.class);
    }

}
