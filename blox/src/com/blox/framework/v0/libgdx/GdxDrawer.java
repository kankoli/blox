package com.blox.framework.v0.libgdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.blox.framework.v0.IDrawable;
import com.blox.framework.v0.IDrawer;

public class GdxDrawer implements IDrawer {
	private SpriteBatch spriteBatch;
	
	public GdxDrawer(SpriteBatch spriteBatch) {
		this.spriteBatch = spriteBatch;
	}

	@Override
	public void draw(IDrawable drawable) {
		spriteBatch.draw(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9)
	}

	@Override
	public void dispose() {
		spriteBatch.dispose();
	}	
}
