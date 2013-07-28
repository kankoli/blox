package com.blox.framework.v0.impl;

import com.blox.framework.v0.ICollisionGroup;
import com.blox.framework.v0.ICollisionManager;
import com.blox.framework.v0.IDrawManager;
import com.blox.framework.v0.IDrawable;
import com.blox.framework.v0.IInputListener;
import com.blox.framework.v0.IInputManager;
import com.blox.framework.v0.IMovable;
import com.blox.framework.v0.IMoveManager;
import com.blox.framework.v0.IScreen;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.Vector;

public abstract class Screen implements IInputListener, IScreen {
	private IMoveManager moveManager;
	private IDrawManager drawManager;
	private ICollisionManager collisionManager;
	private IInputManager inputManager;

	protected Screen() {
	}

	@Override
	public void init() {
		moveManager = Game.getProvider().createMoveManager();
		drawManager = Game.getProvider().createDrawManager();
		collisionManager = Game.getProvider().createCollisionManager();
		inputManager = Game.getProvider().createInputManager();
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
		inputManager.activate();
	}

	@Override
	public void deactivated() {
		inputManager.deactivate();
	}

	protected final void move() {
		moveManager.move();
	}

	protected final void draw() {
		drawManager.draw();
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
		drawManager.register(obj, layer);
	}

	public final void unregisterDrawable(IDrawable obj) {
		drawManager.unregister(obj);
	}

	public final void registerCollisionGroup(ICollisionGroup obj) {
		collisionManager.register(obj);
	}

	public final void unregisterCollisionGroup(ICollisionGroup obj) {
		collisionManager.unregister(obj);
	}

	public final void registerInputListener(IInputListener obj) {
		inputManager.register(obj);
	}

	public final void unregisterInputListener(IInputListener obj) {
		inputManager.unregister(obj);
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
