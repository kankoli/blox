package com.turpgames.framework.v0.net;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

class TurpResponseReceiver {
	private final TurpResponseQueue queue;
	private final String url;
	private final Thread responseThread;

	private volatile boolean running;
	private InputStream responseStream;
	private HttpURLConnection httpConnection;

	TurpResponseReceiver(TurpResponseQueue queue, String url) {
		this.queue = queue;
		this.url = url;
		this.responseThread = new Thread(new Runnable() {
			@Override
			public void run() {
				receiveResponses();
			}
		});
	}

	public synchronized void start() throws IOException {
		if (running)
			return;
		running = true;
		openConnection();
		responseThread.start();
	}

	public synchronized void stop() {
		closeConnection();
		running = false;
	}

	private void openConnection() throws IOException {
		URL url = new URL(this.url);
		httpConnection = (HttpURLConnection) url.openConnection();
		httpConnection.setReadTimeout(0);
		httpConnection.setRequestMethod("GET");
		httpConnection.addRequestProperty("Accept-Charset", "utf-8");
	}

	private void closeConnection() {
		try {
			responseStream.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		httpConnection.disconnect();
	}

	private void receiveResponses() {
		while (running) {
			try {
				responseStream = httpConnection.getInputStream();
				readResponse();
			}
			catch (IOException e) {
				// TODO: Notify connection failed
				e.printStackTrace();
			}
		}
	}

	private void readResponse() throws IOException {
		int bytesRead = 0;
		byte[] buffer = new byte[1024];
		while ((bytesRead = responseStream.read(buffer, 0, buffer.length)) > 0) {
			queue.push(new String(buffer, 0, bytesRead));
		}
	}
}
