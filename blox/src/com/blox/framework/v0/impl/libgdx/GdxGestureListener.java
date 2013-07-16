package com.blox.framework.v0.impl.libgdx;

import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.blox.framework.v0.IInputListener;
import com.blox.framework.v0.util.ToolBox;
import com.blox.framework.v0.util.Vector;

class GdxGestureListener implements GestureListener {
	private IInputListener listener;

	GdxGestureListener(IInputListener listener) {
		this.listener = listener;
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		return listener.touchDown(ToolBox.scale(x), ToolBox.scale(ToolBox.screenHeight - y), pointer, button);
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		return listener.tap(ToolBox.scale(x), ToolBox.scale(ToolBox.screenHeight - y), count, button);
	}

	@Override
	public boolean longPress(float x, float y) {
		return listener.longPress(ToolBox.scale(x), ToolBox.scale(ToolBox.screenHeight - y));
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		return listener.fling(ToolBox.scale(velocityX), ToolBox.scale(velocityY), button);
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		return listener.pan(ToolBox.scale(x), ToolBox.scale(ToolBox.screenHeight - y), ToolBox.scale(deltaX), ToolBox.scale(deltaY));
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		return listener.zoom(ToolBox.scale(initialDistance), ToolBox.scale(distance));
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
			Vector2 pointer1, Vector2 pointer2) {
		Vector ip1 = new Vector(ToolBox.scale(initialPointer1.x), ToolBox.scale(ToolBox.screenHeight - initialPointer1.y), 0);
		Vector ip2 = new Vector(ToolBox.scale(initialPointer2.x), ToolBox.scale(ToolBox.screenHeight - initialPointer2.y), 0);
		Vector p1 = new Vector(ToolBox.scale(pointer1.x), ToolBox.scale(ToolBox.screenHeight - pointer1.y), 0);
		Vector p2 = new Vector(ToolBox.scale(pointer2.x), ToolBox.scale(ToolBox.screenHeight - pointer2.y), 0);

		return listener.pinch(ip1, ip2, p1, p2);
	}
}
