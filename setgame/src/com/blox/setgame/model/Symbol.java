package com.blox.setgame.model;

import com.blox.framework.v0.ITexture;
import com.blox.framework.v0.impl.GameObject;
import com.blox.framework.v0.util.TextureDrawer;
import com.blox.framework.v0.util.Vector;
import com.blox.setgame.utils.R;

public class Symbol extends GameObject {
	private Vector parentLocation;
	private ITexture texture;

	public Symbol(ITexture texture, int colorAttribute, Vector location, Vector parentLocation) {
		this.texture = texture;
		this.location = location;
		this.parentLocation = parentLocation;
		this.width = Card.SymbolWidth;
		this.height = Card.SymbolHeight;

		if (colorAttribute == CardAttributes.Color_Red)
			this.color = R.colors.setRed;
		if (colorAttribute == CardAttributes.Color_Green)
			this.color = R.colors.setGreen;
		if (colorAttribute == CardAttributes.Color_Blue)
			this.color = R.colors.setBlue;
	}

	@Override
	public void draw() {
		TextureDrawer.draw(texture, this);
	}

	@Override
	public Vector getLocation() {
		return parentLocation.tmp().add(this.location);
	}
}
