package com.blox.framework.v0.impl.libgdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.blox.framework.v0.ICollisionDetectorFactory;
import com.blox.framework.v0.ICollisionManager;
import com.blox.framework.v0.IDeltaTime;
import com.blox.framework.v0.IDrawManager;
import com.blox.framework.v0.IGameFactory;
import com.blox.framework.v0.IInputManager;
import com.blox.framework.v0.IMoveManager;
import com.blox.framework.v0.IResourceManager;
import com.blox.framework.v0.ITextureSplitter;
import com.blox.framework.v0.impl.CollisionDetectorFactory;
import com.blox.framework.v0.impl.CollisionManager;
import com.blox.framework.v0.impl.DrawManager;
import com.blox.framework.v0.impl.MoveManager;

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

	@Override
	public ICollisionDetectorFactory createCollisionDetectorFactory() {
		return new CollisionDetectorFactory();
	}

	@Override
	public IDrawManager createDrawManager() {
		return new DrawManager();
	}

	@Override
	public IMoveManager createMoveManager() {
		return new MoveManager();
	}

	@Override
	public ICollisionManager createCollisionManager() {
		return new CollisionManager();
	}
}