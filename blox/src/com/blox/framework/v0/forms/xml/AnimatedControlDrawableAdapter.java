package com.blox.framework.v0.forms.xml;

import com.badlogic.gdx.math.MathUtils;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.Rotation;
import com.blox.framework.v0.util.Vector;

class AnimatedControlDrawableAdapter extends ControlDrawableAdapter {
	private Vector scale;
	private Rotation rotation;
	private float state;
	private float cx;
	private float cy;
	private float dx;
	private float dy;

	protected AnimatedControlDrawableAdapter(Control control, float cx, float cy, float dx, float dy) {
		super(control);
		scale = new Vector();
		rotation = new Rotation();
		this.cx = cx;
		this.cy = cy;
		this.dx = dx;
		this.dy = dy;
	}

	@Override
	protected void update(Layout layout) {
		super.update(layout);

		rotation.origin.x = getLocation().x + getWidth() / 2;
		rotation.origin.y = getLocation().y + getHeight() / 2;
	}
	
	@Override
	public Rotation getRotation() {
		return rotation;
	}

	@Override
	public Vector getScale() {
		return scale;
	}
	
	@Override
	public void draw() {
		state += Game.getDeltaTime();
		scale.x = 1 + MathUtils.sin(cx * state) * dx;
		scale.y = 1 + MathUtils.sin(cy * state) * dy;
		super.draw();
	}
}