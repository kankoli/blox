package com.blox.framework.v0.util;

public class Color {
	public int a;
	public int r;
	public int g;
	public int b;

	public Color() {
		this(255, 255, 255, 255);
	}

	public Color(int gray) {
		this(gray, 255);
	}

	public Color(int gray, int a) {
		this(gray, gray, gray, a);
	}

	public Color(int r, int g, int b) {
		this(r, g, b, 255);
	}

	public Color(int r, int g, int b, int a) {
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}
}
