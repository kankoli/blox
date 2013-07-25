package com.blox.maze;

import com.blox.framework.v0.impl.GameObject;
import com.blox.framework.v0.util.Rotation;

public class MazeGameObject extends GameObject {

	private MazeScreen screen;
	
	public MazeGameObject(MazeScreen s) {
		setScreen(s);
	}
	
	public void setRotation(Rotation r) {
		this.rotation = r;
	}

	public MazeScreen getScreen() {
		return screen;
	}

	public void setScreen(MazeScreen screen) {
		this.screen = screen;
	}
}
