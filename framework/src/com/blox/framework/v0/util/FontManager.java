package com.blox.framework.v0.util;

import com.blox.framework.v0.IFont;

public final class FontManager {
	private FontManager() {

	}

	public static final IFont defaultFont;

	static {
		// default-font = "Arial,24"
		String df = Game.getParam("default-font");
		String[] ss = df.split(",");
		defaultFont = Game.getResourceManager().getFont(ss[0]);
		setSize(Utils.parseInt(ss[1]));
	}
	
	static void setSize(int size) {
		defaultFont.setSize(size);
	}
}