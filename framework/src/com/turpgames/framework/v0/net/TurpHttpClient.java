package com.turpgames.framework.v0.net;

import java.io.IOException;

import com.turpgames.framework.v0.IDisposable;

public final class TurpHttpClient implements IDisposable {
	private final TurpRequestQueue requestQueue;
	private final TurpResponseQueue responseQueue;
	private final TurpRequestSender requestSender;
	private final TurpResponseReceiver responseReceiver;
	
	public TurpHttpClient(String requestServlet, String responseServlet) {
		this.requestQueue = new TurpRequestQueue();
		this.responseQueue = new TurpResponseQueue();

		this.requestSender = new TurpRequestSender(requestQueue, requestServlet);
		this.responseReceiver = new TurpResponseReceiver(responseQueue, responseServlet);
	}
	
	public void connect() throws IOException {
		requestSender.start();
		responseReceiver.start();
	}
	
	public void disconnect() {
		requestSender.stop();
		responseReceiver.stop();
	}
	
	public void sendRequest(String request) {
		requestQueue.push(request);
	}
	
	public String readResponse() {
		return responseQueue.pop();
	}

	@Override
	public void dispose() {
		disconnect();		
	}
}