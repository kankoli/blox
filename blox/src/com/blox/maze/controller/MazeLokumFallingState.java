package com.blox.maze.controller;

import com.blox.framework.v0.IBound;
import com.blox.framework.v0.ICollidable;
import com.blox.framework.v0.ICollisionListener;

public class MazeLokumFallingState extends MazeState implements ICollisionListener {
	private MazeController controller;

	public MazeLokumFallingState(MazeController parent) {
		this.controller = parent;
	}
	
	@Override
	public void collide(ICollidable thisObj, IBound thisBound, ICollidable thatObj, IBound thatBound) {
		// TODO: neye carptigina gore controller'da ayri fonksiyonlar call.
		controller.endLokumFall();
	}
}
