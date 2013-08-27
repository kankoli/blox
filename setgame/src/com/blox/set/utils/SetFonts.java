package com.blox.set.utils;

import com.blox.framework.v0.IFont;
import com.blox.framework.v0.metadata.GameMetadata;
import com.blox.framework.v0.util.FontManager;

public class SetFonts {
	
	public static final IFont font12;
	public static final IFont font18;
	public static final IFont font24;
	public static final IFont font36;
	public static final IFont font48;
	public static final IFont font60;
	public static final IFont font72;
	
	public static void init() {
		
	}
	
	static {
		String fontId = GameMetadata.getParam("default-font");
				
		font12 = FontManager.get(fontId, 12);
		font18 = FontManager.get(fontId, 18);
		font24 = FontManager.get(fontId, 24);
		font36 = FontManager.get(fontId, 36);
		font48 = FontManager.get(fontId, 48);
		font60 = FontManager.get(fontId, 60);
		font72 = FontManager.get(fontId, 72);
	}
	
	private SetFonts() {
		
	}
}
