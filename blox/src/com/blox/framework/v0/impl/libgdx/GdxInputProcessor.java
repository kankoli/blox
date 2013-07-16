package com.blox.framework.v0.impl.libgdx;

import com.badlogic.gdx.InputProcessor;
import com.blox.framework.v0.IInputListener;
import com.blox.framework.v0.util.ToolBox;

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
		// GestureListenerdaki float'lý versiyon çalýþsýn
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return listener.touchUp(screenX, ToolBox.screenHeight - screenY, pointer, button);
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return listener.touchDragged(screenX, ToolBox.screenHeight - screenY, pointer);
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return listener.mouseMoved(screenX, ToolBox.screenHeight - screenY);
	}

	@Override
	public boolean scrolled(int amount) {
		return listener.scrolled(amount);
	}
}
