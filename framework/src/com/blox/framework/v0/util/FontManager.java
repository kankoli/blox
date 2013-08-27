package com.blox.framework.v0.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.blox.framework.v0.IFont;
import com.blox.framework.v0.IFontFactory;
import com.blox.framework.v0.metadata.FontMetadata;
import com.blox.framework.v0.metadata.GameMetadata;

public final class FontManager {
	private FontManager() {

	}

	private static final Map<String, IFont> fonts = new HashMap<String, IFont>();

	public static final IFont defaultFont;
	public static final IFont minFont;
	public static final IFont maxFont;

	static {
		loadFonts();

		String defaultFontName = GameMetadata.getParam("default-font");
		int defaultFontSize = 24;
		if (Game.getScreenHeight() > 1000)
			defaultFontSize = 36;

		defaultFont = get(defaultFontName, defaultFontSize);

		FontMetadata defaultFontMeta = GameMetadata.getFont(defaultFontName);
		int[] sizes = defaultFontMeta.getSizes();

		minFont = get(defaultFontName, sizes[0]);
		maxFont = get(defaultFontName, sizes[sizes.length - 1]);
	}

	public static IFont get(String name, int size) {
		return fonts.get(name + ":" + size);
	}

	public static IFont get(String key) {
		String[] keys = key.split(":");
		return get(keys[0], Utils.parseInt(keys[1]));
	}

	private static void loadFonts() {
		Set<String> fontNames = GameMetadata.getFontNames();
		IFontFactory factory = Game.getFontFactory();

		for (String fontName : fontNames) {
			FontMetadata fontMeta = GameMetadata.getFont(fontName);
			Map<Integer, IFont> fonts = factory.create(fontMeta);
			for (int size : fonts.keySet()) {
				FontManager.fonts.put(fontName + ":" + size, fonts.get(size));
			}
		}
	}
}
