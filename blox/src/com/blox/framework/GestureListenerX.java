package com.blox.framework;

import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;

public class GestureListenerX implements GestureListener {

	private GestureListener parent;
	
	public GestureListenerX() {
		
	}

	public void setParent(GestureListener parent) {
		this.parent = parent;
	}
	public GestureListener getParent() {
		return parent;
	}
	
	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		// parent.touchDown(x, y, pointer, button);
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		parent.tap(x, y, count, button);
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		parent.longPress(x, y);
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		parent.fling(velocityX, velocityY, button);
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		parent.pan(x, y, deltaX, deltaY);
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		parent.zoom(initialDistance, distance);
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
			Vector2 pointer1, Vector2 pointer2) {
		parent.pinch(initialPointer1, initialPointer2, pointer1, pointer2);
		return false;
	}
	
}
