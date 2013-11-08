package com.turpgames.framework.v0.forms.xml;

import com.turpgames.framework.v0.IDrawable;
import com.turpgames.framework.v0.ITexture;
import com.turpgames.framework.v0.util.TextureDrawer;
import com.turpgames.framework.v0.util.Utils;

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
		return Utils.isIn(x, y, drawable);
	}

	protected boolean onTouchDown() {
		return false;
	}

	protected boolean onTouchUp() {
		return false;
	}

	protected boolean onTouchDragged() {
		return false;
	}

	protected boolean onTap() {
		return false;
	}

	protected boolean onLongPress() {
		return false;
	}

	protected boolean onKeyDown(int keycode) {
		return false;
	}

	protected boolean onKeyUp(int keycode) {
		return false;
	}

	protected boolean onKeyTyped(char character) {
		return false;
	}

	protected boolean onMouseOver() {
		return false;
	}

}
