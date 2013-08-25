package com.blox.framework.v0.impl.libgdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.blox.framework.v0.IFont;
import com.blox.framework.v0.IFontFactory;

public class GdxFontFactory implements IFontFactory {
	@Override
	public IFont create(String fontPath, int size) {		
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(fontPath));
		BitmapFont font = generator.generateFont(size);
		generator.dispose();
		return new GdxFont(font);
	}
}