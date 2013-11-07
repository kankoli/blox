package com.turpgames.comet;

import java.util.Calendar;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "comet", urlPatterns = { "/comet" }, asyncSupported = true)
public class CometServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Queue<AsyncContext> queue = new ConcurrentLinkedQueue<AsyncContext>();

	@Override
	public void init() throws ServletException {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						AsyncContext ctx;
						synchronized (queue) {
							queue.wait();
						}
						ctx = queue.poll();
						ctx.getResponse().getWriter().write("" + Calendar.getInstance().get(Calendar.MILLISECOND));
						ctx.complete();
					}
					catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		AsyncContext ctx = request.startAsync(request, response);
		queue.add(ctx);
		synchronized (queue) {
			queue.notify();
		}
	}
}