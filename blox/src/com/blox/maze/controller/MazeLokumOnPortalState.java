package com.blox.maze.controller;

import com.blox.framework.v0.IBound;
import com.blox.framework.v0.ICollidable;
import com.blox.framework.v0.impl.State;
import com.blox.framework.v0.util.Animation;
import com.blox.maze.model.Block;

public class MazeLokumOnPortalState extends State {
	private MazeController controller;

	public MazeLokumOnPortalState(MazeController parent) {
		this.controller = parent;
	}
	
	@Override
	public void onAnimationEnd(Animation animation) {
		controller.portalFinished();
	}
	
	@Override
	public void collide(ICollidable thisObj, IBound thisBound, ICollidable thatObj, IBound thatBound) {
		if (thatObj instanceof Block) {
			controller.lokumFinishOnBlock(thisBound, thatBound, thatObj);
		}
	}
}