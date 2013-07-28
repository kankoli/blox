package com.blox.maze.model;

import com.blox.framework.v0.IAnimationEndListener;
import com.blox.framework.v0.impl.GameObject;
import com.blox.framework.v0.util.Rotation;

public abstract class MazeGameObject extends GameObject {

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
	
	public void registerAnimationEndListener(IAnimationEndListener listener) {
		animator.registerEndListener(listener);
	}

	public void unregisterAnimationEndListener(IAnimationEndListener listener) {
		animator.unregisterEndListener(listener);
	}
}
