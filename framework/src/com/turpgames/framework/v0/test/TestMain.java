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

public class TestMain {
	public static void main(String[] args) {
		try {
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						sendRandomRequests();
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
						readResponses();
					}
					catch (Throwable t) {
						System.out.println("Response Thread failed!");
						t.printStackTrace();
					}
				}
			}).start();

			System.out.println("OK!");

		}
		catch (Throwable t) {
			t.printStackTrace();
		}
	}

	protected static void sendRandomRequests() throws Exception {
		while (true) {
			Thread.sleep(1000);

			String guid = UUID.randomUUID().toString();
			URL url = new URL("http://localhost:8080/ichigu-server/IchiguRequestServlet?p=" + guid);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(10000);
			conn.setRequestMethod("GET");
			conn.addRequestProperty("Accept-Charset", "utf-8");

			InputStream is = conn.getInputStream();

			byte b = (byte) is.read();
			String s = new String(new byte[] { b });
			if ("1".equals(s))
				System.out.println("sent: " + guid);
			else
				System.out.println("not sent: " + guid);

			is.close();
		}
	}

	protected static void readResponses() throws Exception {
		URL url = new URL("http://localhost:8080/ichigu-server/IchiguResponseServlet");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setReadTimeout(0);
		conn.setRequestMethod("GET");
		conn.addRequestProperty("Accept-Charset", "utf-8");

		InputStream is = conn.getInputStream();

		try {
			int bytesRead = 0;
			byte[] buffer = new byte[1024];
			while ((bytesRead = is.read(buffer, 0, buffer.length)) > 0) {
				System.out.println("read: " + bytesRead + " - " + new String(buffer, 0, bytesRead));
			}
		}
		finally {
			is.close();
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