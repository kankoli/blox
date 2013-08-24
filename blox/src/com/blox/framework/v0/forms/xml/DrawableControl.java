package com.blox.framework.v0.forms.xml;

import com.blox.framework.v0.IDrawable;
import com.blox.framework.v0.ITexture;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.Utils;
import com.blox.framework.v0.util.Vector;

public abstract class DrawableControl extends Control {
	protected int x;
	protected int y;
	
	protected ControlDrawableAdapter drawable;
	
	protected DrawableControl() {
		drawable = new ControlDrawableAdapter(this);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	protected abstract ITexture getTexture();

	@Override
	protected void setAttribute(String attribute, String value) {
		if ("x".equals(attribute))
			x = Integer.parseInt(value);
		
		else if ("y".equals(attribute))
			y = Integer.parseInt(value);

		else if ("action".equals(attribute))
			setAction(value);
		
		else
			super.setAttribute(attribute, value);
	}
	
	protected void setAction(String action) {
		
	}
	
	protected void updateDrawable(Form form) {
		drawable.update(form);
	}

	protected void draw() {
		if (!isVisible)
			return;
		getTexture().draw(drawable);
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
