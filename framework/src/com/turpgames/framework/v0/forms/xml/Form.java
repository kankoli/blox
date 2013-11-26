package com.turpgames.framework.v0.forms.xml;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.turpgames.framework.v0.IView;
import com.turpgames.framework.v0.metadata.ControlMetadata;
import com.turpgames.framework.v0.metadata.FormMetadata;
import com.turpgames.framework.v0.metadata.GameMetadata;
import com.turpgames.framework.v0.util.Drawer;
import com.turpgames.framework.v0.util.Utils;

public class Form extends Control implements IView {
	private Map<String, Control> controls;
	private Drawer formDrawer;
	private ControlInputListener inputListener;

	protected Form() {
		controls = new HashMap<String, Control>();
		formDrawer = new Drawer();
		inputListener = new ControlInputListener();
	}

	public void addControl(Control control) {
		controls.put(control.id, control);
		if (control instanceof DrawableControl) {
			DrawableControl drawableControl = (DrawableControl) control;
			formDrawer.register(drawableControl, Utils.LAYER_SCREEN);
			inputListener.register(drawableControl);
		}
	}

	public void removeControl(String id) {
		Control control = controls.remove(id);
		if (control instanceof DrawableControl) {
			DrawableControl drawableControl = (DrawableControl) control;
			formDrawer.unregister(drawableControl);
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

	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (visible)
			inputListener.activate();
		else
			inputListener.deactivate();
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

	@Override
	public void draw() {
		formDrawer.draw();
	}

	@Override
	public void activate() {
		show();
	}

	@Override
	public boolean deactivate() {
		hide();
		return true;
	}

	@Override
	public void disable() {
		for (Control control : controls.values())
			control.disable();
		super.disable();
	}

	@Override
	public void enable() {
		for (Control control : controls.values())
			control.enable();
		super.enable();
	}
}