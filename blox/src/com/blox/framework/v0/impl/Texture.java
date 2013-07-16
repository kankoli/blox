package com.blox.framework.v0.impl;

import com.blox.framework.v0.IDrawable;
import com.blox.framework.v0.IDrawer;
import com.blox.framework.v0.ITexture;

public abstract class Texture implements ITexture {
	private IDrawer drawer;

	protected Texture(IDrawer drawer) {
		this.drawer = drawer;
	}

	@Override
	public void draw(IDrawable drawable) {
		drawer.draw(drawable);
	}
}