package com.blox.framework.v0.libgdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

final class GdxGame {
	static SpriteBatch spriteBatch;
	static ShapeRenderer shapeRenderer;
	
	static {
		Texture.setEnforcePotImages(false);
	}
	
	private GdxGame() {
		
	}
}
