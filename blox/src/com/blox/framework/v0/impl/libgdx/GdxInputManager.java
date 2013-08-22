package com.blox.framework.v0.impl.libgdx;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.input.GestureDetector;
import com.blox.framework.v0.IInputListener;
import com.blox.framework.v0.IInputManager;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.Vector;

class GdxInputManager implements IInputManager, IInputListener {
	private List<IInputListener> listeners;
	private InputMultiplexer multiplexer;
	private boolean listening;

	GdxInputManager() {
		listeners = new ArrayList<IInputListener>();

		multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(new GdxInputProcessor(this));
		multiplexer.addProcessor(new GestureDetector(new GdxGestureListener(this)));
		
	}

	@Override
	public void activate() {
		Gdx.input.setInputProcessor(multiplexer);
		listening = true;
	}

	@Override
	public void deactivate() {
		Gdx.input.setInputProcessor(null);
		listening = false;
	}

	@Override
	public boolean isActive() {
		return listening;
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
	public float getX() {
		return Game.screenToViewportX(Gdx.input.getX());
	}

	@Override
	public float getY() {
		return Game.screenToViewportY(Game.getScreenHeight() - Gdx.input.getY());
	}

	@Override
	public boolean isTouched() {
		return Gdx.input.isTouched();
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		x = Game.screenToViewportX(x);
		y = Game.screenToViewportY(Game.getScreenHeight() - y);
		for (int i = listeners.size() - 1; i >= 0; i--) {
			IInputListener listener = listeners.get(i);
		
			if (listener.touchDown(x, y, pointer, button))
				return true;
		}
		return false;
	}

	@Override
	public boolean touchUp(float x, float y, int pointer, int button) {
		x = Game.screenToViewportX(x);
		y = Game.screenToViewportY(Game.getScreenHeight() - y);
		for (int i = listeners.size() - 1; i >= 0; i--) {
			IInputListener listener = listeners.get(i);
			if (listener.touchUp(x, y, pointer, button))
				return true;
		}
		return false;
	}

	@Override
	public boolean touchDragged(float x, float y, int pointer) {
		x = Game.screenToViewportX(x);
		y = Game.screenToViewportY(Game.getScreenHeight() - y);
		for (int i = listeners.size() - 1; i >= 0; i--) {
			IInputListener listener = listeners.get(i);
			if (listener.touchDragged(x, y, pointer))
				return true;
		}
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		x = Game.screenToViewportX(x);
		y = Game.screenToViewportY(Game.getScreenHeight() - y);
		for (int i = listeners.size() - 1; i >= 0; i--) {
			IInputListener listener = listeners.get(i);
			if (listener.tap(x, y, count, button))
				return true;
		}
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		x = Game.screenToViewportX(x);
		y = Game.screenToViewportY(Game.getScreenHeight() - y);
		for (int i = listeners.size() - 1; i >= 0; i--) {
			IInputListener listener = listeners.get(i);
			if (listener.longPress(x, y))
				return true;
		}
		return false;
	}

	@Override
	public boolean fling(float vx, float vy, int button) {
		vx = Game.screenToViewportX(vx);
		vy = Game.screenToViewportY(vy);
		for (int i = listeners.size() - 1; i >= 0; i--) {
			IInputListener listener = listeners.get(i);
			if (listener.fling(vx, vy, button))
				return true;
		}
		return false;
	}

	@Override
	public boolean pan(float x, float y, float dx, float xy) {
		x = Game.screenToViewportX(x);
		y = Game.screenToViewportY(Game.getScreenHeight() - y);
		for (int i = listeners.size() - 1; i >= 0; i--) {
			IInputListener listener = listeners.get(i);
			if (listener.pan(x, y, dx, xy))
				return true;
		}
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		for (int i = listeners.size() - 1; i >= 0; i--) {
			IInputListener listener = listeners.get(i);
			if (listener.zoom(Game.scale(initialDistance), Game.scale(distance)))
				return true;
		}
		return false;
	}

	@Override
	public boolean pinch(Vector p1Start, Vector p2Start, Vector p1End, Vector p2End) {
		p1Start.y = Game.getScreenHeight() - p1Start.y;
		p2Start.y = Game.getScreenHeight() - p2Start.y;
		p1End.y = Game.getScreenHeight() - p1End.y;
		p2End.y = Game.getScreenHeight() - p2End.y;

		Game.screenToViewport(p1Start);
		Game.screenToViewport(p2Start);
		Game.screenToViewport(p1End);
		Game.screenToViewport(p2End);

		for (int i = listeners.size() - 1; i >= 0; i--) {
			IInputListener listener = listeners.get(i);
			if (listener.pinch(p1Start, p2Start, p1End, p2End))
				return true;
		}
		return false;
	}

	@Override
	public boolean mouseMoved(float x, float y) {
		x = Game.screenToViewportX(x);
		y = Game.screenToViewportY(Game.getScreenHeight() - y);
		for (int i = listeners.size() - 1; i >= 0; i--) {
			IInputListener listener = listeners.get(i);
			if (listener.mouseMoved(x, y))
				return true;
		}
		return false;
	}

	@Override
	public boolean scrolled(float amount) {
		for (int i = listeners.size() - 1; i >= 0; i--) {
			IInputListener listener = listeners.get(i);
			if (listener.scrolled(amount))
				return true;
		}
		return false;
	}

	@Override
	public boolean keyDown(int keycode) {
		for (int i = listeners.size() - 1; i >= 0; i--) {
			IInputListener listener = listeners.get(i);
			if (listener.keyDown(keycode))
				return true;
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		for (int i = listeners.size() - 1; i >= 0; i--) {
			IInputListener listener = listeners.get(i);
			if (listener.keyUp(keycode))
				return true;
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		for (int i = listeners.size() - 1; i >= 0; i--) {
			IInputListener listener = listeners.get(i);
			if (listener.keyTyped(character))
				return true;
		}
		return false;
	}
}
