package com.wondersgroup.commerce.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.constant.Constants;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TjActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.toolbar_txt)
    TextView title;
    @Bind(R.id.toolbar_btn)
    Button btn;
    @Bind(R.id.web)
    WebView webView;

    private String titleName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tj);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        titleName = intent.getStringExtra("title");
        title.setText(titleName);
        toolbar.setTitle("");
        btn.setVisibility(View.INVISIBLE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.app_back);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.setWebChromeClient(new WebChromeClient());
        /*// 设置可以支持缩放
        webView.getSettings().setSupportZoom(true);
        // 设置出现缩放工具
        webView.getSettings().setBuiltInZoomControls(true);
        // 扩大比例的缩放
        webView.getSettings().setUseWideViewPort(true);
        webView.setInitialScale(10);*/
        switch (titleName){
            case "近期新设内资企业户数情况":
                webView.loadUrl(Constants.TJ_URL_1);
                break;
            case "近期新设内资企业注册资本（金）情况":
                webView.loadUrl(Constants.TJ_URL_2);
                break;
            case "新设内资企业规模分布情况":
                webView.loadUrl(Constants.TJ_URL_3);
                break;
            case "外资主体新设情况":
                webView.loadUrl(Constants.TJ_URL_4);
                break;
            case "承包商新设情况":
                webView.loadUrl(Constants.TJ_URL_5);
                break;
            case "新设外资企业投资规模情况":
                webView.loadUrl(Constants.TJ_URL_6);
                break;
            case "消费维权类咨询情况":
                webView.loadUrl(Constants.TJ_URL_7);
                break;
            case "消费者关注的热点商品":
                webView.loadUrl(Constants.TJ_URL_8);
                break;
            case "消费者关注的热点服务":
                webView.loadUrl(Constants.TJ_URL_9);
                break;
            case "消费者投诉举报趋势":
                webView.loadUrl(Constants.TJ_URL_10);
                break;
            case "各机关本期结案案件数量+本期案件案值、处罚金额、入库金额":
                webView.loadUrl(Constants.TJ_URL_11);
                break;
            case "本机各类法规案件数量+本期案件案值、处罚金额、入库金额":
                webView.loadUrl(Constants.TJ_URL_12);
                break;
            case "历年案件数量+历年案件案值、处罚金额、入库金额":
                webView.loadUrl(Constants.TJ_URL_13);
                break;
            case "近五年所有市场主体年报上报趋势图":
                webView.loadUrl(Constants.TJ_YN_QYNB_LINK_1);
                break;
            case "近五年第一二三产业发展趋势":
                webView.loadUrl(Constants.TJ_YN_QYNB_LINK_2);
                break;
            case "历年已年报、应年报的比率趋势":
                webView.loadUrl(Constants.TJ_YN_QYNB_LINK_3);
                break;
            case "2016-2017年近6个月已年报同比情况":
                webView.loadUrl(Constants.TJ_YN_QYNB_LINK_4);
                break;
            case "排名十位行业的利润统计图":
                webView.loadUrl(Constants.TJ_YN_QYNB_LINK_5);
                break;
            case "排名十位行业的纳税统计图":
                webView.loadUrl(Constants.TJ_YN_QYNB_LINK_6);
                break;
            case "排名十位行业的行业营业收入统计图":
                webView.loadUrl(Constants.TJ_YN_QYNB_LINK_7);
                break;
            case "连续N年未年报的图表":
                webView.loadUrl(Constants.TJ_YN_QYNB_LINK_8);
                break;

            case "小微企业各注册资本范围数量统计图":
                webView.loadUrl(Constants.TJ_YN_XWXX_LINK_1);
                break;
            case "各机关小微企业数量统计图":
                webView.loadUrl(Constants.TJ_YN_XWXX_LINK_2);
                break;
            case "近6个月全省小微企业享受扶持金额趋势图":
                webView.loadUrl(Constants.TJ_YN_XWXX_LINK_3);
                break;
            case "排名前十的行业小微企业数量统计图":
                webView.loadUrl(Constants.TJ_YN_XWXX_LINK_4);
                break;
            case "各承办部门小微企业扶持数量统计图":
                webView.loadUrl(Constants.TJ_YN_XWXX_LINK_5);
                break;

            case "企业列入异常名录原因数量统计图":
                webView.loadUrl(Constants.TJ_YN_JYYC_LINK_1);
                break;
            case "企业移出异常名录原因数量统计图":
                webView.loadUrl(Constants.TJ_YN_JYYC_LINK_2);
                break;
            case "各机关企业列入异常名录数量统计图":
                webView.loadUrl(Constants.TJ_YN_JYYC_LINK_3);
                break;
            case "全省企业近6个月列入异常名录数量趋势图":
                webView.loadUrl(Constants.TJ_YN_JYYC_LINK_4);
                break;
            case "历年因未年报列入异常名录同比图":
                webView.loadUrl(Constants.TJ_YN_JYYC_LINK_5);
                break;
            case "列入异常名录次数的企业数量":
                webView.loadUrl(Constants.TJ_YN_JYYC_LINK_6);
                break;

            case "本期新设个体工商户户数":
                webView.loadUrl(Constants.TJ_YN_GTGSH_LINK_1);
                break;
            case "个体工商户数占各第三产业类别前五名":
                webView.loadUrl(Constants.TJ_YN_GTGSH_LINK_2);
                break;
            case "个体工商户存续周期统计图":
                webView.loadUrl(Constants.TJ_YN_GTGSH_LINK_3);
                break;
            case "近十年个体工商户期末统计图":
                webView.loadUrl(Constants.TJ_YN_GTGSH_LINK_4);
                break;
            case "旧照换新照数量及比例":
                webView.loadUrl(Constants.TJ_YN_GTGSH_LINK_5);
                break;
            case "2016-2017年个体同比折线图（按月份）":
                webView.loadUrl(Constants.TJ_YN_GTGSH_LINK_6);
                break;
            case "个体经营户则年龄段占比图":
                webView.loadUrl(Constants.TJ_YN_GTGSH_LINK_7);
                break;
            case "个体经营者各年龄段占前十行业数量":
                webView.loadUrl(Constants.TJ_YN_GTGSH_LINK_8);
                break;
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
