package com.wondersgroup.commerce.teamwork.wywork.jyqx.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.teamwork.wywork.jyqx.QYXXActivity;
import com.wondersgroup.commerce.widget.MyProgressDialog;

/**
 * Created by wy on 2016/4/14.
 */
public class XygsFragment extends Fragment {

    private String url  =   "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_qyxx_xygs, null);
        init();
        final WebView wb = (WebView) view.findViewById(R.id.wb);
        wb.getSettings().setJavaScriptEnabled(true);
        wb.getSettings().setUseWideViewPort(true);
        wb.getSettings().setLoadWithOverviewMode(true);
        wb.loadUrl(url);
        MyProgressDialog.show(getActivity());
        wb.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }

            public void onReceivedSslError(WebView view,
                                           android.webkit.SslErrorHandler handler,
                                           android.net.http.SslError error) {
                handler.proceed();
            }

            public void onPageFinished(WebView view, String url) {
                MyProgressDialog.dismiss();
            }
        });
        Button back = (Button) view.findViewById(R.id.back_btn);
        Button home = (Button) view.findViewById(R.id.home_btn);

        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                wb.goBack();
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                wb.loadUrl(url);
            }
        });
        return view;
    }

    private void init() {
        url =   ((QYXXActivity)getActivity()).getUrl();
    }
}