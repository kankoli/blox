package com.turpgames.ichigu.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.turpgames.ichigu.db.DbManager;
import com.turpgames.ichigu.db.MySqlProvider;

@WebServlet("/IchiguRequestServlet")
public class IchiguRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
		DbManager.setConnectionProvider(new MySqlProvider());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			synchronized (MessageQueue.syncObj) {
				MessageQueue.push(request.getParameter("p"));
				response.getOutputStream().print("1");
			}
		}
		catch (Throwable t) {
			t.printStackTrace();
			response.getOutputStream().print("0");
		}
	}
}
