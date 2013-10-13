package com.blox.framework.v0.impl;

import com.blox.framework.v0.ITexture;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.TextureDrawer;

public class TexturedGameObject extends GameObject {
	private ITexture texture;

	public TexturedGameObject(String id) {
		setTexture(id);
	}
	
	public void setTexture(String id) {
		texture = Game.getResourceManager().getTexture(id); 
	}
	
	@Override
	public void draw() {
		TextureDrawer.draw(texture, this);		
	}
}