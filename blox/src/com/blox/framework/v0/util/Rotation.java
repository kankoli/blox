package com.blox.framework.v0.util;

public class Rotation {
	public Vector rotation;
	public Vector origin;

	public Rotation() {
		rotation = new Vector();
		origin = new Vector();
	}

	public Rotation(Rotation rotation) {
		this.rotation = new Vector(rotation.rotation);
		this.origin = new Vector(rotation.origin);
	}

	public Rotation(float rx, float ry, float rz, float ox, float oy, float oz) {
		this.rotation = new Vector(rx, ry, rz);
		this.origin = new Vector(ox, oy, oz);
	}
	
	public void setRotationZ(float z) {
		rotation.z = z;
	}

	public void addRotationZ(float z) {
		rotation.z += z;
	}
}
