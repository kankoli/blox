package com.blox.framework.v0.forms.xml;

import java.util.Map;

import com.blox.framework.v0.IInputManager;
import com.blox.framework.v0.util.Game;

public abstract class Control {
	protected static final IInputManager inputManager = Game.getInputManager();

	protected String id;
	protected int cols;
	protected int rows;
	protected boolean isVisible;
	protected boolean isEnabled;
	protected String skinId;

	protected Control() {
		isVisible = true;
		isEnabled = true;
		
		skinId = Game.getParam("default-skin");
	}
	
	public String getId() {
		return id;
	}

	public int getCols() {
		return cols;
	}

	public int getRows() {
		return rows;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean visible) {
		isVisible = visible;
	}

	public void show() {
		setVisible(true);
	}

	public void hide() {
		setVisible(false);
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean enabled) {
		isEnabled = enabled;
	}

	public void enable() {
		setEnabled(true);
	}

	public void disable() {
		setEnabled(false);
	}

	protected void setAttribute(String attribute, String value) {
		if ("id".equals(attribute))
			id = value;
		else if ("cols".equals(attribute))
			cols = Integer.parseInt(value);
		else if ("rows".equals(attribute))
			rows = Integer.parseInt(value);
		else if ("enabled".equals(attribute))
			setEnabled("true".equals(value));
		else if ("visible".equals(attribute))
			setVisible("true".equals(value));
	}

	protected void loadSkin() {
		Skin skin = UIManager.getSkin(skinId);
		if (skin == null) 
			return;
		
		Map<String, String> skinValues = skin.get(getNodeName());		
		if (skinValues == null)
			return;
		
		for (String key : skinValues.keySet())
			setAttribute(key, skinValues.get(key));
	}

	protected void initAttributes(Map<String, String> attributes) {
		String sid = attributes.get("skin-id");
		if (sid != null && !"".equals(sid))
			skinId = sid;
		
		loadSkin();
		
		for (String key : attributes.keySet())
			setAttribute(key, attributes.get(key));
	}
	
	protected abstract String getNodeName();

	@Override
	public String toString() {
		return id;
	}

}