package com.blox.maze.controller;

import java.util.List;

import com.blox.framework.v0.IBound;
import com.blox.framework.v0.ICollidable;
import com.blox.framework.v0.IState;
import com.blox.framework.v0.impl.State;
import com.blox.framework.v0.impl.StateManager;
import com.blox.maze.model.Lokum;
import com.blox.maze.model.Maze;
import com.blox.maze.model.Portal;
import com.blox.maze.model.PortalDoor;
import com.blox.maze.view.MazeScreen;

public class MazeController extends StateManager {

	private MazeWaitingState waiting;
	private MazeUserRotatingState userRotating;
	private MazeMazeRotatingState mazeRotating;
	private MazeLokumFallingState lokumFalling;
	private MazeLokumOnTrapState lokumOnTrap;
	private MazeLokumOnObjectiveState lokumOnObjective;
	private MazeLokumOnPortalState lokumOnPortal;

	private MazeScreen screen;

	private Maze maze;
	private List<Portal> portals;
	private Lokum lokum;
	
	public MazeController(MazeScreen parent) {
		this.screen = parent;

		waiting = new MazeWaitingState(this);
		userRotating = new MazeUserRotatingState(this);
		mazeRotating = new MazeMazeRotatingState(this);
		lokumFalling = new MazeLokumFallingState(this);
		lokumOnTrap = new MazeLokumOnTrapState(this);
		lokumOnObjective = new MazeLokumOnObjectiveState(this);
		lokumOnPortal = new MazeLokumOnPortalState(this);
		
		maze = new Maze(this.screen);
		portals = maze.getPortals();
		lokum = new Lokum(maze, 1, 1);
		
		MazeMover.instance.register(lokum);
		this.screen.registerDrawable(lokum, 2);
		this.screen.registerMovable(lokum);
		this.screen.registerCollidable(lokum);

		setCurrState(waiting);
	}

	private void setCurrState(IState s) {
		if (currState instanceof State) {
			screen.unregisterInputListener(currState);
			lokum.unregisterCollisionListener(currState);
			lokum.unregisterAnimationEndListener(currState);
			maze.unregisterPortalsCollisionListener(currState);
			maze.unregisterPortalsAnimationEndListener(currState);
		}
		currState = s;
		if (s instanceof State) {
			screen.registerInputListener(s);
			lokum.registerCollisionListener(currState);
			lokum.registerAnimationEndListener(currState);
			maze.registerPortalsCollisionListener(currState);
			maze.registerPortalsAnimationEndListener(currState);
		}
	}
	
	private float mazeOldRotation; // keep old rotation

	public void beginUserRotating(float rotateStartX, float rotateStartY) {
		mazeOldRotation = maze.getRotationZ();
		userRotating.setStarts(rotateStartX, rotateStartY);
		setCurrState(userRotating);
	}

	public void userRotated(float userRotation) {
		maze.setRotationZ(mazeOldRotation + userRotation);
	}

	public void userAbortRotation() {
		maze.setRotationZ(mazeOldRotation);
		setCurrState(waiting);
	}

	private int userRotation; // init -1 or 1 for maze rotation. add to
								// mazeOldRotation and set rotation when
								// animation is finished
	private float mazeTempRotation; // decrement while rotating

	public void startMazeRotate(int userRotation, float userAngle) {
		this.userRotation = userRotation;
		mazeTempRotation = 90 - userRotation * userAngle;
		setCurrState(mazeRotating);
	}

	private float epsilon = 0.1f;

	public void mazeRotate(float increment) {
		maze.getRotation().rotation.z += userRotation * increment;
		mazeTempRotation -= increment;
		if (mazeTempRotation <= 0 + epsilon) { // MAZE_ROTATE FINISHED
			maze.setRotationZ(mazeOldRotation + userRotation * 90);
			MazeMover.instance.turn(userRotation == 1);

			setCurrState(lokumFalling);
		}
	}

	public void lokumFallOnBlock(IBound thisBound, IBound thatBound, ICollidable thatObj) {
		lokum.fellOnBlock(thisBound, thatBound, thatObj);
		setCurrState(waiting);
	}

	public void lokumFinishOnBlock(IBound thisBound, IBound thatBound, ICollidable thatObj) {
		lokum.fellOnBlock(thisBound, thatBound, thatObj);
	}
	
	public void lokumFallOnTrap() {
		lokum.fellOnTrap();
		setCurrState(lokumOnTrap);
	}

	public void resetMap() {
		// TODO: reset map and lokum
		MazeMover.instance.resetRotation();
		lokum.reset();
		maze.reset();
		setCurrState(waiting);
	}
	
	public void lokumFallOnObjective() {
		lokum.fellOnObjective();
		setCurrState(lokumOnObjective);
	}

	public void finishMap() {
		// TODO Get next map falan
		resetMap();
	}

	private PortalDoor door;
	private PortalDoor doorPair;
	public void lokumFallOnPortal(PortalDoor door) {
		this.door = door;
		this.doorPair = this.door.getPair();
		doorPair.registerAnimationEndListener(lokumOnPortal);
		
		maze.collidedPortalDoor(this.door);
		screen.unregisterCollidable(this.doorPair);
		screen.unregisterDrawable(lokum);
		this.door.registerAnimationEndListener(lokumOnPortal);
		setCurrState(lokumOnPortal);
	}

	public void portalFinished() {
		lokum.teleport(this.door.getPair());
		this.door.unregisterAnimationEndListener(lokumOnPortal);
		maze.finishedPortal(this.door);
		screen.registerDrawable(lokum, 2);
		setCurrState(waiting);
	}

	public void readdOldPortalExitDoor() {
//		if (this.doorPair != null)
//			screen.registerCollidable(this.doorPair);
	}
}
