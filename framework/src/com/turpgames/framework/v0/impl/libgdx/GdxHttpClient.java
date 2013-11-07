package com.turpgames.framework.v0.impl.libgdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.turpgames.framework.v0.IHttpClient;
import com.turpgames.framework.v0.IHttpRequest;
import com.turpgames.framework.v0.IHttpResponseListener;

class GdxHttpClient implements IHttpClient {
	GdxHttpClient() {

	}

	@Override
	public void send(IHttpRequest request, IHttpResponseListener listener) {
		Net.HttpRequest gdxHttpRequest = new Net.HttpRequest(request.getMethod());

		gdxHttpRequest.setTimeOut(request.getTimeout());
		gdxHttpRequest.setContent(request.getContentStream(), request.getContentLength());
		gdxHttpRequest.setUrl(request.getUrl());

		for (String key : request.getHeaders().keySet()) {
			gdxHttpRequest.setHeader(key, request.getHeaders().get(key));
		}

		Gdx.net.sendHttpRequest(gdxHttpRequest, new GdxHttpResponseListener(listener));
	}
}
