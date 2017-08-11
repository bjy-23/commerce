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

import com.wondersgroup.commerce.R;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GSActivity extends AppCompatActivity {

    @Bind(R.id.webview)
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

        webView.addJavascriptInterface(new JsInteration(), "interaction");
        //webView.loadUrl("http://10.10.16.163:8080/public/publicityInquiry/app/index.html#/entInfoQuery?searchType=1&backUrl=home");
        webView.loadUrl("file:///android_asset/publicityInquiry/app/index.html#/entInfoQuery?searchType=1&backUrl=home");
    }

    public class JsInteration {
        @JavascriptInterface
        public void goBackHunan() {
            //Toast.makeText(GSActivity.this, "aaaaaa", Toast.LENGTH_SHORT).show();
            finish();
        }

        @JavascriptInterface
        public void onSumResult(int result) {
            Log.i("aaa", "onSumResult result=" + result);
        }
    }
}
