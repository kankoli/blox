package com.blox.framework.v0;

public class Vector {
	public float x;
	public float y;
	public float z;

	public Vector add(Vector v) {
		x += v.x;
		y += v.y;
		z += v.z;
		return this;
	}

	public Vector sub(Vector v) {
		x -= v.x;
		y -= v.y;
		z -= v.z;
		return this;
	}

	public Vector div(Vector v) {
		x /= v.x;
		y /= v.y;
		z /= v.z;
		return this;
	}

	public Vector mul(Vector v) {
		x *= v.x;
		y *= v.y;
		z *= v.z;
		return this;
	}

	public Vector mul(float f) {
		x *= f;
		y *= f;
		z *= f;
		return this;
	}

	public float len() {
		return (float) Math.sqrt(len2());
	}

	public float len2() {
		return x * x + y * y + z * z;
	}
	
	public float dist(Vector v) {
		return (float) Math.sqrt(dist2(v));
	}

	public float dist2(Vector v) {
		float dx = x - v.x;
		float dy = y - v.y;
		float dz = z - v.z;
		return dx * dx + dy * dy + dz * dz;
	}

	public float dot(Vector v) {
		return x * v.x + y * v.y + z * v.z;
	}
}
