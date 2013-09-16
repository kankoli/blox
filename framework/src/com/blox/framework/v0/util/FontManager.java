package com.blox.framework.v0.util;

import com.blox.framework.v0.IFont;

public final class FontManager {
	private FontManager() {

	}

	public static final IFont defaultFont;

	static {
		defaultFont = createDefaultFontInstance();
	}
	
	static void setSize(int size) {
		defaultFont.setSize(size);
	}
	
	public static IFont createDefaultFontInstance() {
		// default-font = "Arial,24"
		String df = Game.getParam("default-font");
		String[] ss = df.split(",");
		IFont font =  Game.getResourceManager().getFont(ss[0]);
		font.setSize(Utils.parseInt(ss[1]));
		return font;
	}
}