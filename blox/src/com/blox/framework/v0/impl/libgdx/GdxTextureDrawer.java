package com.blox.framework.v0.impl.libgdx;

import com.badlogic.gdx.graphics.Texture;
import com.blox.framework.v0.IDrawable;
import com.blox.framework.v0.IDrawer;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.Rotation;
import com.blox.framework.v0.util.Vector;

class GdxTextureDrawer implements IDrawer {
	private final static GdxTextureDrawer instance = new GdxTextureDrawer();
	
	private GdxTextureDrawer() {
		
	}
	
	static GdxTextureDrawer getInstance() {
		return instance;
	}
	
	private Texture texture;

	void setTexture(Texture texture) {
		this.texture = texture;
	}
	
	@Override
	public void draw(IDrawable drawable) {
		Vector l = drawable.getLocation();
		Rotation r = drawable.getRotation();
		float width = drawable.getWidth();
		float height = drawable.getHeight();
		Vector s = drawable.getScale();
		boolean flipX = drawable.isFlipX();
		boolean flipY = drawable.isFlipY();

		GdxGame.spriteBatch
				.draw(texture, Game.world.descale(l.x), Game.world.descale(l.y),
						Game.world.descale(r.origin.x-l.x), Game.world.descale(r.origin.y-l.y),
						Game.world.descale(width), Game.world.descale(height), s.x,
						s.y, Game.world.descale(r.rotation.z), 0, 0,
						(int) Game.world.descale(width), (int) Game.world.descale(height),
						flipX, flipY);
	}
}
