package com.blox.setgame.model;

import com.blox.framework.v0.ITexture;
import com.blox.framework.v0.impl.GameObject;
import com.blox.framework.v0.util.Rotation;
import com.blox.framework.v0.util.TextureDrawer;
import com.blox.framework.v0.util.Vector;
import com.blox.setgame.utils.R;

public class Symbol extends GameObject {
	private Card parent;
	private ITexture texture;

	public Symbol(ITexture texture, int colorAttribute, Vector location, Card parent) {
		this.texture = texture;
		this.parent = parent;

		setWidth(Card.SymbolWidth);
		setHeight(Card.SymbolHeight);

		super.getLocation().set(location);

		if (colorAttribute == CardAttributes.Color_Red)
			getColor().set(R.colors.setRed);
		if (colorAttribute == CardAttributes.Color_Green)
			getColor().set(R.colors.setGreen);
		if (colorAttribute == CardAttributes.Color_Blue)
			getColor().set(R.colors.setBlue);
	}

	@Override
	public void draw() {
		TextureDrawer.draw(texture, this);
	}

	@Override
	public Vector getLocation() {
		return parent.getLocation().tmp().add(super.getLocation());
	}
	
	@Override
	public Vector getScale() {
		return parent.getScale();
	}
	
	@Override
	public Rotation getRotation() {
		return parent.getRotation();
	}
}
