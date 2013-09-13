package com.blox.setgame.model;

import com.blox.framework.v0.ITexture;
import com.blox.framework.v0.impl.GameObject;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.TextureDrawer;
import com.blox.setgame.utils.R;

public class Logo extends GameObject {
	private static final float logoSize = 500f;

	private ITexture logo;

	public Logo() {
		logo = Game.getResourceManager().getTexture(R.game.textures.logo);

		setWidth(logoSize);
		setHeight(logoSize);
		getLocation().set(25f, 50f + (Game.getVirtualHeight() - logoSize) / 2f);
	}

	@Override
	public void draw() {
		TextureDrawer.draw(logo, this);
	}
}
