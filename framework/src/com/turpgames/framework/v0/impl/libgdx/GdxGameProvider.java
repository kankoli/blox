package com.turpgames.framework.v0.impl.libgdx;

import com.badlogic.gdx.Gdx;
import com.turpgames.framework.v0.IDeltaTime;
import com.turpgames.framework.v0.IGameProvider;
import com.turpgames.framework.v0.IInputManager;
import com.turpgames.framework.v0.IResourceManager;
import com.turpgames.framework.v0.ISettings;
import com.turpgames.framework.v0.IShapeRenderer;
import com.turpgames.framework.v0.ITextureDrawer;
import com.turpgames.framework.v0.IVibrator;

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
	public IShapeRenderer createShapeRenderer() {
		return new GdxShapeRenderer();
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
	public void exit() {
		Gdx.app.exit();
	}
}