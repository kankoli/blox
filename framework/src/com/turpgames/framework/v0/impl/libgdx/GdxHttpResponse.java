package com.turpgames.framework.v0.impl.libgdx;

import java.io.InputStream;

import com.badlogic.gdx.Net;
import com.turpgames.framework.v0.IHttpResponse;

class GdxHttpResponse implements IHttpResponse {
	private final Net.HttpResponse response;

	private String[] headers;

	public GdxHttpResponse(Net.HttpResponse response) {
		this.response = response;
	}

	public InputStream getInputStream() {
		return response.getResultAsStream();
	}

	public int getStatus() {
		return response.getStatus().getStatusCode();
	}

	public String getHeader(String name) {
		return response.getHeader(name);
	}

	public String[] getHeaders() {
		if (headers == null) {
			headers = response.getHeaders().keySet().toArray(new String[0]);
		}
		return headers;
	}
}