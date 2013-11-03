package com.turpgames.framework.v0.net;

import java.util.PriorityQueue;
import java.util.Queue;

class TurpRequestQueue {
	private Queue<String> requestQueue = new PriorityQueue<String>();
	
	TurpRequestQueue() {
		
	}
	
	public synchronized void push(String request) {
		requestQueue.add(request);
	}
	
	public synchronized String pop() {
		return requestQueue.poll();
	}
}
