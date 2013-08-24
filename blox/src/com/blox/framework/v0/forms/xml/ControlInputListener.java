package com.blox.framework.v0.forms.xml;

import java.util.ArrayList;
import java.util.List;

import com.blox.framework.v0.impl.CompositeInputListener;

class ControlInputListener extends CompositeInputListener {
	private List<DrawableControl> controls;

	public ControlInputListener() {
		controls = new ArrayList<DrawableControl>();
	}

	void register(DrawableControl control) {
		if (!controls.contains(control))
			controls.add(control);
	}

	void unregister(DrawableControl control) {
		controls.remove(control);
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		for (int i = controls.size() - 1; i >= 0; i--) {
			DrawableControl control = controls.get(i);
			if (control.isIn(x, y))
				control.onTouchDown();
		}
		return false;
	}

	@Override
	public boolean touchUp(float x, float y, int pointer, int button) {
		for (int i = controls.size() - 1; i >= 0; i--) {
			DrawableControl control = controls.get(i);
			if (control.isIn(x, y))
				control.onTouchUp();
		}
		return false;
	}

	@Override
	public boolean touchDragged(float x, float y, int pointer) {
		for (int i = controls.size() - 1; i >= 0; i--) {
			DrawableControl control = controls.get(i);
			if (control.isIn(x, y))
				control.onTouchDragged();
		}
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		for (int i = controls.size() - 1; i >= 0; i--) {
			DrawableControl control = controls.get(i);
			if (control.isIn(x, y))
				control.onTap();
		}
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		for (int i = controls.size() - 1; i >= 0; i--) {
			DrawableControl control = controls.get(i);
			if (control.isIn(x, y))
				control.onLongPress();
		}
		return false;
	}

	@Override
	public boolean keyDown(int keycode) {
		for (int i = controls.size() - 1; i >= 0; i--) {
			DrawableControl control = controls.get(i);
			control.onKeyDown(keycode);
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		for (int i = controls.size() - 1; i >= 0; i--) {
			DrawableControl control = controls.get(i);
			control.onKeyUp(keycode);
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		for (int i = controls.size() - 1; i >= 0; i--) {
			DrawableControl control = controls.get(i);
			control.onKeyTyped(character);
		}
		return false;
	}

	@Override
	public boolean mouseMoved(float x, float y) {
		for (int i = controls.size() - 1; i >= 0; i--) {
			DrawableControl control = controls.get(i);
			control.onMouseOver();
		}
		return false;
	}
}
