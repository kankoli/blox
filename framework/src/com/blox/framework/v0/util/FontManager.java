package com.blox.framework.v0.util;

import com.blox.framework.v0.IFont;

public final class FontManager {
	private FontManager() {

	}

	public static final IFont defaultFont;

	static {
		defaultFont = createDefaultFontInstance();
	}
		
	public static IFont createDefaultFontInstance() {
		// default-font = "Arial,0.5"
		String df = Game.getParam("default-font");
		String[] ss = df.split(",");
		IFont font =  Game.getResourceManager().getFont(ss[0]);
		font.setScale(Utils.parseFloat(ss[1]));
		return font;
	}
}