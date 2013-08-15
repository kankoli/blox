package com.blox.framework.v0.forms.xml;

import com.blox.framework.v0.IDrawable;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.Rotation;
import com.blox.framework.v0.util.Vector;

class ControlDrawableAdapter implements IDrawable {
	
	private static final Vector noScale = new Vector(1, 1, 1);
	private static final Rotation noRotation = new Rotation();

	private float width;
	private float height;
	private Vector location;
	
	private final Control control;

	protected ControlDrawableAdapter(Control control) {
		this.control = control;
		this.location = new Vector();	
	}
	
	protected void update(Layout layout) {
		float xScale = Game.getScreenWidth() / layout.getCols();
		float yScale = Game.getScreenHeight() / layout.getRows();

		location.x = xScale * control.getX();
		location.y = yScale * control.getY();

		width = xScale * control.getCols();
		height = yScale * control.getRows();		
	}

	@Override
	public float getWidth() {
		return width;
	}

	@Override
	public float getHeight() {
		return height;
	}

	@Override
	public Vector getLocation() {
		return location;
	}

	@Override
	public Vector getScale() {		
		return noScale;
	}

	@Override
	public Rotation getRotation() {
		return noRotation;
	}

	@Override
	public boolean isFlipX() {
		return false;
	}

	@Override
	public boolean isFlipY() {
		return false;
	}

	@Override
	public boolean ignoreViewportOffset() {
		return true;
	}

	@Override
	public boolean ignoreViewportScaling() {
		return true;
	}

	@Override
	public void draw() {
		control.getTexture().draw(this);
	}
}
