package com.blox.framework.v0;

import com.blox.framework.v0.util.Vector;

public interface IInputEventListener {
	
	void onTouchDown(IGameObject obj, float x, float y, int pointer, int button);

	void onTouchUp(IGameObject obj, float x, float y, int pointer, int button);

	void onTouchDragged(IGameObject obj, float x, float y, int pointer);

	void onTap(IGameObject obj, float x, float y, int count, int button);

	void onLongPress(IGameObject obj, float x, float y);

	void onFling(IGameObject obj, float vx, float vy, int button);

	void onPan(IGameObject obj, float x, float y, float dx, float xy);

	void onZoom(IGameObject obj, float initialDistance, float distance);

	void onPinch(IGameObject obj, Vector p1Start, Vector p2Start, Vector p1End, Vector p2End);

	void onKeyDown(IGameObject obj, int keycode);

	void onKeyUp(IGameObject obj, int keycode);

	void onKeyTyped(IGameObject obj, char character);

	void onMouseMoved(IGameObject obj, float x, float y);

	void onScrolled(IGameObject obj, float amount);
}
