package com.turpgames.framework.v0.impl.libgdx;

import com.badlogic.gdx.Net;
import com.badlogic.gdx.Net.HttpResponse;
import com.turpgames.framework.v0.IHttpResponseListener;

class GdxHttpResponseListener implements Net.HttpResponseListener {
	private final IHttpResponseListener listener;
	
	GdxHttpResponseListener(IHttpResponseListener listener) {
		this.listener = listener;
	}

	@Override
	public void handleHttpResponse(HttpResponse httpResponse) {
		listener.onHttpResponseReceived(new GdxHttpResponse(httpResponse));
	}

	@Override
	public void failed(Throwable t) {
		listener.onError(t);
	}
}
