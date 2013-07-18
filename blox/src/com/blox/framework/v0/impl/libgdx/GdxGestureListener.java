package com.blox.framework.v0.impl.libgdx;

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
		return listener.touchDown(Game.world.scale(x), Game.world.scale(Game.world.screenHeight - y), pointer, button);
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		return listener.tap(Game.world.scale(x), Game.world.scale(Game.world.screenHeight - y), count, button);
	}

	@Override
	public boolean longPress(float x, float y) {
		return listener.longPress(Game.world.scale(x), Game.world.scale(Game.world.screenHeight - y));
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		return listener.fling(Game.world.scale(velocityX), Game.world.scale(velocityY), button);
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		return listener.pan(Game.world.scale(x), Game.world.scale(Game.world.screenHeight - y), Game.world.scale(deltaX), Game.world.scale(deltaY));
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		return listener.zoom(Game.world.scale(initialDistance), Game.world.scale(distance));
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
			Vector2 pointer1, Vector2 pointer2) {
		Vector ip1 = new Vector(Game.world.scale(initialPointer1.x), Game.world.scale(Game.world.screenHeight - initialPointer1.y), 0);
		Vector ip2 = new Vector(Game.world.scale(initialPointer2.x), Game.world.scale(Game.world.screenHeight - initialPointer2.y), 0);
		Vector p1 = new Vector(Game.world.scale(pointer1.x), Game.world.scale(Game.world.screenHeight - pointer1.y), 0);
		Vector p2 = new Vector(Game.world.scale(pointer2.x), Game.world.scale(Game.world.screenHeight - pointer2.y), 0);

		return listener.pinch(ip1, ip2, p1, p2);
	}
}
