package com.blox.framework.v0;

public class Vector {
	public float x;
	public float y;
	public float z;

	public Vector set(float v) {
		set(v, v, v);
		return this;
	}

	public Vector set(Vector v) {
		set(v.x, v.y, v.z);
		return this;
	}

	public Vector set(float x, float y, float z) {
		setX(x);
		setY(y);
		setZ(z);
		return this;
	}

	public Vector setX(float x) {
		this.x = x;
		return this;
	}

	public Vector setY(float y) {
		this.y = y;
		return this;
	}

	public Vector setZ(float z) {
		this.z = z;
		return this;
	}

	public Vector add(float d) {
		add(d, d, d);
		return this;
	}

	public Vector addX(float dx) {
		this.x += dx;
		return this;
	}

	public Vector addY(float dy) {
		this.y += dy;
		return this;
	}

	public Vector addZ(float dz) {
		this.z += dz;
		return this;
	}

	public Vector add(float dx, float dy, float dz) {
		addX(dx);
		addY(dy);
		addZ(dz);
		return this;
	}

	public Vector add(Vector dr) {
		addX(dr.x);
		addY(dr.y);
		addZ(dr.z);
		return this;
	}
}
