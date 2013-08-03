package com.blox.maze.controller;

import com.blox.framework.v0.util.Game;

public class MazeMazeRotatingState extends MazeState {

	private MazeController controller;

	public MazeMazeRotatingState(MazeController parent) {
		this.controller = parent;
	}

	private float increment;
	private float rotationSpeed = 90; // degrees per second

	@Override
	public void work() {
		increment = rotationSpeed * Game.getDeltaTime();
		controller.mazeRotate(increment);
	}
}
