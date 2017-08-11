package com.wondersgroup.commerce.teamwork.dailycheck;

public interface HttpCallbackListener {
	void onFinish(String response);
	void onError(Exception e);
}
