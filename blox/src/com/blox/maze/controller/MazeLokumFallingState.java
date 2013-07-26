package com.blox.maze.controller;

import com.blox.framework.v0.IBound;
import com.blox.framework.v0.ICollidable;
import com.blox.framework.v0.impl.State;
import com.blox.maze.model.Objective;
import com.blox.maze.model.Portal;
import com.blox.maze.model.Trap;
import com.blox.maze.model.Block;

public class MazeLokumFallingState extends State {
	private MazeController controller;

	public MazeLokumFallingState(MazeController parent) {
		this.controller = parent;
	}
	
	@Override
	public void collide(ICollidable thisObj, IBound thisBound, ICollidable thatObj, IBound thatBound) {
		if (thatObj instanceof Block) {
			controller.lokumFallOnBlock(thisBound, thatBound, thatObj);
		}
		else if (thatObj instanceof Trap) {
			controller.lokumFallOnTrap();
		}
		else if (thatObj instanceof Objective) {
			controller.lokumFallOnObjective();
		}
		else if (thatObj instanceof Portal) {
			// check if collided entry door
			controller.lokumFallOnPortal();
		}
	}
}
