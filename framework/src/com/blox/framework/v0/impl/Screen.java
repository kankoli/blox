package com.blox.framework.v0.impl;

import com.badlogic.gdx.Input.Keys;
import com.blox.framework.v0.ICollisionGroup;
import com.blox.framework.v0.ICompositeInputListener;
import com.blox.framework.v0.IDrawable;
import com.blox.framework.v0.IDrawingInfo;
import com.blox.framework.v0.IInputListener;
import com.blox.framework.v0.IMusic;
import com.blox.framework.v0.ISettingsChangeListener;
import com.blox.framework.v0.ITexture;
import com.blox.framework.v0.IView;
import com.blox.framework.v0.metadata.GameMetadata;
import com.blox.framework.v0.metadata.ScreenMetadata;
import com.blox.framework.v0.util.Drawer;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.TextureDrawer;
import com.blox.framework.v0.util.Utils;
import com.blox.framework.v0.util.Vector;

public abstract class Screen implements IInputListener, IView {
	private String id;
	private MoveManager moveManager;
	private Drawer drawer;
	private CollisionManager collisionManager;
	private ICompositeInputListener inputListener;
	private ITexture background;
	private IMusic music;

	private boolean hasInited;
	private boolean isActive;
	
	private final ISettingsChangeListener settingsListener = new ISettingsChangeListener() {
		@Override
		public void settingChanged(String key, Object newValue) {
			if ("music".equals(key)) {
				if (newValue.equals(false))
					disposeBgMusic();
				else
					startPlayingBgMusic();
			}
		}
	};

	protected Screen() {
	}

	public static Screen load(String screenId) {
		ScreenMetadata metadata = GameMetadata.getScreen(screenId);
		if (metadata == null)
			return null;

		Screen screen = (Screen) Utils.createInstance(metadata.getScreenClass());
		screen.id = screenId;

		String bg = metadata.getBackgroundTextureId();
		if (!Utils.isNullOrWhitespace(bg))
			screen.background = Game.getResourceManager().getTexture(bg);

		return screen;
	}

	@Override
	public String getId() {
		return id;
	}

	public void init() {
		if (hasInited)
			return;
		drawer = new Drawer();
		moveManager = new MoveManager();
		collisionManager = new CollisionManager();
		inputListener = new CompositeInputListener();
		inputListener.register(this);
		hasInited = true;
	}

	public void update() {
		moveManager.execute();
		collisionManager.execute();
	}

	@Override
	public void render() {
		if (background != null)
			TextureDrawer.draw(background, IDrawingInfo.screen);
		drawer.draw();
	}

	@Override
	public void activated() {
		inputListener.activate();
		startPlayingBgMusic();
		Settings.registerListener(settingsListener);
		MoveManager.setCurrent(moveManager);
		isActive = true;
	}

	@Override
	public void deactivated() {
		inputListener.deactivate();
		disposeBgMusic();
		Settings.unregisterListener(settingsListener);
		MoveManager.setCurrent(null);
		isActive = false;
	}
	
	public boolean isActive() {
		return isActive;
	}

	protected void startPlayingBgMusic() {
		disposeBgMusic();
		if (!Settings.isMusicOn())
			return;

		ScreenMetadata meta = GameMetadata.getScreen(id);
		String musicId = meta.getBackgroundMusicId();

		if (Utils.isNullOrWhitespace(musicId))
			return;

		music = Game.getResourceManager().getMusic(musicId);
		music.play();
	}

	protected void disposeBgMusic() {
		if (music != null) {
			music.stop();
			music.dispose();
			music = null;
		}
	}

	protected boolean onBack() {
		return false;
	}
	
//	public final void registerMovable(IMovable obj) {
//		moveManager.register(obj);
//	}
//
//	public final void unregisterMovable(IMovable obj) {
//		moveManager.unregister(obj);
//	}

	public final void registerDrawable(IDrawable obj, int layer) {
		drawer.register(obj, layer);
	}

	public final void unregisterDrawable(IDrawable obj) {
		drawer.unregister(obj);
	}

	public final void registerCollisionGroup(ICollisionGroup obj) {
		collisionManager.register(obj);
	}

	public final void unregisterCollisionGroup(ICollisionGroup obj) {
		collisionManager.unregister(obj);
	}

	public final void registerInputListener(IInputListener obj) {
		inputListener.register(obj);
	}

	public final void unregisterInputListener(IInputListener obj) {
		inputListener.unregister(obj);
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(float x, float y, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(float x, float y, int pointer) {
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		return false;
	}

	@Override
	public boolean fling(float vx, float vy, int button) {
		return false;
	}

	@Override
	public boolean pan(float x, float y, float dx, float xy) {
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		return false;
	}

	@Override
	public boolean pinch(Vector p1Start, Vector p2Start, Vector p1End, Vector p2End) {
		return false;
	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.BACK || keycode == Keys.ESCAPE) {
			if (onBack()) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean mouseMoved(float x, float y) {
		return false;
	}

	@Override
	public boolean scrolled(float amount) {
		return false;
	}
}
