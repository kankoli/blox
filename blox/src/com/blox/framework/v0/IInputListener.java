package com.blox.framework.v0;

public interface IInputListener {
	public boolean touchDown(float x, float y, int pointer, int button);

	public boolean touchUp(float x, float y, int pointer, int button);

	public boolean touchDragged(float x, float y, int pointer);
	
	public boolean tap(float x, float y, int count, int button);

	public boolean longPress(float x, float y);

	public boolean fling(float vx, float vy, int button);

	public boolean pan(float x, float y, float dx, float xy);

	public boolean zoom(float initialDistance, float distance);

	public boolean pinch(Vector p1Start, Vector p2Start, Vector p1End, Vector p2End);

	public boolean keyDown(int keycode);

	public boolean keyUp(int keycode);

	public boolean keyTyped(char character);

	public boolean mouseMoved(float x, float y);

	public boolean scrolled(float amount);
}
