package com.blox.framework.v0.impl;

import com.blox.framework.v0.ICollisionGroup;
import com.blox.framework.v0.ICollisionManager;
import com.blox.framework.v0.ICompositeInputListener;
import com.blox.framework.v0.IDrawer;
import com.blox.framework.v0.IDrawable;
import com.blox.framework.v0.IInputListener;
import com.blox.framework.v0.IMovable;
import com.blox.framework.v0.IMoveManager;
import com.blox.framework.v0.IScreen;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.GameMetadata;
import com.blox.framework.v0.util.ScreenMetadata;
import com.blox.framework.v0.util.Utils;
import com.blox.framework.v0.util.Vector;

public abstract class Screen implements IInputListener, IScreen {
	private String id;
	private IMoveManager moveManager;
	private IDrawer drawer;
	private ICollisionManager collisionManager;
	private ICompositeInputListener inputListener;

	private boolean hasInited;

	protected Screen() {
	}

	public static Screen load(String screenId) {
		ScreenMetadata metadata = GameMetadata.getScreen(screenId);
		if (metadata == null)
			return null;

		Screen screen = (Screen) Utils.createInstance(metadata.getScreenClass());
		screen.id = screenId;
		return screen;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void init() {
		if (hasInited)
			return;
		moveManager = Game.createMoveManager();
		drawer = Game.createDrawer();
		collisionManager = Game.createCollisionManager();
		inputListener = new CompositeInputListener();
		inputListener.register(this);
		hasInited = true;
	}

	@Override
	public void update() {
		move();
		collide();
	}

	@Override
	public void render() {
		draw();
	}

	@Override
	public void activated() {
		inputListener.activate();
	}

	@Override
	public void deactivated() {
		inputListener.deactivate();
	}

	protected final void move() {
		moveManager.move();
	}

	protected final void draw() {
		drawer.draw();
	}

	protected final void collide() {
		collisionManager.collide();
	}

	public final void registerMovable(IMovable obj) {
		moveManager.register(obj);
	}

	public final void unregisterMovable(IMovable obj) {
		moveManager.unregister(obj);
	}

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
