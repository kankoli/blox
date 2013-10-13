package com.blox.framework.v0.util;

public class Rectangle {
	public float x;
	public float y;
	public float width;
	public float height;

	public void setLocation(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void setLocation(Vector v) {
		this.x = v.x;
		this.y = v.y;
	}

	public void setSize(float w, float h) {
		this.width = w;
		this.height = h;
	}
	
	public void setSize(Vector v) {
		this.width = v.x;
		this.height = v.y;
	}
	
	public void set(float x, float y,float w, float h) {
		setLocation(x, y);
		setSize(w, h);
	}

	public void set(Rectangle rect) {
		set(rect.x, rect.y, rect.width, rect.height);
	}
}
