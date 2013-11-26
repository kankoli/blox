package com.turpgames.framework.v0;

public interface IHttpResponseListener {
	void onHttpResponseReceived(IHttpResponse reponse);

	void onError(Throwable t);
}