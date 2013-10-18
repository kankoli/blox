package com.turpgames.framework.v0.forms.xml;

import com.turpgames.framework.v0.IDrawable;
import com.turpgames.framework.v0.IDrawingInfo;
import com.turpgames.framework.v0.util.Color;
import com.turpgames.framework.v0.util.Game;
import com.turpgames.framework.v0.util.Rotation;
import com.turpgames.framework.v0.util.Vector;

class ControlDrawingInfo implements IDrawingInfo, IDrawable {
	
	private static final Vector noScale = new Vector(1, 1, 1);
	private static final Rotation noRotation = new Rotation();

	private float width;
	private float height;
	private Vector location;
	private Color color;
	
	protected final DrawableControl control;

	protected ControlDrawingInfo(DrawableControl control) {
		this.control = control;
		this.location = new Vector();
		this.color = Color.white();
	}
	
	protected void update(Form layout) {
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
	public void setWidth(float width) {
		this.width = width;
	}

	@Override
	public float getHeight() {
		return height;
	}

	@Override
	public void setHeight(float height) {
		this.height = height;
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
	public Color getColor() {
		return color;
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
	public boolean ignoreViewport() {
		return true;
	}
	
	@Override
	public boolean ignoreShifting() {
		return false;
	}	
	
	@Override
	public void draw() {
		control.draw();
	}
}
