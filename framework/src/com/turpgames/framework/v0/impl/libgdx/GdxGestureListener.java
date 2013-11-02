package com.turpgames.framework.v0.impl.libgdx;

import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.turpgames.framework.v0.IInputListener;
import com.turpgames.framework.v0.util.Vector;

class GdxGestureListener implements GestureListener {
	private IInputListener listener;

	GdxGestureListener(IInputListener listener) {
		this.listener = listener;
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		return listener.touchDown(x, y, pointer, button);
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		return listener.tap(x, y, count, button);
	}

	@Override
	public boolean longPress(float x, float y) {
		return listener.longPress(x, y);
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		return listener.fling(velocityX, velocityY, button);
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		return listener.pan(x, y, deltaX, deltaY);
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		return listener.zoom(initialDistance, distance);
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
		Vector ip1 = new Vector(initialPointer1.x, initialPointer1.y, 0);
		Vector ip2 = new Vector(initialPointer2.x, initialPointer2.y, 0);
		Vector p1 = new Vector(pointer1.x, pointer1.y, 0);
		Vector p2 = new Vector(pointer2.x, pointer2.y, 0);

		return listener.pinch(ip1, ip2, p1, p2);
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		return false;
	}
}
