package com.blox.framework.v0.impl.libgdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.blox.framework.v0.ITexture;
import com.blox.framework.v0.ITextureSplitter;
import com.blox.framework.v0.util.Game;

class GdxTextureSplitter implements ITextureSplitter {
	GdxTextureSplitter() {

	}

	@Override
	public ITexture[] split(ITexture texture, int width, int height) {
		Texture gdxTexture = ((GdxTexture) texture).texture;

		float w = Game.world.descale(width);
		float h = Game.world.descale(height);

		int cols = (int) (gdxTexture.getWidth() / w);
		int rows = (int) (gdxTexture.getHeight() / h);

		if (cols < 1 || rows < 1)
			return new GdxTextureRegion[] { new GdxTextureRegion(
					new TextureRegion(gdxTexture)) };

		TextureRegion[][] regions = TextureRegion.split(gdxTexture, (int) w,
				(int) h);

		return toArray(cols, rows, regions);
	}

	private GdxTextureRegion[] toArray(int cols, int rows,
			TextureRegion[][] regions) {
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