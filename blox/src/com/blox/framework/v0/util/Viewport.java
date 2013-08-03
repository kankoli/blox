package com.blox.framework.v0.util;

public class Viewport {
	private float scale;
	private Vector offset;

	private Viewport(float scale, Vector offset) {
		this.scale = scale;
		this.offset = new Vector(offset);
	}

	public float getScale() {
		return scale;
	}

	public Vector getOffset() {
		return offset;
	}

	public static Viewport create(float width, float height, float screenWidth, float screenHeight) {
		float aspect = width / height;

		float sa = screenWidth / screenHeight;

		float w, h, scale;
		Vector offset = new Vector();
		
		if (sa < aspect) {
			scale = screenWidth / width;
			
			w = screenWidth;
			h = scale  * height;
			
			offset.x = 0;
			offset.y = (screenHeight - h) / 2;
		}
		else {
			scale = screenHeight / height;
			
			h = screenHeight;
			w = scale  * width;
			
			offset.x = (screenWidth - w) / 2;
			offset.y = 0;
		}

		return new Viewport(scale, offset);
	}
}
