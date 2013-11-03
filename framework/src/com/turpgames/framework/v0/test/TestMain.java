package com.turpgames.framework.v0.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.UUID;

import com.turpgames.framework.v0.net.TurpHttpClient;

public class TestMain {
	public static void main(String[] args) {
		try {
			for (int i = 0; i < 100; i++)
				testTurpClient();
			System.out.println("OK!");
		}
		catch (Throwable t) {
			t.printStackTrace();
		}
	}

	protected static void testTurpClient() throws IOException {
		final TurpHttpClient client = new TurpHttpClient(
				"http://localhost:8080/ichigu-server/IchiguRequestServlet",
				"http://localhost:8080/ichigu-server/IchiguResponseServlet");

		client.connect();

		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					sendRandomRequests(client);
				}
				catch (Throwable t) {
					System.out.println("Request Thread failed!");
					t.printStackTrace();
				}
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					readResponses(client);
				}
				catch (Throwable t) {
					System.out.println("Read Response Thread failed!");
					t.printStackTrace();
				}
			}
		}).start();
	}

	protected static void sendRandomRequests(TurpHttpClient client) throws Exception {
		while (true) {
			Thread.sleep(50);

			String guid = UUID.randomUUID().toString();

			client.sendRequest(guid);

			System.out.println("sent: " + guid);
		}
	}

	protected static void readResponses(TurpHttpClient client) throws Exception {
		while (true) {
			String s = client.readResponse();
			if (s != null)
				System.out.println("read: " + s);
			Thread.sleep(50);
		}
	}

	protected static void testEncoding() throws UnsupportedEncodingException, MalformedURLException, IOException, ProtocolException {
		String plain = "ý ü ð þ ç ö Ý Ü Ð Þ Ç Ö";
		String encoded = URLEncoder.encode(plain, "UTF-8");
		String decoded = URLDecoder.decode(encoded, "UTF-8");

		System.out.println(plain);
		System.out.println(encoded);
		System.out.println(decoded);

		byte[] buffer = new byte[10240];

		URL url = new URL("http://localhost:8080/ichigu-server/IchiguRequestServlet?w=" + encoded);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setReadTimeout(10000);
		conn.setRequestMethod("GET");
		conn.addRequestProperty("Accept-Charset", "utf-8");

		InputStream is = conn.getInputStream();

		int b = 0;
		int i = 0;
		while ((b = is.read()) > 0)
			buffer[i++] = (byte) b;

		System.out.println(new String(buffer, 0, i));
	}
}