package com.blox.framework.v0.impl.libgdx;

import com.badlogic.gdx.Gdx;
import com.blox.framework.v0.IActionHandlerFactory;
import com.blox.framework.v0.ICollisionDetectorFactory;
import com.blox.framework.v0.ICollisionManager;
import com.blox.framework.v0.IDeltaTime;
import com.blox.framework.v0.IDisposeManager;
import com.blox.framework.v0.IDrawer;
import com.blox.framework.v0.IDrawerManager;
import com.blox.framework.v0.IFontFactory;
import com.blox.framework.v0.IGameProvider;
import com.blox.framework.v0.IInputManager;
import com.blox.framework.v0.IMoveManager;
import com.blox.framework.v0.IResourceManager;
import com.blox.framework.v0.ITextureSplitter;
import com.blox.framework.v0.impl.ActionHandlerFactory;
import com.blox.framework.v0.impl.CachedResourceManager;
import com.blox.framework.v0.impl.CollisionDetectorFactory;
import com.blox.framework.v0.impl.CollisionManager;
import com.blox.framework.v0.impl.DisposeManager;
import com.blox.framework.v0.impl.Drawer;
import com.blox.framework.v0.impl.DrawerManager;
import com.blox.framework.v0.impl.MoveManager;

class GdxGameProvider implements IGameProvider {
	private String gameXml;

	GdxGameProvider(String gameXml) {
		this.gameXml = gameXml;
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
	public IDrawer createDrawer() {
		return new Drawer();
	}

	@Override
	public IDrawerManager createDrawerManager() {
		return new DrawerManager();
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
	public IFontFactory createFontFactory() {
		return new GdxFontFactory();
	}

	@Override
	public IDisposeManager createDisposeManager() {
		return new DisposeManager();
	}

	@Override
	public IActionHandlerFactory createActionHandlerFactory() {
		return new ActionHandlerFactory();
	}

	@Override
	public String getMetadataFile() {
		return gameXml;
	}

	@Override
	public void exit() {
		Gdx.app.exit();
	}
}