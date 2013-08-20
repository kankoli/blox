package com.blox.framework.v0.util;

public class Utils {
	public static boolean isIn(float x, float y, Vector location, float width, float height) {
		return x > location.x && x < location.x + width &&
			   y > location.y && y < location.y + height;
	}
}
