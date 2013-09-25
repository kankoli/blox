package com.blox.framework.v0;

import com.blox.framework.v0.util.Color;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.Rotation;
import com.blox.framework.v0.util.Vector;

public interface IDrawingInfo {
	public static final Vector noScale = new Vector(1, 1, 1);
	public static final Rotation noRotation = new Rotation();
	public static final Vector origin = new Vector();
	public static final Color white = Color.white();
	
	public static final IDrawingInfo screen = new IDrawingInfo() {
		@Override
		public boolean isFlipY() {
			return false;
		}
		
		@Override
		public boolean isFlipX() {
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
		public Color getColor() {
			return white;
		}
		
		@Override
		public Vector getLocation() {
			return origin;
		}
		
		@Override
		public float getHeight() {
			return Game.getScreenHeight();
		}
	};
	
	public static final IDrawingInfo viewport = new IDrawingInfo() {
		@Override
		public boolean isFlipY() {
			return false;
		}
		
		@Override
		public boolean isFlipX() {
			return false;
		}
				
		@Override
		public boolean ignoreViewport() {
			return false;
		}
		
		@Override
		public boolean ignoreShifting() {
			return false;
		}		
		
		@Override
		public float getWidth() {
			return Game.getVirtualWidth();
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
			return white;
		}
		
		@Override
		public Vector getLocation() {
			return origin;
		}
		
		@Override
		public float getHeight() {
			return Game.getVirtualHeight();
		}
	};
	
	float getWidth();

	float getHeight();

	Vector getLocation();

	Vector getScale();

	Rotation getRotation();
	
	Color getColor();

	boolean isFlipX();

	boolean isFlipY();

	boolean ignoreViewport();
	
	boolean ignoreShifting();
}
