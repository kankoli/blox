package com.turpgames.framework.v0.net;

import java.util.PriorityQueue;
import java.util.Queue;

class TurpResponseQueue {
	private final Queue<String> responseQueue;
	
	private final StringBuffer currentResponse;
	private int stack = -1;
	
	TurpResponseQueue() {
		this.responseQueue = new PriorityQueue<String>();
		this.currentResponse = new StringBuffer();
	}
	
	public synchronized void push(String response) {
		char[] chars = response.toCharArray();

		for (int i = 0; i < chars.length; i++) {
			if (chars[i] == '{') {
				if (stack == -1)
					stack = 1;
				else
					stack++;
			}

			else if (chars[i] == '}')
				stack--;

			if (stack == 0) {
				responseQueue.add(currentResponse.toString());
				stack = -1;
				currentResponse.setLength(0);
			}
			else if (stack != 1 || chars[i] != '{') {
				currentResponse.append(chars[i]);
			}
		}
	}
	
	public synchronized String pop() {
		return responseQueue.poll();
	}
}
