package com.turpgames.framework.v0;

public interface IHttpClient {
	void send(IHttpRequest request, IHttpResponseListener listener);
}