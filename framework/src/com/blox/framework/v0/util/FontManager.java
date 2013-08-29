package com.blox.framework.v0.util;

import com.blox.framework.v0.IFont;

public final class FontManager {
	private FontManager() {

	}

	private final static float fontScale;
	public static final IFont defaultFont;

	static {
		fontScale = (Game.getScreenHeight() / Game.getVirtualHeight()) / 72f;
		defaultFont = Game.getResourceManager().getFont(Game.getParam("default-font"));
		setSize(24f);
	}
	
	static void setSize(float size) {
		defaultFont.scale(fontScale * size);
	}
}