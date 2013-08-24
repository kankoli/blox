package com.blox.framework.v0.util;

import java.util.HashMap;
import java.util.Map;

import com.blox.framework.v0.IFont;

public final class FontManager {
	private FontManager() {
		
	}
	
	private static final Map<String, IFont> fonts = new HashMap<String, IFont>();
	
	static {
		
	}

	public synchronized static void put(String key, IFont font) {
		fonts.put(key, font);
	}
	
	public synchronized static IFont get(String name) {
		return fonts.get(name);
	}
}
