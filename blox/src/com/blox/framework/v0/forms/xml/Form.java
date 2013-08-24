package com.blox.framework.v0.forms.xml;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.blox.framework.v0.IDrawer;
import com.blox.framework.v0.util.ControlMetadata;
import com.blox.framework.v0.util.FormMetadata;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.GameMetadata;

public class Form extends Control {
	private Map<String, Control> controls;
	private IDrawer formDrawer;
	private ControlInputListener inputListener;

	protected Form() {
		controls = new HashMap<String, Control>();
		formDrawer = Game.createDrawer();
		inputListener = new ControlInputListener();
	}

	public void addControl(Control control) {
		controls.put(control.id, control);
		if (control instanceof DrawableControl) {
			DrawableControl drawableControl = (DrawableControl) control;
			formDrawer.register(drawableControl.getDrawable(), 1000);
			inputListener.register(drawableControl);
		}
	}

	public void removeControl(String id) {
		Control control = controls.remove(id);
		if (control instanceof DrawableControl) {
			DrawableControl drawableControl = (DrawableControl) control;
			formDrawer.unregister(drawableControl.getDrawable());
			inputListener.unregister(drawableControl);
		}
	}

	public Collection<Control> getControls() {
		return controls.values();
	}

	@SuppressWarnings("unchecked")
	public <T extends Control> T getControl(String id) {
		return (T) controls.get(id);
	}

	public int getRows() {
		return rows;
	}

	public int getCols() {
		return cols;
	}

	public void render() {
		if (isVisible)
			formDrawer.draw();
	}

	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (visible) {
			Game.getDrawerManager().register(formDrawer, 1000);
			inputListener.activate();
		}
		else {
			Game.getDrawerManager().unregister(formDrawer);
			inputListener.deactivate();
		}
	}

	@Override
	protected String getNodeName() {
		return "form";
	}

	public static Form load(String formId) {
		FormMetadata metadata = GameMetadata.getForm(formId);
		if (metadata == null)
			return null;

		Form form = new Form();
		form.initAttributes(metadata.getAttributes());
		form.initControls(metadata.getControls());
		return form;
	}

	private void initControls(List<ControlMetadata> controlsMetadata) {
		for (ControlMetadata controlMetadata : controlsMetadata) {
			Control control = UIManager.createControl(controlMetadata);
			if (control == null)
				continue;
			if (control instanceof DrawableControl)
				((DrawableControl) control).updateDrawable(this);
			addControl(control);
		}
	}
}