package com.blox.maze.controller;

import com.blox.framework.v0.ICollidable;
import com.blox.framework.v0.impl.State;
import com.blox.framework.v0.util.Animation;
import com.blox.framework.v0.util.CollisionEvent;
import com.blox.maze.model.Block;

public class MazeLokumOnTrapState extends State {

	private MazeController controller;

	public MazeLokumOnTrapState(MazeController parent) {
		this.controller = parent;
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		controller.resetMap();
	}

	@Override
	public void onCollide(CollisionEvent event) {
		ICollidable thatObj = event.getThatObj();
		if (thatObj instanceof Block) {
			controller.lokumStopOnBlock(event.getThisBound(), event.getThatBound(), thatObj);
		}
	}
}
