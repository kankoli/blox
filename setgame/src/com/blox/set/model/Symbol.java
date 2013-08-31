package com.blox.set.model;

import com.blox.framework.v0.ITexture;
import com.blox.framework.v0.impl.GameObject;
import com.blox.framework.v0.util.TextureDrawer;
import com.blox.framework.v0.util.Vector;

public class Symbol extends GameObject {
	private ITexture texture;
	private Vector parentLocation;

	public Symbol(ITexture texture, Vector location, Vector parentLocation) {
		this.texture = texture;
		this.location = location;
		this.parentLocation = parentLocation;
		this.width = Card.SymbolWidth;
		this.height = Card.SymbolHeight;
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
