package com.blox.set.utils;

import com.blox.framework.v0.IFont;
import com.blox.framework.v0.IFontFactory;
import com.blox.framework.v0.util.FontManager;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.GameMetadata;

public class SetFonts {
	
	public static final IFont font12;
	public static final IFont font18;
	public static final IFont font24;
	public static final IFont font36;
	public static final IFont font48;
	public static final IFont font60;
	public static final IFont font72;
	
	static {

		String fontId = GameMetadata.getParam("default-font");
		
		IFontFactory factory = Game.getFontFactory();
		font12 = factory.create(fontId, 12);
		font18 = factory.create(fontId, 18);
		font24 = factory.create(fontId, 24);
		font36 = factory.create(fontId, 36);
		font48 = factory.create(fontId, 48);
		font60 = factory.create(fontId, 60);
		font72 = factory.create(fontId, 72);

		FontManager.put(fontId + "-12", font12);
		FontManager.put(fontId + "-18", font18);
		FontManager.put(fontId + "-24", font24);
		FontManager.put(fontId + "-36", font36);
		FontManager.put(fontId + "-48", font48);
		FontManager.put(fontId + "-60", font60);
		FontManager.put(fontId + "-72", font72);
	}
	
	private SetFonts() {
		
	}
}
