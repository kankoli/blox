package com.blox.framework.v0.forms.xml;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import com.blox.framework.v0.ITexture;
import com.blox.framework.v0.IUpdatable;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.Vector;

public abstract class Control implements IUpdatable {
	protected String id;
	protected int x;
	protected int y;
	protected int cols;
	protected int rows;
	protected boolean isVisible;
	protected boolean isEnabled;
	protected Panel panel;
	
	protected Control() {
		isVisible = true;
		isEnabled = true;
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
	
	public Panel getPanel() {
		return panel;
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
	
	protected void load(Node node) {
		NamedNodeMap attributes = node.getAttributes();		
		for (int i = 0; i < attributes.getLength(); i++) {
			Node attribute =attributes.item(i); 
			setAttribute(attribute.getNodeName(), attribute.getNodeValue());
		}
	}

	protected void draw() {
		if (!isVisible)
			return;

		ControlDrawableAdapter.instance.update(this);
		
		ITexture texture = getTexture();
		if (texture != null) {
			texture.draw(ControlDrawableAdapter.instance);
		} else {
			// TODO: draw rect 
		}
	}

	protected boolean isTouched() {		
		if (!Game.currentInputManager.isTouched())
			return false;
		
		float x = Game.currentInputManager.getX();
		float y = Game.currentInputManager.getY();
		
		return isIn(x, y);
	}

	protected boolean isIn(float x, float y) {
		ControlDrawableAdapter.instance.update(this);
		
		Vector loc = ControlDrawableAdapter.instance.getLocation();
		float width = ControlDrawableAdapter.instance.getWidth();
		float height = ControlDrawableAdapter.instance.getHeight();

		return x > loc.x && x < loc.x + width && y > loc.y && y < loc.y + height;
	}
	
	protected abstract ITexture getTexture();
	
	@Override
	public void update() {
	}	
	
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