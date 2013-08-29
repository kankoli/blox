package com.blox.framework.v0;

import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.Rotation;
import com.blox.framework.v0.util.Vector;

public interface IDrawable {
	public static final Vector noScale = new Vector(1, 1, 1);
	public static final Rotation noRotation = new Rotation();
	public static final Vector origin = new Vector();
	
	public static final IDrawable background = new IDrawable() {
		@Override
		public boolean isFlipY() {
			return false;
		}
		
		@Override
		public boolean isFlipX() {
			return false;
		}
		
		@Override
		public boolean ignoreViewportScaling() {
			return true;
		}
		
		@Override
		public boolean ignoreViewportOffset() {
			return true;
		}
		
		@Override
		public float getWidth() {
			return Game.getScreenWidth();
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
		public Vector getLocation() {
			return origin;
		}
		
		@Override
		public float getHeight() {
			return Game.getScreenHeight();
		}
		
		@Override
		public void draw() {

		}
	};
	
	float getWidth();

	float getHeight();

	Vector getLocation();

	Vector getScale();

	Rotation getRotation();

	boolean isFlipX();

	boolean isFlipY();

	void draw();

	boolean ignoreViewportOffset();
	
	boolean ignoreViewportScaling();
}
