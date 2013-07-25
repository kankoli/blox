package com.blox.maze.controller;

import com.blox.framework.v0.impl.State;
import com.blox.framework.v0.util.Game;

public class MazeMazeRotatingState extends State {

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

	// public void update() {
	// if (currState == MazeState.MAZE_ROTATING) {
	//
	// }
	// }
}
