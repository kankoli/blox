package com.blox.framework.v0.impl.libgdx;

import com.badlogic.gdx.Gdx;
import com.blox.framework.v0.IActionHandlerFactory;
import com.blox.framework.v0.ICollisionDetectorFactory;
import com.blox.framework.v0.IDeltaTime;
import com.blox.framework.v0.IFontFactory;
import com.blox.framework.v0.IGameProvider;
import com.blox.framework.v0.IInputManager;
import com.blox.framework.v0.IResourceManager;
import com.blox.framework.v0.ITextureSplitter;
import com.blox.framework.v0.impl.ActionHandlerFactory;
import com.blox.framework.v0.impl.CachedResourceManager;
import com.blox.framework.v0.impl.CollisionDetectorFactory;

public class GdxGameProvider implements IGameProvider {
	public GdxGameProvider() {
		
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
	public IFontFactory createFontFactory() {
		return new GdxFontFactory();
	}

	@Override
	public IActionHandlerFactory createActionHandlerFactory() {
		return new ActionHandlerFactory();
	}

	@Override
	public void exit() {
		Gdx.app.exit();
	}
}