package com.blox.framework.v0.impl.libgdx;

import com.badlogic.gdx.Gdx;
import com.blox.framework.v0.ICollisionDetectorFactory;
import com.blox.framework.v0.IDeltaTime;
import com.blox.framework.v0.IGameProvider;
import com.blox.framework.v0.IInputManager;
import com.blox.framework.v0.IResourceManager;
import com.blox.framework.v0.ISettings;
import com.blox.framework.v0.ITextureDrawer;
import com.blox.framework.v0.IVibrator;
import com.blox.framework.v0.forms.xml.IControlActionHandlerFactory;
import com.blox.framework.v0.impl.ActionHandlerFactory;
import com.blox.framework.v0.impl.CollisionDetectorFactory;

public class GdxGameProvider implements IGameProvider {
	public GdxGameProvider() {
		
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
	public ITextureDrawer createTextureDrawer() {
		return new GdxTextureDrawer();
	}

	@Override
	public IInputManager createInputManager() {
		return new GdxInputManager();
	}

	@Override
	public ISettings createSettings() {
		return new GdxSettings();
	}

	@Override
	public IVibrator createVibrator() {
		return new GdxVibrator();
	}

	@Override
	public ICollisionDetectorFactory createCollisionDetectorFactory() {
		return new CollisionDetectorFactory();
	}

	@Override
	public IControlActionHandlerFactory createActionHandlerFactory() {
		return new ActionHandlerFactory();
	}

	@Override
	public void exit() {
		Gdx.app.exit();
	}
}