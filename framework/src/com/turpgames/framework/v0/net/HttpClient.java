package com.turpgames.framework.v0.net;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public final class HttpClient {
	private HttpClient() {
		
	}

	public static int get(String uri, byte[] responseBuffer, int timeout) throws Exception {
		URL url = new URL(uri);
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setReadTimeout(timeout);		
		conn.setRequestMethod("GET");
		conn.addRequestProperty("charset", "utf-8");
		
		InputStream is = conn.getInputStream();
		
		int b = 0;
		int i = 0;
		while ((b = is.read()) > 0)
			responseBuffer[i++] = (byte)b;
		
		return i;
	}
}
