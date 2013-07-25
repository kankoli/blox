package com.blox.framework.v0.impl.libgdx;

import com.badlogic.gdx.Gdx;
import com.blox.framework.v0.ICollisionDetectorFactory;
import com.blox.framework.v0.ICollisionManager;
import com.blox.framework.v0.IDeltaTime;
import com.blox.framework.v0.IDisposeManager;
import com.blox.framework.v0.IDrawManager;
import com.blox.framework.v0.IGameProvider;
import com.blox.framework.v0.IInputManager;
import com.blox.framework.v0.IMoveManager;
import com.blox.framework.v0.IResourceManager;
import com.blox.framework.v0.IScreenFader;
import com.blox.framework.v0.ITextDrawer;
import com.blox.framework.v0.ITextureSplitter;
import com.blox.framework.v0.impl.CachedResourceManager;
import com.blox.framework.v0.impl.CollisionDetectorFactory;
import com.blox.framework.v0.impl.CollisionManager;
import com.blox.framework.v0.impl.DisposeManager;
import com.blox.framework.v0.impl.DrawManager;
import com.blox.framework.v0.impl.MoveManager;

class GdxGameProvider implements IGameProvider {
	GdxGameProvider() {

	}

	@Override
	public IResourceManager createResourceManager() {
		return new CachedResourceManager(new GdxResourceManager());
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

	@Override
	public IScreenFader createScreenFader() {
		return new GdxScreenFader();
	}

	@Override
	public ITextDrawer createTextDrawer() {
		return new GdxTextDrawer();
	}

	@Override
	public IDisposeManager createDisposeManager() {
		return new DisposeManager();
	}

	@Override
	public void exit() {
		Gdx.app.exit();
	}
}