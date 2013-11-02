package com.turpgames.ichigu.servlet;

import java.util.PriorityQueue;
import java.util.Queue;

public class MessageQueue {
	public final static Object syncObj = new Object(); 
	
	private final static Queue<String> messages = new PriorityQueue<String>();
	
	public static void push(String message) {
		messages.add(message);
	}
	
	public static String pop() {
		return messages.poll();
	}
	
	public static int size() {
		return messages.size();
	}
}
