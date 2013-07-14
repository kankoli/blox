package com.blox.framework.v0.libgdx;

import com.badlogic.gdx.math.Vector3;
import com.blox.framework.v0.IVector;

class GdxVector implements IVector {
	Vector3 vector;

	GdxVector() {
		vector = new Vector3();
	}

	GdxVector(float x, float y, float z) {
		this();
		vector.x = x;
		vector.y = y;
		vector.z = z;
	}

	GdxVector(Vector3 vector) {
		this(vector.x, vector.y, vector.z);
	}

	@Override
	public float getX() {
		return vector.x;
	}

	@Override
	public IVector setX(float x) {
		vector.x = x;
		return this;
	}

	@Override
	public float getY() {
		return vector.y;
	}

	@Override
	public IVector setY(float y) {
		vector.y = y;
		return this;
	}

	@Override
	public float getZ() {
		return vector.z;
	}

	@Override
	public IVector setZ(float z) {
		vector.z = z;
		return this;
	}

	@Override
	public IVector add(IVector v) {
		vector.add(v.getX(), v.getY(), v.getZ());
		return this;
	}

	@Override
	public IVector sub(IVector v) {
		vector.sub(v.getX(), v.getY(), v.getZ());
		return this;
	}

	@Override
	public IVector mul(IVector v) {
		vector.mul(v.getX(), v.getY(), v.getZ());
		return this;
	}

	@Override
	public float dot(IVector v) {
		return vector.dot(v.getX(), v.getY(), v.getZ());
	}

	@Override
	public IVector div(IVector v) {
		vector.div(v.getX(), v.getY(), v.getZ());
		return this;
	}

	@Override
	public IVector mul(float v) {
		vector.mul(v);
		return this;
	}

	@Override
	public float len() {
		return vector.len();
	}
}
