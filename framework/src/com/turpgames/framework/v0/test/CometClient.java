package com.turpgames.framework.v0.test;

import java.io.IOException;

import com.turpgames.framework.v0.IGameProvider;
import com.turpgames.framework.v0.IHttpClient;
import com.turpgames.framework.v0.IHttpResponse;
import com.turpgames.framework.v0.IHttpResponseListener;
import com.turpgames.framework.v0.impl.HttpRequest;
import com.turpgames.framework.v0.impl.libgdx.GdxGameProvider;
import com.turpgames.framework.v0.util.Utils;

public class CometClient {
	public void subscribe() {
		IGameProvider prov = new GdxGameProvider();

		IHttpClient client = prov.createHttpClient();

		HttpRequest req = new HttpRequest("GET");

		req.setUrl("http://localhost:8080/ichigu-server/comet");
		req.setTimeout(10000);
		req.setContent(null, 0);

		client.send(req, new IHttpResponseListener() {

			@Override
			public void onHttpResponseReceived(IHttpResponse response) {
				try {
					if (response.getStatus() >= 200 && response.getStatus() < 300) {
						String resp = Utils.readUtf8String(response.getInputStream());
						System.out.println("subscribe: " + resp);
						subscribe();
					}
					else {
						System.out.println("subscribe returned: " + response.getStatus());
					}
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onError(Throwable t) {
				t.printStackTrace();
			}
		});
	}

	public void bid(String bid) {
		IGameProvider prov = new GdxGameProvider();

		IHttpClient client = prov.createHttpClient();

		HttpRequest req = new HttpRequest("POST");

		req.setUrl("http://localhost:8080/ichigu-server/comet?bid=" + bid);
		req.setTimeout(0);
		req.setContent(null, 0);

		client.send(req, new IHttpResponseListener() {
			@Override
			public void onHttpResponseReceived(IHttpResponse response) {
				String resp;
				try {
					resp = Utils.readUtf8String(response.getInputStream());
					System.out.println("bid: " + resp);
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onError(Throwable t) {
				t.printStackTrace();
			}
		});
	}
}
