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
import java.util.Random;
import java.util.UUID;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.NetJavaImpl;
import com.badlogic.gdx.net.ServerSocket;
import com.badlogic.gdx.net.ServerSocketHints;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;
import com.turpgames.framework.v0.net.TurpHttpClient;
import com.turpgames.framework.v0.util.Utils;

public class TestMain {
	public static void main(String[] args) {
		try {
			int[] arr;
			for (int i = 0; i < 100; i++) {
				arr = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

				int start = 3;

				shuffle(arr, start, arr.length, 100);

				boolean error = false;

				for (int x = 0; x < start; x++)
					error = error || arr[x] != x;

				if (error)
					System.out.print("ERRORRRRRRR: ");

				System.out.print(String.format("[%d]", start));
				arrayWriteLine(arr);
			}

			// for (int i = 0; i < 10; i++) {
			// arr = new int[] { 0, 1, 2, 3, 4, 5, 6, -1, -1, -1 };
			//
			// for (int k = 0; k < 3; k++) {
			// int x = Utils.randInt(arr.length - 3);
			//
			// insertAt(x, arr, 88);
			//
			// System.out.print(String.format("%d = ", x));
			// arrayWriteLine(arr);
			// }
			// for (int j = 0; j < arr.length; j++) {
			// if (arr[j] < 0) {
			// System.out.println("Error!!!");
			// break;
			// }
			// }
			//
			// System.out.println();
			// }

			System.out.println("OK!");
		}
		catch (Throwable t) {
			t.printStackTrace();
		}
	}

	private static void arrayWriteLine(int[] arr) {
		for (int i = 0; i < arr.length; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
	}

	private static void shuffle(int[] arr, int start, int end, int iter) {
		if (start == end || start + 1 == end)
			return;
		while (iter-- > 0) {
			int i1 = Utils.randInt(start, end);
			int i2 = Utils.randInt(start, end);
			while (i1 == i2)
				i2 = Utils.randInt(start, end);

			int tmp = arr[i1];
			arr[i1] = arr[i2];
			arr[i2] = tmp;
		}
	}

	private static void insertAt(int index, int[] deck, int cardToInsert) {
		if (deck[index] > -1) {
			for (int i = deck.length - 1; i > index; i--)
				deck[i] = deck[i - 1];
		}

		deck[index] = cardToInsert;
	}

	private static void testGdxhttpClient() throws Exception {
		Gdx.net = new Net() {
			NetJavaImpl net = new NetJavaImpl();

			@Override
			public void sendHttpRequest(HttpRequest httpRequest, HttpResponseListener httpResponseListener) {
				net.sendHttpRequest(httpRequest, httpResponseListener);
			}

			@Override
			public void openURI(String URI) {

			}

			@Override
			public ServerSocket newServerSocket(Protocol protocol, int port, ServerSocketHints hints) {
				return null;
			}

			@Override
			public Socket newClientSocket(Protocol protocol, String host, int port, SocketHints hints) {
				return null;
			}
		};

		CometClient cc = new CometClient();
		cc.subscribe();
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