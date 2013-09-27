package com.blox.setgame.model;

import com.blox.framework.v0.ITexture;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.TextureDrawer;
import com.blox.framework.v0.util.Utils;
import com.blox.setgame.utils.R;

public class SetGameImageButton extends SetGameButton {
	private final static float buttonSize = 48;
	
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
	public void draw() {
		if (Game.getInputManager().isTouched() && Utils.isIn(Game.getInputManager().getX(), Game.getInputManager().getY(), this))
			getColor().set(R.colors.setBlue);
		else
			getColor().set(1);
		TextureDrawer.draw(texture, this);
	}
}
