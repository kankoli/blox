package com.turpgames.framework.v0.impl.libgdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.turpgames.framework.v0.ITexture;

final class GdxTextureSplitter {
	private GdxTextureSplitter() {

	}

	static ITexture[] split(Texture texture, int width, int height) {

		int cols = texture.getWidth() / width;
		int rows = texture.getHeight() / height;

		if (cols < 1 || rows < 1)
			return new GdxTextureRegion[] { new GdxTextureRegion(new TextureRegion(texture)) };

		TextureRegion[][] regions = TextureRegion.split(texture, width, height);

		return toArray(cols, rows, regions);
	}

	private static GdxTextureRegion[] toArray(int cols, int rows, TextureRegion[][] regions) {
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