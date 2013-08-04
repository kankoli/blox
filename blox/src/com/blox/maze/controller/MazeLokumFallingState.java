package com.blox.maze.controller;

import com.blox.framework.v0.IBound;
import com.blox.framework.v0.ICollidable;
import com.blox.framework.v0.ICollisionListener;
import com.blox.framework.v0.util.CollisionEvent;
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
	public void onCollide(CollisionEvent event) {
		IBound thisBound = event.getThisBound();
		ICollidable thatObj = event.getThatObj();
		IBound thatBound = event.getThatBound();
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
	public void onNotCollide(CollisionEvent event) {
		controller.lokumUncollidedPortalDoor(event.getThatObj());
	}
}
