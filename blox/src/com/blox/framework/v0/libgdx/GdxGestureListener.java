package com.blox.framework.v0.libgdx;

import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.blox.framework.v0.IInputListener;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.Vector;

class GdxGestureListener implements GestureListener {
	private IInputListener listener;

	GdxGestureListener(IInputListener listener) {
		this.listener = listener;
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		return listener.touchDown(Game.scale(x), Game.scale(Game.height - y), pointer, button);
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		return listener.tap(Game.scale(x), Game.scale(Game.height - y), count, button);
	}

	@Override
	public boolean longPress(float x, float y) {
		return listener.longPress(Game.scale(x), Game.scale(Game.height - y));
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		return listener.fling(Game.scale(velocityX), Game.scale(velocityY), button);
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		return listener.pan(Game.scale(x), Game.scale(Game.height - y), Game.scale(deltaX), Game.scale(deltaY));
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		return listener.zoom(Game.scale(initialDistance), Game.scale(distance));
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
			Vector2 pointer1, Vector2 pointer2) {
		Vector ip1 = new Vector(Game.scale(initialPointer1.x), Game.scale(Game.height - initialPointer1.y), 0);
		Vector ip2 = new Vector(Game.scale(initialPointer2.x), Game.scale(Game.height - initialPointer2.y), 0);
		Vector p1 = new Vector(Game.scale(pointer1.x), Game.scale(Game.height - pointer1.y), 0);
		Vector p2 = new Vector(Game.scale(pointer2.x), Game.scale(Game.height - pointer2.y), 0);

		return listener.pinch(ip1, ip2, p1, p2);
	}
}
