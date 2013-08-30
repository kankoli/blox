package com.blox.set.model;

import java.util.ArrayList;
import java.util.List;

import com.blox.framework.v0.IInputEventListener;
import com.blox.framework.v0.IInputListener;
import com.blox.framework.v0.impl.GameObject;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.Utils;
import com.blox.framework.v0.util.Vector;

public class CardGameObject extends GameObject implements IInputListener{
	
	protected boolean isActive;

	private List<IInputEventListener> inputEventListeners;
	
	public CardGameObject() {
		inputEventListeners = new ArrayList<IInputEventListener>();
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
	
	public void registerInputEventListener(IInputEventListener listener) {
		inputEventListeners.add(listener);
	}

	public void unregisterInputEventListener(IInputEventListener listener) {
		inputEventListeners.remove(listener);
	}
	
	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		if (Utils.isIn(x, y, location, width, height)) {
			for (int i = inputEventListeners.size()-1; i >= 0; i--) {
				inputEventListeners.get(i).onTouchDown(this, x, y, pointer, button);
			}
		}
		return false;
	}

	@Override
	public boolean touchUp(float x, float y, int pointer, int button) {
		if (Utils.isIn(x, y, location, width, height)) {
			for (int i = inputEventListeners.size()-1; i >= 0; i--) {
				inputEventListeners.get(i).onTouchUp(this, x, y, pointer, button);
			}
		}
		return false;
	}

	@Override
	public boolean touchDragged(float x, float y, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		if (Utils.isIn(x, y, location, width, height)) {
			for (int i = inputEventListeners.size()-1; i >= 0; i--) {
				inputEventListeners.get(i).onTap(this, x, y, count, button);
			}
		}
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		if (Utils.isIn(x, y, location, width, height)) {
			for (int i = inputEventListeners.size()-1; i >= 0; i--) {
				inputEventListeners.get(i).onLongPress(this, x, y);
			}
		}
		return false;
	}

	@Override
	public boolean fling(float vx, float vy, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pan(float x, float y, float dx, float xy) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pinch(Vector p1Start, Vector p2Start, Vector p1End, Vector p2End) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(float x, float y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(float amount) {
		// TODO Auto-generated method stub
		return false;
	}
}
