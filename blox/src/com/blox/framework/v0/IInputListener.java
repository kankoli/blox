package com.blox.framework.v0;

import com.blox.framework.v0.util.Vector;

public interface IInputListener {
	boolean touchDown(float x, float y, int pointer, int button);

	boolean touchUp(float x, float y, int pointer, int button);

	boolean touchDragged(float x, float y, int pointer);

	boolean tap(float x, float y, int count, int button);

	boolean longPress(float x, float y);

	boolean fling(float vx, float vy, int button);

	boolean pan(float x, float y, float dx, float xy);

	boolean zoom(float initialDistance, float distance);

	boolean pinch(Vector p1Start, Vector p2Start, Vector p1End, Vector p2End);

	boolean keyDown(int keycode);

	boolean keyUp(int keycode);

	boolean keyTyped(char character);

	boolean mouseMoved(float x, float y);

	boolean scrolled(float amount);
}
