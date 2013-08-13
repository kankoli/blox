package com.blox.maze.controller;

import com.blox.framework.v0.ICollidable;
import com.blox.framework.v0.util.Animation;
import com.blox.framework.v0.util.CollisionEvent;
import com.blox.maze.model.Block;

public class MazeLokumOnObjectiveState extends MazeState {
	private MazeController controller;

	public MazeLokumOnObjectiveState(MazeController parent) {
		this.controller = parent;
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		controller.finishMap();
	}

	@Override
	public void onCollide(CollisionEvent event) {
		ICollidable thatObj = event.getThatObj();
		if (thatObj instanceof Block) {
			controller.lokumStopOnBlock(event.getThisBound(), event.getThatBound(), thatObj);
		}
	}
}
