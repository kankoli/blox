package com.blox.framework.v0.forms;

import java.util.ArrayList;
import java.util.List;

public abstract class ControlContainer extends ControlBase {
	private List<ControlBase> controls;
	
	protected ControlContainer() {
		controls = new ArrayList<ControlBase>();
	}
	
	public void add(ControlBase control) {
		controls.add(control);
	}
	
	public void remove(ControlBase control) {
		controls.remove(control);
	}
	
	public List<ControlBase> getControls() {
		return controls;
	}
}
