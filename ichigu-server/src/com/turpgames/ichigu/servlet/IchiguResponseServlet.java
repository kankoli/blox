package com.turpgames.ichigu.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.ClientAbortException;

@WebServlet("/IchiguResponseServlet")
public class IchiguResponseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public IchiguResponseServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			ServletOutputStream os = response.getOutputStream(); 
			while (true) {
				synchronized (MessageQueue.syncObj) {
					if (MessageQueue.size() > 0) {
						while (MessageQueue.size() > 0) {
							os.print(MessageQueue.pop());
						}
						os.flush();
					}
				}
				Thread.sleep(50);					
			}
		}
		catch (ClientAbortException cae) {
			System.out.println("user closed connection!");
		}
		catch (Throwable t) {
			t.printStackTrace();
			response.getOutputStream().print(t.getMessage());
		}
	}
}
