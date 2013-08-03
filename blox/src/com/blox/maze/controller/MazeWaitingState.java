package com.blox.maze.controller;


public class MazeWaitingState extends MazeState {
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
