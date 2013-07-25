package com.blox.maze.model;

import com.blox.framework.v0.impl.GameObject;
import com.blox.framework.v0.util.Rotation;

public class MazeGameObject extends GameObject {

	public MazeGameObject() {
	}

	public void setRotation(Rotation r) {
		this.rotation = r;
	}

	public void setRotationZ(float z) {
		this.rotation.rotation.z = z;
	}

	public float getRotationZ() {
		return this.rotation.rotation.z;
	}
}
