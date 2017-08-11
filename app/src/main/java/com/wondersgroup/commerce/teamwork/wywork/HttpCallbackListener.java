package com.wondersgroup.commerce.teamwork.wywork;

public interface HttpCallbackListener {
	void onFinish(String response);
	void onError(Exception e);
}
