package com.blox.framework.v0.impl;

import java.util.ArrayList;
import java.util.List;

import com.blox.framework.v0.ICompositeInputListener;
import com.blox.framework.v0.IInputListener;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.Vector;

public class CompositeInputListener implements ICompositeInputListener {

	private List<IInputListener> listeners;
	private boolean listening;

	public CompositeInputListener() {		
		listeners = new ArrayList<IInputListener>();
	}
	
	@Override
	public void register(IInputListener listener) {
		if (!listeners.contains(listener))
			listeners.add(listener);
	}

	@Override
	public void unregister(IInputListener listener) {
		listeners.remove(listener);
	}

	@Override
	public void activate() {
		if (!listening)
			Game.getInputManager().register(this);
		listening = true;
	}

	@Override
	public void deactivate() {
		if (listening)
			Game.getInputManager().unregister(this);
		listening = false;
	}

	@Override
	public boolean isActive() {
		return listening;
	}
	
	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		for (IInputListener listener : listeners) {
			if (listener.touchDown(x, y, pointer, button))
				return true;
		}
		return false;
	}

	@Override
	public boolean touchUp(float x, float y, int pointer, int button) {
		for (IInputListener listener : listeners) {
			if (listener.touchUp(x, y, pointer, button))
				return true;
		}
		return false;
	}

	@Override
	public boolean touchDragged(float x, float y, int pointer) {
		for (IInputListener listener : listeners) {
			if (listener.touchDragged(x, y, pointer))
				return true;
		}
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		for (IInputListener listener : listeners) {
			if (listener.tap(x, y, count, button))
				return true;
		}
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		for (IInputListener listener : listeners) {
			if (listener.longPress(x, y))
				return true;
		}
		return false;
	}

	@Override
	public boolean fling(float vx, float vy, int button) {
		for (IInputListener listener : listeners) {
			if (listener.fling(vx, vy, button))
				return true;
		}
		return false;
	}

	@Override
	public boolean pan(float x, float y, float dx, float xy) {
		for (IInputListener listener : listeners) {
			if (listener.pan(x, y, dx, xy))
				return true;
		}
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		for (IInputListener listener : listeners) {
			if (listener.zoom(initialDistance, distance))
				return true;
		}
		return false;
	}

	@Override
	public boolean pinch(Vector p1Start, Vector p2Start, Vector p1End, Vector p2End) {
		p1Start.y = p1Start.y;
		p2Start.y = p2Start.y;
		p1End.y = p1End.y;
		p2End.y = p2End.y;
		
		for (IInputListener listener : listeners) {
			if (listener.pinch(p1Start, p2Start, p1End, p2End))
				return true;
		}
		return false;
	}

	@Override
	public boolean mouseMoved(float x, float y) {
		for (IInputListener listener : listeners) {
			if (listener.mouseMoved(x, y))
				return true;
		}
		return false;
	}

	@Override
	public boolean scrolled(float amount) {
		for (IInputListener listener : listeners) {
			if (listener.scrolled(amount))
				return true;
		}
		return false;
	}

	@Override
	public boolean keyDown(int keycode) {
		for (IInputListener listener : listeners) {
			if (listener.keyDown(keycode))
				return true;
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		for (IInputListener listener : listeners) {
			if (listener.keyUp(keycode))
				return true;
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		for (IInputListener listener : listeners) {
			if (listener.keyTyped(character))
				return true;
		}
		return false;
	}
}
