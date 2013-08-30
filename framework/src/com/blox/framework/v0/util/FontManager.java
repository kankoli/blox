package com.blox.framework.v0.util;

import com.blox.framework.v0.IFont;

public final class FontManager {
	private FontManager() {

	}

	public static final IFont defaultFont;

	static {
		defaultFont = Game.getResourceManager().getFont(Game.getParam("default-font"));
		setSize(36);
	}
	
	static void setSize(int size) {
		defaultFont.setSize(size);
	}
}