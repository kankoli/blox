package com.turpgames.framework.v0.impl;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.turpgames.framework.v0.IHttpRequest;

public class HttpRequest implements IHttpRequest {
	private final String httpMethod;
	
	private String url;
	private Map<String, String> headers;
	private int timeout = 0;

	private InputStream contentStream;
	private long contentLength;

	public HttpRequest (String httpMethod) {
		this.httpMethod = httpMethod;
		this.headers = new HashMap<String, String>();
	}

	public void setUrl (String url) {
		this.url = url;
	}

	public void putHeader (String name, String value) {
		headers.put(name, value);
	}
	
	public void setContent (InputStream contentStream, long contentLength) {
		this.contentStream = contentStream;
		this.contentLength = contentLength;
	}

	public void setTimeout (int timeout) {
		this.timeout = timeout;
	}
	
	@Override
	public int getTimeout () {
		return timeout;
	}

	public String getMethod () {
		return httpMethod;
	}
	
	@Override
	public String getUrl () {
		return url;
	}
	
	@Override
	public InputStream getContentStream () {
		return contentStream;
	}
	
	@Override
	public long getContentLength () {
		return contentLength;
	}
	
	@Override
	public Map<String, String> getHeaders () {
		return headers;
	}
}