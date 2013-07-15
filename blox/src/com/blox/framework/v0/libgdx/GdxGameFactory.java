package com.blox.framework.v0.libgdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.blox.framework.v0.IDeltaTime;
import com.blox.framework.v0.IGameFactory;
import com.blox.framework.v0.IInputManager;
import com.blox.framework.v0.IResourceManager;
import com.blox.framework.v0.ITextureSplitter;

public class GdxGameFactory implements IGameFactory {
	public GdxGameFactory(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
		GdxGame.spriteBatch = spriteBatch;
		GdxGame.shapeRenderer = shapeRenderer;
	}
	
	@Override
	public IResourceManager createResourceManager() {
		return new GdxResourceManager();
	}

	@Override
	public IDeltaTime createDeltaTime() {
		return new GdxDeltaTime();
	}

	@Override
	public ITextureSplitter createTextureSplitter() {
		return new GdxTextureSplitter();
	}

	@Override
	public IInputManager createInputManager() {
		return new GdxInputManager();
	}
}