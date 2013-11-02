package com.turpgames.framework.v0.forms.xml;

import com.turpgames.framework.v0.IDrawable;
import com.turpgames.framework.v0.ITexture;
import com.turpgames.framework.v0.util.Game;
import com.turpgames.framework.v0.util.TextureDrawer;
import com.turpgames.framework.v0.util.Utils;
import com.turpgames.framework.v0.util.Vector;

public abstract class DrawableControl extends Control implements IDrawable {
	protected int x;
	protected int y;
	
	protected ControlDrawingInfo drawable;
	
	protected DrawableControl() {
		drawable = new ControlDrawingInfo(this);
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

	@Override
	public void draw() {
		if (!isVisible)
			return;
		if (getTexture() != null)
			TextureDrawer.draw(getTexture(), drawable);
		onDraw();
	}
	
	protected void onDraw() {
		
	}

	protected boolean isTouched() {
		if (!inputManager.isTouched())
			return false;
		if (inputManager.isDialogActive())
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
