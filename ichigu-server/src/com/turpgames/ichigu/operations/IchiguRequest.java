package com.turpgames.ichigu.operations;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

public class IchiguRequest {
	private final Map<String, String[]> requestParams;

	public IchiguRequest(Map<String, String[]> requestParams) {
		this.requestParams = requestParams;
	}

	public String getParam(String paramName) {
		try {
			return URLDecoder.decode(requestParams.get(paramName)[0], "UTF-8");
		}
		catch (UnsupportedEncodingException e) {
			return requestParams.get(paramName)[0];
		}
	}

	public String getAuthKey() {
		return getParam("a");
	}

	public String getOperation() {
		return getParam("o");
	}
}
