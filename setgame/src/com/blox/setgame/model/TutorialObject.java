package com.blox.setgame.model;

import com.blox.framework.v0.ITexture;
import com.blox.framework.v0.util.TextureDrawer;
import com.blox.framework.v0.util.Vector;

public class TutorialObject extends SetGameObject {

	private ITexture texture;
	
	public TutorialObject(ITexture texture, Vector location, int width, int height) {
		this.texture = texture;
		
		setWidth(width);
		setHeight(height);
		super.getLocation().set(location);
	}
	@Override
	public void draw() {
		TextureDrawer.draw(texture, this);
	}

}
