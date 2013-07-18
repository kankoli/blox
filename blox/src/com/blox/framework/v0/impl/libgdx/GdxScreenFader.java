package com.blox.framework.v0.impl.libgdx;

import com.blox.framework.v0.IScreenFader;

class GdxScreenFader implements IScreenFader {
	GdxScreenFader() {

	}

	@Override
	public void fade(float alpha) {
		GdxGame.spriteBatch.setColor(1, 1, 1, alpha);
	}
}
