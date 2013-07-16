package com.blox.framework.v0.impl.libgdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.blox.framework.v0.util.ToolBox;

final class GdxGame {
	static SpriteBatch spriteBatch;
	static ShapeRenderer shapeRenderer;
	
	static {
		Texture.setEnforcePotImages(false);
		ToolBox.screenWidth = Gdx.graphics.getWidth();
		ToolBox.screenHeight = Gdx.graphics.getHeight();
	}
	
	private GdxGame() {
		
	}
}
