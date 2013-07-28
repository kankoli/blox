package com.blox.maze.controller;

import com.blox.framework.v0.impl.State;
import com.blox.framework.v0.util.Animation;

public class MazeLokumOnPortalState extends State {
	private MazeController controller;

	public MazeLokumOnPortalState(MazeController parent) {
		this.controller = parent;
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		controller.portalFinished();
	}
}