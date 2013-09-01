package com.blox.setgame.model;

import com.blox.framework.v0.IInputListener;
import com.blox.framework.v0.impl.GameObject;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.Vector;

public class CardGameObject extends GameObject implements IInputListener {

	protected boolean isActive;

	public CardGameObject() {

	}

	public void activate() {
		Game.getInputManager().register(this);
		isActive = true;
	}

	public void deactivate() {
		Game.getInputManager().unregister(this);
		isActive = false;
	}

	public boolean isActive() {
		return isActive;
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
