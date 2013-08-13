package com.blox.framework.v0.forms.xml;

import java.util.ArrayList;
import java.util.List;

import com.blox.framework.v0.impl.CompositeInputListener;
import com.blox.framework.v0.util.Game;

class ControlInputListener extends CompositeInputListener {
	private List<Control> controls;
	
	static final ControlInputListener instance = new ControlInputListener();

	private ControlInputListener() {
		controls = new ArrayList<Control>();
		Game.getInputManager().register(this);
	}
	
	void register(Control control) {
		if (!controls.contains(control))
			controls.add(control);
	}
	
	void unregister(Control control) {
		controls.remove(control);
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		for (Control control : controls) {
			if (control.isIn(x, y))
				control.onTouchDown();
		}
		return false;
	}

	@Override
	public boolean touchUp(float x, float y, int pointer, int button) {
		for (Control control : controls) {
			if (control.isIn(x, y))
				control.onTouchUp();
		}
		return false;
	}

	@Override
	public boolean touchDragged(float x, float y, int pointer) {
		for (Control control : controls) {
			if (control.isIn(x, y))
				control.onTouchDragged();
		}
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		for (Control control : controls) {
			if (control.isIn(x, y))
				control.onTap();
		}
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		for (Control control : controls) {
			if (control.isIn(x, y))
				control.onLongPress();
		}
		return false;
	}

	@Override
	public boolean keyDown(int keycode) {
		for (Control control : controls) {
			control.onKeyDown(keycode);
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		for (Control control : controls) {
			control.onKeyUp(keycode);
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		for (Control control : controls) {
			control.onKeyTyped(character);
		}
		return false;
	}

	@Override
	public boolean mouseMoved(float x, float y) {
		for (Control control : controls) {
			control.onMouseOver();
		}
		return false;
	}
}
