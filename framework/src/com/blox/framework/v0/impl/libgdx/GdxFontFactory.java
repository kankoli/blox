package com.blox.framework.v0.impl.libgdx;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.blox.framework.v0.IFont;
import com.blox.framework.v0.IFontFactory;
import com.blox.framework.v0.metadata.FontMetadata;

public class GdxFontFactory implements IFontFactory {
	@Override
	public Map<Integer, IFont> create(FontMetadata metadata) {
		Map<Integer, IFont> map = new HashMap<Integer, IFont>();
		
		int[] sizes = metadata.getSizes();
		
		FileHandle file = Gdx.files.internal(metadata.getPath());
		
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(file);
		for (int i = 0; i < sizes.length; i++) {
			BitmapFont font = generator.generateFont(sizes[i]);
			map.put(sizes[i], new GdxFont(font));
		}		
		generator.dispose();
		
		return map;
	}
}