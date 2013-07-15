package com.blox.framework.v0.libgdx;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.blox.framework.v0.Vector;

class GdxVector extends Vector {
	private Vector3 vector;

	GdxVector() {
		vector = new Vector3();
	}

	GdxVector(float x, float y, float z) {
		this();

		this.vector.x = x;
		this.vector.y = y;
		this.vector.z = z;
	}

	GdxVector(float x, float y) {
		this(x, y, 0);
	}

	GdxVector(Vector2 vector) {
		this(vector.x, vector.y, 0);
	}

	GdxVector(Vector3 vector) {
		this(vector.z, vector.y, vector.z);
	}

	Vector3 getVector3() {
		vector.x = this.x;
		vector.y = this.y;
		vector.z = this.z;
		return vector;
	}
}