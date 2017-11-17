package com.wondersgroup.commerce.teamwork.dailycheck;

import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.activity.FragmentActivity;
import com.wondersgroup.commerce.application.RootAppcation;

public class WebViewActivity extends FragmentActivity {

	private RootAppcation application;

	public class JsObject {
		@JavascriptInterface
		public void getMessage(String problemName,String problem) {
			Log.e("success", problemName);
			Log.e("success", problem);
			application.setProblemName(problemName);
			application.setProblem(problem);
			finish();
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview_activity);
		application = (RootAppcation) getApplication();
		WebView webView = (WebView) findViewById(R.id.webview);
		WebSettings webset = webView.getSettings();
		webset.setJavaScriptEnabled(true);
		webView.setWebChromeClient(new WebChromeClient() {
		});
		webView.addJavascriptInterface(new JsObject(), "jsObject");
		Log.e("app", application.getProblem()+"");
		webView.loadUrl(Url.HTML_IN_USE+"problem.do?ids="+application.getProblem());
	}

}
