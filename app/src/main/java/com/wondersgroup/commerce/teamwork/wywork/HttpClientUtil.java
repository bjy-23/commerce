package com.wondersgroup.commerce.teamwork.wywork;


import com.wondersgroup.commerce.teamwork.wywork.HttpCallbackListener;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class HttpClientUtil {

	public static void callWebService(final String postParam,
			final String address, final HttpCallbackListener listener) {

		new Thread(new Runnable() {

			@Override
			public void run() {
				PostMethod postMethod = new PostMethod(address);
				// 设置POST方法请求超时
				postMethod.getParams().setParameter(
						HttpMethodParams.SO_TIMEOUT, 8000);

				try {

					postMethod.addRequestHeader("Content-Type",
							"application/json;charset=UTF-8");
					postMethod.addRequestHeader("Accept", "application/json");
					postMethod.setRequestBody(postParam);
					
					HttpClient httpClient = new HttpClient();
					HttpConnectionManagerParams managerParams = httpClient
							.getHttpConnectionManager().getParams();
					// 设置连接超时时间(单位毫秒)
					managerParams.setConnectionTimeout(8000);
					// 设置读数据超时时间(单位毫秒)
					managerParams.setSoTimeout(8000);

					int statusCode = httpClient.executeMethod(postMethod);
					if (statusCode != HttpStatus.SC_OK)
						throw new IllegalStateException("调用webservice错误 : "
								+ postMethod.getStatusLine());

					BufferedReader br = new BufferedReader(
							new InputStreamReader(
									postMethod.getResponseBodyAsStream(),
									"UTF-8"));
					StringBuffer sb = new StringBuffer();
					String str = "";
					while ((str = br.readLine()) != null) {
						sb.append(str);
					}
					if (listener != null) {
						// 回调onFinish()方法
//						Log.d("--------------------------------------------",
//								"回调onfinish" + sb);
						listener.onFinish(sb.toString());

					}
				} catch (UnsupportedEncodingException e) {
					if (listener != null) {
						// 回调onError()方法
						e.printStackTrace();
//						Log.d("--------------------------------------------",
//								"回调onError");
						listener.onError(e);
					}
				} catch (HttpException e) {
					if (listener != null) {
						// 回调onError()方法
						e.printStackTrace();
//						Log.d("--------------------------------------------",
//								"回调onError");
						listener.onError(e);
					}
				} catch (IOException e) {
					if (listener != null) {
						// 回调onError()方法
						e.printStackTrace();
//						Log.d("--------------------------------------------",
//								"回调onError");
						listener.onError(e);
					}
				} catch (IllegalStateException e) {
					if (listener != null) {
						// 回调onError()方法
						e.printStackTrace();
//						Log.d("--------------------------------------------",
//								"回调onError");
						listener.onError(e);
					}
				} finally {
					postMethod.releaseConnection();
				}

			}
		}).start();

	}

	public static void callWebServiceForGet(final String address,
			final HttpCallbackListener listener) {

		new Thread(new Runnable() {

			@Override
			public void run() {
				GetMethod getMethod = new GetMethod(address);
				// 设置POST方法请求超时
				getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT,
						8000);

				try {

					getMethod.addRequestHeader("Content-Type",
							"text/html;charset=UTF-8");

					HttpClient httpClient = new HttpClient();
					HttpConnectionManagerParams managerParams = httpClient
							.getHttpConnectionManager().getParams();
					// 设置连接超时时间(单位毫秒)
					managerParams.setConnectionTimeout(8000);
					// 设置读数据超时时间(单位毫秒)
					managerParams.setSoTimeout(600000);

					int statusCode = httpClient.executeMethod(getMethod);
					if (statusCode != HttpStatus.SC_OK)
						throw new IllegalStateException("调用webservice错误 : "
								+ getMethod.getStatusLine());

					BufferedReader br = new BufferedReader(
							new InputStreamReader(
									getMethod.getResponseBodyAsStream(),
									"UTF-8"));
					StringBuffer sb = new StringBuffer();
					String str = "";
					while ((str = br.readLine()) != null) {
						sb.append(str);
					}
					if (listener != null) {
						// 回调onFinish()方法
//						Log.d("--------------------------------------------",
//								"回调onfinish" + sb);
						listener.onFinish(sb.toString());

					}
				} catch (UnsupportedEncodingException e) {
					if (listener != null) {
						// 回调onError()方法
						e.printStackTrace();
//						Log.d("--------------------------------------------",
//								"回调onError");
						listener.onError(e);
					}
				} catch (HttpException e) {
					if (listener != null) {
						// 回调onError()方法
						e.printStackTrace();
//						Log.d("--------------------------------------------",
//								"回调onError");
						listener.onError(e);
					}
				} catch (IOException e) {
					if (listener != null) {
						// 回调onError()方法
						e.printStackTrace();
//						Log.d("--------------------------------------------",
//								"回调onError");
						listener.onError(e);
					}
				} catch (IllegalStateException e) {
					if (listener != null) {
						// 回调onError()方法
						e.printStackTrace();
//						Log.d("--------------------------------------------",
//								"回调onError");
						listener.onError(e);
					}
				} finally {
					getMethod.releaseConnection();
				}

			}
		}).start();

	}
}
