package com.blox.framework.v0.forms.xml;

import com.blox.framework.v0.IDrawable;
import com.blox.framework.v0.IDrawer;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.Rotation;
import com.blox.framework.v0.util.Vector;

final class ControlDrawableAdapter implements IDrawable {
	final static ControlDrawableAdapter instance = new ControlDrawableAdapter();
	
	private static final Vector noScale = new Vector(1, 1, 1);
	private static final Rotation noRotation = new Rotation(0, 0, 0, 0, 0, 0);

	private float width;
	private float height;
	private Vector location;

	private IDrawer drawer;
	private Control currentControl;

	private ControlDrawableAdapter() {
		location = new Vector();
	}

	void setCurrentControl(Control control) {
		if (currentControl == control)
			return;		
		currentControl = control;
		
		float xScale = Game.getScreenWidth() / control.panel.getCols();
		float yScale = Game.getScreenHeight() / control.panel.getRows();

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
	public void draw() {
		drawer.draw(this);
	}

	@Override
	public void setDrawer(IDrawer drawer) {
		this.drawer = drawer;
	}

	@Override
	public boolean ignoreViewportOffset() {
		return true;
	}

	@Override
	public boolean ignoreViewportScaling() {
		return true;
	}

}
