package com.turpgames.framework.v0;

import java.io.InputStream;
import java.util.Map;

public interface IHttpRequest {
	int getTimeout();

	String getMethod();

	String getUrl();

	InputStream getContentStream();

	long getContentLength();

	Map<String, String> getHeaders();
}
