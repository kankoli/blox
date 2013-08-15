package com.blox.framework.v0.forms.xml;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Layout  {
	protected int rows;
	protected int cols;
	private Map<String, Control> controls;

	protected Layout() {
		controls = new HashMap<String, Control>();
	}

	public void addControl(Control control) {
		controls.put(control.id, control);
	}

	public void removeControl(String id) {
		controls.remove(id);
	}

	public Collection<Control> getControls() {
		return controls.values();
	}

	@SuppressWarnings("unchecked")
	public <T extends Control> T getControl(String id) {
		return (T)controls.get(id);
	}

	public int getRows() {
		return rows;
	}

	public int getCols() {
		return cols;
	}

	public void draw() {
		for (Control control : getControls())
			control.draw();
	}
	
	void load(Element node) {
		NodeList children = node.getChildNodes();
		for (int i = 0; i < children.getLength(); i++) {
			Node child = children.item(i);
			Control control = UIManager.createControl(child.getNodeName());
			if (control == null)
				continue;
			control.load(child, this);			
			addControl(control);
		}
	}
}