package com.blox.setgame.model;

import com.blox.framework.v0.ITexture;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.TextureDrawer;

public class SetGameImageButton extends SetGameButton {
	public final static float buttonSize = 48;
	
	private ITexture texture;
	
	SetGameImageButton() {
		super();
		setWidth(buttonSize);
		setHeight(buttonSize);
	}
	
	void setTexture(String id) {
		texture = Game.getResourceManager().getTexture(id);
	}
	
	@Override
	protected void onDraw() {
		TextureDrawer.draw(texture, this);
	}
}
