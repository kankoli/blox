package com.blox.framework.v0.libgdx;

import com.badlogic.gdx.InputProcessor;
import com.blox.framework.v0.Game;
import com.blox.framework.v0.IInputListener;

class GdxInputProcessor implements InputProcessor {
	private IInputListener listener;

	GdxInputProcessor(IInputListener listener) {
		this.listener = listener;
	}

	@Override
	public boolean keyDown(int keycode) {
		return listener.keyDown(keycode);
	}

	@Override
	public boolean keyUp(int keycode) {
		return listener.keyUp(keycode);
	}

	@Override
	public boolean keyTyped(char character) {
		return listener.keyTyped(character);
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// GestureListenerdaki float'l� versiyon �al��s�n
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return listener.touchUp(screenX, Game.height - screenY, pointer, button);
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return listener.touchDragged(screenX, Game.height - screenY, pointer);
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return listener.mouseMoved(screenX, Game.height - screenY);
	}

	@Override
	public boolean scrolled(int amount) {
		return listener.scrolled(amount);
	}
}
