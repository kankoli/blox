package com.blox.framework.v0.libgdx;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.input.GestureDetector;
import com.blox.framework.v0.IInputListener;
import com.blox.framework.v0.IInputManager;
import com.blox.framework.v0.Vector;

class GdxInputManager implements IInputManager, IInputListener {
	private List<IInputListener> listeners;

	GdxInputManager() {
		listeners = new ArrayList<IInputListener>();
		
		InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(new GdxInputProcessor(this));
		multiplexer.addProcessor(new GestureDetector(new GdxGestureListener(this)));
		Gdx.input.setInputProcessor(multiplexer);
	}

	@Override
    public void register(IInputListener listener) {
		listeners.add(listener);
	}

	@Override
    public void unregister(IInputListener listener) {
		listeners.remove(listener);
	}
	
	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		for(IInputListener listener : listeners) {
			if (listener.touchDown(x, y, pointer, button))
				return true;
		}
		return false;
	}

	@Override
	public boolean touchUp(float x, float y, int pointer, int button) {
		for(IInputListener listener : listeners) {
			if (listener.touchUp(x, y, pointer, button))
				return true;
		}
		return false;
	}

	@Override
	public boolean touchDragged(float x, float y, int pointer) {
		for(IInputListener listener : listeners) {
			if (listener.touchDragged(x, y, pointer))
				return true;
		}
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		for(IInputListener listener : listeners) {
			if (listener.tap(x, y, count, button))
				return true;
		}
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		for(IInputListener listener : listeners) {
			if (listener.longPress(x, y))
				return true;
		}
		return false;
	}

	@Override
	public boolean fling(float vx, float vy, int button) {
		for(IInputListener listener : listeners) {
			if (listener.fling(vx, vy, button))
				return true;
		}
		return false;
	}

	@Override
	public boolean pan(float x, float y, float dx, float xy) {
		for(IInputListener listener : listeners) {
			if (listener.pan(x, y, dx, xy))
				return true;
		}
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		for(IInputListener listener : listeners) {
			if (listener.zoom(initialDistance, distance))
				return true;
		}
		return false;
	}

	@Override
	public boolean pinch(Vector p1Start, Vector p2Start, Vector p1End, Vector p2End) {
		for(IInputListener listener : listeners) {
			if (listener.pinch(p1Start, p2Start, p1End, p2End))
				return true;
		}
		return false;
	}

	@Override
	public boolean mouseMoved(float x, float y) {
		for(IInputListener listener : listeners) {
			if (listener.mouseMoved(x, y))
				return true;
		}
		return false;
	}

	@Override
	public boolean scrolled(float amount) {
		for(IInputListener listener : listeners) {
			if (listener.scrolled(amount))
				return true;
		}
		return false;
	}

	@Override
	public boolean keyDown(int keycode) {
		for(IInputListener listener : listeners) {
			if (listener.keyDown(keycode))
				return true;
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		for(IInputListener listener : listeners) {
			if (listener.keyUp(keycode))
				return true;
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		for(IInputListener listener : listeners) {
			if (listener.keyTyped(character))
				return true;
		}
		return false;
	}
}
