package com.blox.maze.controller;

import java.util.ArrayList;
import java.util.List;

import com.blox.framework.v0.IBound;
import com.blox.framework.v0.ICollidable;
import com.blox.framework.v0.ICollisionListener;
import com.blox.framework.v0.IState;
import com.blox.framework.v0.impl.CollisionGroup;
import com.blox.framework.v0.impl.State;
import com.blox.framework.v0.impl.StateManager;
import com.blox.maze.model.Block;
import com.blox.maze.model.Lokum;
import com.blox.maze.model.Maze;
import com.blox.maze.model.Objective;
import com.blox.maze.model.Portal;
import com.blox.maze.model.PortalDoor;
import com.blox.maze.model.Trap;
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
	private List<ICollidable> blocks;
	private List<ICollidable> traps;
	private List<ICollidable> objectives;
	private List<ICollidable> portals;
	private Lokum lokum;
	
	private CollisionGroup lokumToBlocks;
	private CollisionGroup lokumToTraps;
	private CollisionGroup lokumToObjectives;
	private CollisionGroup lokumToPortals;
	private CollisionGroup lokumNotCollide;
	
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
		blocks = maze.getBlocks();
		traps = maze.getTraps();
		objectives = maze.getObjectives();
		portals = maze.getPortals();
		lokum = new Lokum(maze, 1, 1);
		
		lokumToBlocks = new CollisionGroup(lokum, blocks);
		lokumToTraps = new CollisionGroup(lokum, traps);
		lokumToObjectives = new CollisionGroup(lokum, objectives);
		lokumToPortals = new CollisionGroup(lokum, portals);
		lokumNotCollide = new CollisionGroup(lokum, new ArrayList<ICollidable>());

		lokumNotCollide.registerNotCollisionListener(lokumFalling);
		
		MazeMover.instance.register(lokum);
		this.screen.registerDrawable(lokum, 2);
		this.screen.registerMovable(lokum);

		this.screen.registerCollisionGroup(lokumToBlocks);
		this.screen.registerCollisionGroup(lokumToTraps);
		this.screen.registerCollisionGroup(lokumToObjectives);
		this.screen.registerCollisionGroup(lokumToPortals);
		this.screen.registerCollisionGroup(lokumNotCollide);

		setCurrState(waiting);
	}

	private void registerToLokumCollisionGroups(ICollisionListener listener) {
		lokumToBlocks.registerCollisionListener(listener);
		lokumToTraps.registerCollisionListener(listener);
		lokumToObjectives.registerCollisionListener(listener);
		lokumToPortals.registerCollisionListener(listener);
	}
	
	private void unregisterFromLokumCollisionGroups(ICollisionListener listener) {
		lokumToBlocks.unregisterCollisionListener(listener);
		lokumToTraps.unregisterCollisionListener(listener);
		lokumToObjectives.unregisterCollisionListener(listener);
		lokumToPortals.unregisterCollisionListener(listener);
	}
	
	private void setCurrState(IState s) {
		if (currState instanceof State) {
			screen.unregisterInputListener(currState);
			unregisterFromLokumCollisionGroups(currState);
			lokum.unregisterAnimationEndListener(currState);
			maze.unregisterPortalsAnimationEndListener(currState);
		}
		currState = s;
		System.out.println(currState.getClass().getName());
		if (currState instanceof State) {
			screen.registerInputListener(currState);
			registerToLokumCollisionGroups(currState);
			lokum.registerAnimationEndListener(currState);
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
		lokumStopOnBlock(thisBound, thatBound, thatObj);
		setCurrState(waiting);
	}

	public void lokumStopOnBlock(IBound thisBound, IBound thatBound, ICollidable thatObj) {
		lokumOnBlock(thisBound, thatBound, thatObj);
		lokum.stopLokum();
	}
	
	public void lokumOnBlock(IBound thisBound, IBound thatBound, ICollidable thatObj) {
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
		
		maze.collidedPortalDoor(this.door);
		screen.unregisterDrawable(lokum);
		this.door.registerAnimationEndListener(lokumOnPortal);
		setCurrState(lokumOnPortal);
	}

	public void portalFinished() {
		lokum.teleport(this.door.getPair());
		screen.registerDrawable(lokum, 2);
		this.door.unregisterAnimationEndListener(lokumOnPortal);
		maze.finishedPortal(this.door);
		
		setCurrState(lokumFalling);
		
		lokumToPortals.unregisterSecond(this.doorPair);
		lokumNotCollide.registerSecond(this.doorPair);
	}

	public void lokumUncollidedObject(ICollidable obj) {
		lokumToPortals.registerSecond(obj);
		lokumNotCollide.unregisterSecond(obj);
	}
}
