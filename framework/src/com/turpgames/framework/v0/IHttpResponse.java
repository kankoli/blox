package com.turpgames.framework.v0;

import java.io.InputStream;

public interface IHttpResponse {
	int getStatus();

	InputStream getInputStream();

	String getHeader(String name);

	String[] getHeaders();
}