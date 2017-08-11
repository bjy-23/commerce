package com.wondersgroup.commerce.teamwork.mysupervision;

public interface HttpCallbackListener {
	void onFinish(String response);
	void onError(Exception e);
}
