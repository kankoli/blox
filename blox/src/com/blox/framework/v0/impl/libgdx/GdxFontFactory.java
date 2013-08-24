package com.blox.framework.v0.impl.libgdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.blox.framework.v0.IFont;
import com.blox.framework.v0.IFontFactory;
import com.blox.framework.v0.util.FontMetadata;
import com.blox.framework.v0.util.GameMetadata;

public class GdxFontFactory implements IFontFactory {

	@Override
	public IFont create(String fontId, int size) {
		FontMetadata metadata = GameMetadata.getFont(fontId);
		
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(metadata.getPath()));
		BitmapFont font = generator.generateFont(size);
		generator.dispose();
		return new GdxFont(font);
	}

}
