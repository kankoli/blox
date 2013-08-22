package com.blox.framework.v0.forms.xml;

import java.util.Map;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import com.blox.framework.v0.IDrawable;
import com.blox.framework.v0.IInputManager;
import com.blox.framework.v0.ITexture;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.Utils;
import com.blox.framework.v0.util.Vector;

public abstract class Control {
	protected static final IInputManager inputManager = Game.getInputManager();

	protected String id;
	protected int x;
	protected int y;
	protected int cols;
	protected int rows;
	protected boolean isVisible;
	protected boolean isEnabled;
	protected String skinId;
	
	protected ControlDrawableAdapter drawable;

	protected Control() {		
		setVisible(true);
		setEnabled(true);
		
		skinId = "default";

		drawable = new ControlDrawableAdapter(this);
	}
	
	public String getId() {
		return id;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
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
		else if ("x".equals(attribute))
			x = Integer.parseInt(value);
		else if ("y".equals(attribute))
			y = Integer.parseInt(value);
		else if ("cols".equals(attribute))
			cols = Integer.parseInt(value);
		else if ("rows".equals(attribute))
			rows = Integer.parseInt(value);
		else if ("enabled".equals(attribute))
			setEnabled("true".equals(value));
		else if ("visible".equals(attribute))
			setVisible("true".equals(value));
	}

	protected void load(Node node, Layout layout) {
		String sid = Utils.getAttributeValue(node, "skinId");
		if (sid != null && !"".equals(sid))
			skinId = sid;
		
		loadSkin();
		
		NamedNodeMap attributes = node.getAttributes();
		for (int i = 0; i < attributes.getLength(); i++) {
			Node attribute = attributes.item(i);
			setAttribute(attribute.getNodeName(), attribute.getNodeValue());
		}
		drawable.update(layout);
	}

	protected void draw() {

	}
	
	protected IDrawable getDrawable() {
		return drawable;
	}

	protected boolean isTouched() {
		if (!inputManager.isTouched())
			return false;

		float x = inputManager.getX();
		float y = inputManager.getY();

		return isIn(x, y);
	}

	protected boolean isIn(float x, float y) {
		Vector loc = drawable.getLocation();

		float width = drawable.getWidth();
		float height = drawable.getHeight();

		return Utils.isIn(Game.viewportToScreenX(x), Game.viewportToScreenY(y), loc, width, height);
	}

	private void loadSkin() {		
		Skin skin = UIManager.getSkin(skinId);
		if (skin == null) 
			return;
		
		Map<String, String> skinValues = skin.get(getNodeName());		
		if (skinValues == null)
			return;
		
		for (String key : skinValues.keySet())
			setAttribute(key, skinValues.get(key));
	}
	
	protected abstract ITexture getTexture();
	protected abstract String getNodeName();

	@Override
	public String toString() {
		return id;
	}

	protected void onTouchDown() {

	}

	protected void onTouchUp() {

	}

	protected void onTouchDragged() {

	}

	protected void onTap() {

	}

	protected void onLongPress() {

	}

	protected void onKeyDown(int keycode) {

	}

	protected void onKeyUp(int keycode) {

	}

	protected void onKeyTyped(char character) {

	}

	protected void onMouseOver() {

	}
}