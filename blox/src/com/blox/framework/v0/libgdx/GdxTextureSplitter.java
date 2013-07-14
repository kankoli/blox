package com.blox.framework.v0.libgdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.blox.framework.v0.ITexture;
import com.blox.framework.v0.ITextureSplitter;

class GdxTextureSplitter implements ITextureSplitter {
	GdxTextureSplitter() {
		
	}
	
	@Override
	public ITexture[] split(ITexture texture, int width, int height) {
		Texture gdxTexture = ((GdxTexture) texture).texture;

		int cols = (int) (gdxTexture.getWidth() / width);
		int rows = (int) (gdxTexture.getHeight() / height);

		TextureRegion[][] regions = TextureRegion.split(gdxTexture, width,
				height);

		GdxTextureRegion[] gdxRegions = new GdxTextureRegion[cols * rows];
		int index = 0;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				gdxRegions[index++] = new GdxTextureRegion(regions[i][j]);
			}
		}

		return gdxRegions;
	}
}