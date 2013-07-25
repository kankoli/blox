package com.blox.maze.states;

import com.blox.framework.v0.impl.State;
import com.blox.framework.v0.impl.StateManager;

public class MazeRotatingState extends State {

	public MazeRotatingState(StateManager parent) {
		super(parent);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void work() {
		parent.advanceState();
		// TODO: do parent's (maze) 90 degrees rotation animation
	}
}
