package com.blox.setgame.model;

import com.blox.framework.v0.ITexture;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.TextureDrawer;
import com.blox.setgame.utils.R;

public class SetGameImageButton extends SetGameButton {	
	private ITexture texture;
	
	SetGameImageButton() {
		super();
		setWidth(R.ui.imageButtonSize);
		setHeight(R.ui.imageButtonSize);
	}
	
	void setTexture(String id) {
		texture = Game.getResourceManager().getTexture(id);
	}
	
	@Override
	protected void onDraw() {
		TextureDrawer.draw(texture, this);
	}
}
