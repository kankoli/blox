package com.blox.maze.controller;

import com.blox.framework.v0.IBound;
import com.blox.framework.v0.ICollidable;
import com.blox.framework.v0.impl.State;
import com.blox.framework.v0.util.Animation;
import com.blox.maze.model.Block;

public class MazeLokumOnObjectiveState extends State {
	private MazeController controller;

	public MazeLokumOnObjectiveState(MazeController parent) {
		this.controller = parent;
	}
	
	@Override
	public void onAnimationEnd(Animation animation) {
		controller.finishMap();
	}
	
	@Override
	public void collide(ICollidable thisObj, IBound thisBound, ICollidable thatObj, IBound thatBound) {
		if (thatObj instanceof Block) {
			controller.lokumFinishOnBlock(thisBound, thatBound, thatObj);
		}
	}
}
