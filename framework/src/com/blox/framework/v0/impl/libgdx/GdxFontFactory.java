package com.blox.framework.v0.impl.libgdx;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.blox.framework.v0.metadata.ResourceMetadata;
import com.blox.framework.v0.util.Utils;

class GdxFontFactory {
	static GdxFont create(ResourceMetadata metadata) {
		Map<Integer, BitmapFont> map = new HashMap<Integer, BitmapFont>();

		String sSizes = metadata.get("sizes");
		String[] ss = sSizes.split(",");
		int[] sizes = new int[ss.length];
		for (int i = 0; i < sizes.length; i++)
			sizes[i] = Utils.parseInt(ss[i]);
		Arrays.sort(sizes);

		FileHandle file = Gdx.files.internal(metadata.getPath());

		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(file);
		for (int i = 0; i < sizes.length; i++) {
			BitmapFont font = generator.generateFont(sizes[i]);
			map.put(sizes[i], font);
		}
		generator.dispose();

		GdxFont font = new GdxFont(map);
		font.setSize(sizes[sizes.length - 1]);
		
		return font;
	}
}