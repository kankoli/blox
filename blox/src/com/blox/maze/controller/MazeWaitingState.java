package com.blox.maze.controller;

import com.blox.framework.v0.impl.State;

public class MazeWaitingState extends State {
	private MazeController controller;

	public MazeWaitingState(MazeController parent) {
		this.controller = parent;
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		controller.beginUserRotating(x, y);
		return false;
	}
}
