package com.blox.framework.v0.forms.xml;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.blox.framework.v0.util.Utils;

public class Layout  {
	protected int rows;
	protected int cols;
	private Map<String, Control> controls;
	private String skinId;

	protected Layout() {
		controls = new HashMap<String, Control>();
		skinId = "default";
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
	
	protected void setAttribute(String attribute, String value) {
		if ("cols".equals(attribute))
			cols = Integer.parseInt(value);
		else if ("rows".equals(attribute))
			rows = Integer.parseInt(value);
	}
	
	void load(Element node) {
		String sid = Utils.getAttributeValue(node, "skinId");
		if (sid != null && !"".equals(sid))
			skinId = sid;
		
		loadSkin();
		
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
	
	private void loadSkin() {
		Skin skin = UIManager.getSkin(skinId);
		if (skin == null) 
			return;
		
		Map<String, String> skinValues = skin.get("layout");		
		if (skinValues == null)
			return;
		
		for (String key : skinValues.keySet())
			setAttribute(key, skinValues.get(key));
	}
}