package com.blox.framework.v0.util;

import java.util.HashMap;
import java.util.Map;

public class Color {
	private static final Map<Character, Integer> hexChars;

	static {
		hexChars = new HashMap<Character, Integer>();
		hexChars.put('0', 0x0);
		hexChars.put('1', 0x1);
		hexChars.put('2', 0x2);
		hexChars.put('3', 0x3);
		hexChars.put('4', 0x4);
		hexChars.put('5', 0x5);
		hexChars.put('6', 0x6);
		hexChars.put('7', 0x7);
		hexChars.put('8', 0x8);
		hexChars.put('9', 0x9);
		hexChars.put('a', 0xa);
		hexChars.put('b', 0xb);
		hexChars.put('c', 0xc);
		hexChars.put('d', 0xd);
		hexChars.put('e', 0xe);
		hexChars.put('f', 0xf);
	}

	public static final Color White = new Color(1);
	public static final Color Black = new Color();
	public static final Color Red = new Color(1, 0, 0);
	public static final Color Green = new Color(0, 1, 0);
	public static final Color Blue = new Color(0, 0, 1);

	private static float getColorValue(char hex) {
		if (hexChars.containsKey(hex))
			return (float) hexChars.get(hex) / 15f;
		return 0;
	}

	private static int getColorValue(String hex) {
		if (hex.length() != 2)
			return 0;
		int hex0 = hexChars.get(hex.charAt(0));
		int hex1 = hexChars.get(hex.charAt(1));
		return 16 * hex0 + hex1;
	}

	public float a;
	public float r;
	public float g;
	public float b;

	public Color() {
		this(0, 0, 0, 1);
	}

	public Color(int gray) {
		this(gray, 1);
	}

	public Color(int gray, int a) {
		this(gray, gray, gray, a);
	}

	public Color(int r, int g, int b) {
		this(r, g, b, 1);
	}

	public Color(int r, int g, int b, int a) {
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}

	public static Color fromHex(String value) {
		Color color = new Color();

		if (!value.startsWith("#"))
			return color;

		String hex = value.toLowerCase().substring(1);

		if (hex.length() == 3) {
			color.r = getColorValue(hex.charAt(0));
			color.g = getColorValue(hex.charAt(1));
			color.b = getColorValue(hex.charAt(2));
		}
		else if (hex.length() == 6) {
			color.r = getColorValue(hex.substring(0, 2));
			color.g = getColorValue(hex.substring(2, 4));
			color.b = getColorValue(hex.substring(4, 6));
		}
		else if (hex.length() == 8) {
			color.r = getColorValue(hex.substring(0, 2));
			color.g = getColorValue(hex.substring(2, 4));
			color.b = getColorValue(hex.substring(4, 5));
			color.a = getColorValue(hex.substring(6, 8));
		}

		return color;
	}
}
