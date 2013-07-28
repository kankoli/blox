package com.blox.maze.controller;

import com.blox.framework.v0.IBound;
import com.blox.framework.v0.ICollidable;
import com.blox.framework.v0.impl.State;
import com.blox.framework.v0.util.Animation;
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
	public void collide(ICollidable thisObj, IBound thisBound, ICollidable thatObj, IBound thatBound) {
		if (thatObj instanceof Block) {
			controller.lokumStopOnBlock(thisBound, thatBound, thatObj);
		}
	}
}
