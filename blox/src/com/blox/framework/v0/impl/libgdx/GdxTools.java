package com.blox.framework.v0.impl.libgdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.blox.framework.v0.util.ToolBox;

final class GdxTools {
	static SpriteBatch spriteBatch;
	
	static {
		Texture.setEnforcePotImages(false);
		ToolBox.screenWidth = Gdx.graphics.getWidth();
		ToolBox.screenHeight = Gdx.graphics.getHeight();
	}
	
	private GdxTools() {
		
	}
}
