package com.turpgames.framework.v0.net;

import java.io.IOException;

class TurpRequestSender {
	private final TurpRequestQueue queue;
	private String url;
	private final Thread requestThread;

	private volatile boolean running;

	private PersistedHttpClient client = null;

	TurpRequestSender(TurpRequestQueue queue, String url) {
		this.queue = queue;
		this.url = url;
		this.requestThread = new Thread(new Runnable() {
			@Override
			public void run() {
				sendRequests();
			}
		});
	}

	public synchronized void start() throws IOException {
		if (running)
			return;

		client = new PersistedHttpClient(url);

		running = true;
		requestThread.start();
	}

	public synchronized void stop() {
		running = false;
		if (client != null) {
			client.dispose();
			client = null;
		}
	}

	private void sendRequests() {
		while (running) {
			try {
				String request;
				while ((request = queue.pop()) != null) {
					client.send(request);
				}
				Thread.sleep(50);
			}
			catch (InterruptedException e) {
				stop();
			}
			catch (IOException e) {
				// TODO: Notify connection failed
				e.printStackTrace();
			}
		}
	}
}
