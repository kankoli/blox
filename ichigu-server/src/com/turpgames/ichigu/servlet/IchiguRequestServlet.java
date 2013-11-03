package com.turpgames.ichigu.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/IchiguRequestServlet")
public class IchiguRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
		// DbManager.setConnectionProvider(new MySqlProvider());
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			synchronized (MessageQueue.syncObj) {
				ServletInputStream is = request.getInputStream();
				StringBuffer buffer = new StringBuffer();

				int bytesRead;
				byte[] bytes = new byte[32];

				while ((bytesRead = is.read(bytes, 0, bytes.length)) > 0)
					buffer.append(new String(bytes, 0, bytesRead, "UTF-8"));

				if (buffer.length() > 0)
					MessageQueue.push(buffer.toString());
			}
		}
		catch (Throwable t) {
			t.printStackTrace();
		}
		finally {
			response.getOutputStream().flush();
		}
	}
}