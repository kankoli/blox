package com.turpgames.framework.v0.net;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.turpgames.framework.v0.IDisposable;

class PersistedHttpClient implements IDisposable {
	private URL url;

	private HttpURLConnection httpConnection = null;

	PersistedHttpClient(String url) throws MalformedURLException {
		this.url = new URL(url);
	}

	public void send(String message) throws IOException {
		OutputStream requestStream = null;
		try {
			requestStream = connect();

			byte[] bytes = message.getBytes("UTF-8");
			requestStream.write(bytes);
			requestStream.flush();

			httpConnection.getInputStream().close();
		}
		catch (UnsupportedEncodingException e) {

		}
		finally {
			if (requestStream != null) {
				try {
					requestStream.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private OutputStream connect() throws IOException {
		httpConnection = (HttpURLConnection) url.openConnection();
		httpConnection.setReadTimeout(0);
		httpConnection.setRequestMethod("POST");
		httpConnection.addRequestProperty("Accept-Charset", "utf-8");
		httpConnection.setDoOutput(true);

		return httpConnection.getOutputStream();
	}

	@Override
	public void dispose() {
		if (httpConnection != null) {
			httpConnection.disconnect();
			httpConnection = null;
		}
	}
}