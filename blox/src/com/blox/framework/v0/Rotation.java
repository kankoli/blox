package com.blox.framework.v0;

public class Rotation {
	private Vector vector;

	public Rotation() {
		vector = new Vector();
	}

	public Vector getVector() {
		return vector;
	}

	public Rotation setRotation(Vector rotation) {
		setRotation(rotation.x, rotation.y, rotation.z);
		return this;
	}

	public Rotation setRotation(float x, float y, float z) {
		setXRotation(x);
		setYRotation(y);
		setZRotation(z);
		return this;
	}

	public Rotation setXRotation(float x) {
		vector.x = x;
		return this;
	}

	public Rotation setYRotation(float y) {
		vector.y = y;
		return this;
	}

	public Rotation setZRotation(float z) {
		vector.z = z;
		return this;
	}

	public Rotation rotateX(float dx) {
		vector.x += dx;
		return this;
	}

	public Rotation rotateY(float dy) {
		vector.y += dy;
		return this;
	}

	public Rotation rotateZ(float dz) {
		vector.z += dz;
		return this;
	}

	public Rotation rotate(float dx, float dy, float dz) {
		rotateX(dx);
		rotateY(dy);
		rotateZ(dz);
		return this;
	}

	public Rotation rotate(Vector dr) {
		rotateX(dr.x);
		rotateY(dr.y);
		rotateZ(dr.z);
		return this;
	}
}
