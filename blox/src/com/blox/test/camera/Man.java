package com.blox.test.camera;

import com.blox.framework.v0.impl.GameObject;

public class Man extends GameObject {
	public Man() {
		addAnimation("anim", "all.png", 0.5f, 128, 128).setLooping(true);;
		startAnimation("anim");
		width = 128;
		height = 128;
	}
	
	void restart() {
		startAnimation(true);
	}
}