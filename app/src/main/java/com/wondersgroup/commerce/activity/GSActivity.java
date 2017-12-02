package com.wondersgroup.commerce.activity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.wondersgroup.commerce.BuildConfig;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.service.ApiManager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GSActivity extends AppCompatActivity {
    public final static String GS_URL_SC_1="http://182.131.3.110:8012/notice";
    public final static String GS_URL_SC_2="http://sc.gsxt.gov.cn/notice";
    public final static String GS_URL_YN_1="http://220.163.27.42:8021/notice";
    public final static String GS_URL_YN_2="http://gsxt.ynaic.gov.cn/notice";

    @BindView(R.id.webview)
    WebView webView;

    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gs);
        ButterKnife.bind(this);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);

        try {
            if (Build.VERSION.SDK_INT >= 16) {
                Class<?> clazz = webView.getSettings().getClass();
                Method method = clazz.getMethod(
                        "setAllowUniversalAccessFromFileURLs", boolean.class);
                if (method != null) {
                    method.invoke(webView.getSettings(), true);
                }
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                Log.w("zzz_zzz", consoleMessage.message());
                return super.onConsoleMessage(consoleMessage);
            }

            @Override
            public void onConsoleMessage(String message, int lineNumber, String sourceID) {
                Log.w("zzz_zzz", message);
                super.onConsoleMessage(message, lineNumber, sourceID);
            }
        });
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        JsInteration jsInteration = new JsInteration();
        String url = "";
        if (Constants.AREA.equals(Constants.AREA_SC)){
            if (ApiManager.ENVIRONMENT.equals(ApiManager.ENVIRONMENT_PRODUCT_FORMAL))
                url = GS_URL_SC_2;
            else
                url = GS_URL_SC_1;
        }else if (Constants.AREA.equals(Constants.AREA_YN)){
            if (ApiManager.ENVIRONMENT.equals(ApiManager.ENVIRONMENT_PRODUCT_FORMAL))
                url = GS_URL_YN_2;
            else
                url = GS_URL_YN_1;
        }
        jsInteration.url = url;
        webView.addJavascriptInterface(jsInteration, "interaction");
        //webView.loadUrl("http://10.10.16.163:8080/public/publicityInquiry/app/index.html#/entInfoQuery?searchType=1&backUrl=home");
        webView.loadUrl("file:///android_asset/publicityInquiry/app/index.html#/entInfoQuery?searchType=1&backUrl=home");
    }

    public class JsInteration {
        public String url;

        @JavascriptInterface
        public void goBackHunan() {
            //Toast.makeText(GSActivity.this, "aaaaaa", Toast.LENGTH_SHORT).show();
            finish();
        }

        @JavascriptInterface
        public void onSumResult(int result) {
            Log.i("aaa", "onSumResult result=" + result);
        }

        @JavascriptInterface
        public String setBaseUrl(){
            return url;
        }
    }
}
