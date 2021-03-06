package com.turpgames.framework.v0.impl;

import com.turpgames.framework.v0.ITexture;
import com.turpgames.framework.v0.util.Game;
import com.turpgames.framework.v0.util.TextureDrawer;

public abstract class TexturedGameObject extends GameObject {
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