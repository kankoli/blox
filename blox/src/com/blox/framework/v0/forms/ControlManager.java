package com.blox.framework.v0.forms;

import java.util.ArrayList;
import java.util.List;

import com.blox.framework.v0.IInputListener;
import com.blox.framework.v0.util.Vector;

public class ControlManager implements IInputListener {

	private List<ControlBase> controls;

	public ControlManager() {
		controls = new ArrayList<ControlBase>();
	}

	public void register(ControlBase control) {
		if (!controls.contains(control))
			controls.add(control);
	}

	public void unregister(ControlBase control) {
		controls.remove(control);
	}

	private List<ControlBase> getControls(float x, float y) {
		return getControls(controls, x, y);
	}

	private static List<ControlBase> getControls(List<ControlBase> controls, float x, float y) {
		List<ControlBase> list = new ArrayList<ControlBase>();
		for (ControlBase control : controls) {
			if (isIn(control, x,y)) {
				list.add(control);
				if (control instanceof ControlContainer) {
					list.addAll(getControls(((ControlContainer)control).getControls(), x, y));
				}
			}
		}
		return list;
	}

	private static boolean isIn(ControlBase control, float x, float y) {
		Vector loc = control.getLocation();
		return x > loc.x && x < loc.x + control.getWidth() && 
			   y > loc.y && y < loc.y + control.getHeight();
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		List<ControlBase> list = getControls(x, y);
		for (ControlBase control : list)
			control.onTouchDown();
		return false;
	}

	@Override
	public boolean touchUp(float x, float y, int pointer, int button) {
		List<ControlBase> list = getControls(x, y);
		for (ControlBase control : list)
			control.onTouchUp();
		return false;
	}

	@Override
	public boolean touchDragged(float x, float y, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(float x, float y) {
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		// TODO Auto-generated method stub
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
	public boolean scrolled(float amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
