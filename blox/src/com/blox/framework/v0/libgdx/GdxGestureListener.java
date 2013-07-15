package com.blox.framework.v0.libgdx;

import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.blox.framework.v0.Game;
import com.blox.framework.v0.IInputListener;
import com.blox.framework.v0.Vector;

class GdxGestureListener implements GestureListener {
	private IInputListener listener;

	GdxGestureListener(IInputListener listener) {
		this.listener = listener;
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		return listener.touchDown(x, Game.height - y, pointer, button);
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		return listener.tap(x, Game.height - y, count, button);
	}

	@Override
	public boolean longPress(float x, float y) {
		return listener.longPress(x, Game.height - y);
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		return listener.fling(velocityX, velocityY, button);
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		return listener.pan(x, Game.height - y, deltaX, deltaY);
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		return listener.zoom(initialDistance, distance);
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
			Vector2 pointer1, Vector2 pointer2) {
		Vector ip1 = new GdxVector(initialPointer1.x, Game.height - initialPointer1.y);
		Vector ip2 = new GdxVector(initialPointer2.x, Game.height - initialPointer2.y);
		Vector p1 = new GdxVector(pointer1.x, Game.height - pointer1.y);
		Vector p2 = new GdxVector(pointer2.x, Game.height - pointer2.y);

		return listener.pinch(ip1, ip2, p1, p2);
	}
}
