package com.blox.maze.controller;

import com.blox.framework.v0.IBound;
import com.blox.framework.v0.ICollidable;
import com.blox.framework.v0.ICollisionListener;
import com.blox.maze.model.Block;
import com.blox.maze.model.Objective;
import com.blox.maze.model.PortalDoor;
import com.blox.maze.model.Trap;

public class MazeLokumFallingState extends MazeState implements ICollisionListener {

	private MazeController controller;

	public MazeLokumFallingState(MazeController parent) {
		this.controller = parent;
	}

	@Override
	public void collide(ICollidable thisObj, IBound thisBound, ICollidable thatObj, IBound thatBound) {
		if (thatObj instanceof Block) {
			controller.lokumFallOnBlock(thisBound, thatBound, thatObj);
		} else if (thatObj instanceof Trap) {
			controller.lokumFallOnTrap();
		} else if (thatObj instanceof Objective) {
			controller.lokumFallOnObjective();
		} else if (thatObj instanceof PortalDoor) {
			controller.lokumFallOnPortal((PortalDoor) thatObj);
		}
	}

	@Override
	public void notCollide(ICollidable thisObj, IBound thisBound, ICollidable thatObj, IBound thatBound) {
		controller.lokumUncollidedPortalDoor(thatObj);
	}
}
