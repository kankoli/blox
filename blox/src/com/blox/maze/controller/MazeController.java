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
import com.blox.maze.model.Lokum;
import com.blox.maze.model.Maze;
import com.blox.maze.model.PortalDoor;
import com.blox.maze.view.MazeScreen;

public class MazeController extends StateManager {

	private MazeScreen screen; // Parent screen.

	// FSM States implementing IState
	/**
	 * Waiting for user input.
	 */
	private MazeWaitingState waiting;
	/**
	 * User input (rotation) is being received.
	 */
	private MazeUserRotatingState userRotating;
	/**
	 * Input finished, maze is rotating.
	 */
	private MazeMazeRotatingState mazeRotating;
	/**
	 * Maze rotated, Lokum starts falling.
	 */
	private MazeLokumFallingState lokumFalling;
	/**
	 * Lokum fell on a trap.
	 */
	private MazeLokumOnTrapState lokumOnTrap;
	/**
	 * Lokum fell on the objective/door.
	 */
	private MazeLokumOnObjectiveState lokumOnObjective;
	/**
	 * Lokum fell on a portal door.
	 */
	private MazeLokumOnPortalState lokumOnPortal;

	// Collision groups for game logic
	private CollisionGroup lokumToBlocks;
	private CollisionGroup lokumToTraps;
	private CollisionGroup lokumToObjectives;
	private CollisionGroup lokumToPortals;
	/**
	 * Used to not-collide some objects with Lokum.
	 */
	private CollisionGroup lokumNotCollide;

	// Game objects. Lists used in collision groups
	private Maze maze;
	private Lokum lokum;
	private List<ICollidable> blocks;
	private List<ICollidable> traps;
	private List<ICollidable> objectives;
	private List<ICollidable> portals;

	public MazeController(MazeScreen parent) {
		this.screen = parent;

		// Initializations
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

		// 'lokumFalling' state listens to not-collision events.
		lokumNotCollide.registerNotCollisionListener(lokumFalling);

		MazeMover.instance.register(lokum); // MazeMover is delegated to handle
											// Lokum movement.
		this.screen.registerDrawable(lokum, 2);
		this.screen.registerMovable(lokum);

		// The collision groups are registered to the collision manager of the
		// parent screen.
		this.screen.registerCollisionGroup(lokumToBlocks);
		this.screen.registerCollisionGroup(lokumToTraps);
		this.screen.registerCollisionGroup(lokumToObjectives);
		this.screen.registerCollisionGroup(lokumToPortals);
		this.screen.registerCollisionGroup(lokumNotCollide);

		// State machine is started on 'waiting' state.
		setCurrState(waiting);
	}

	/***
	 * Registers given state for listening to all collision groups.
	 * 
	 * @param listener
	 */
	private void registerToLokumCollisionGroups(ICollisionListener listener) {
		lokumToBlocks.registerCollisionListener(listener);
		lokumToTraps.registerCollisionListener(listener);
		lokumToObjectives.registerCollisionListener(listener);
		lokumToPortals.registerCollisionListener(listener);
	}

	/***
	 * UNregisters given state to stop its listening to all collision groups.
	 * 
	 * @param listener
	 */
	private void unregisterFromLokumCollisionGroups(ICollisionListener listener) {
		lokumToBlocks.unregisterCollisionListener(listener);
		lokumToTraps.unregisterCollisionListener(listener);
		lokumToObjectives.unregisterCollisionListener(listener);
		lokumToPortals.unregisterCollisionListener(listener);
	}

	/***
	 * Sets current FSM state to given state. Stops the listening (Collision &
	 * Animation End) of the old state and starts the listening of the given
	 * state.
	 * 
	 * @param s
	 */
	private void setCurrState(IState s) {
		if (currState instanceof State) {
			screen.unregisterInputListener(currState);
			unregisterFromLokumCollisionGroups(currState);
			lokum.unregisterAnimationEndListener(currState);
			maze.unregisterPortalsAnimationEndListener(currState);
		}
		currState = s;
		// System.out.println(currState.getClass().getName());
		if (currState instanceof State) {
			screen.registerInputListener(currState);
			registerToLokumCollisionGroups(currState);
			lokum.registerAnimationEndListener(currState);
			maze.registerPortalsAnimationEndListener(currState);
		}
	}

	/**
	 * Keeps the rotation before the user input starts.
	 */
	private float mazeOldRotation;

	/***
	 * Called by {@link com.blox.maze.controller.MazeController#waiting waiting}
	 * state to signal start of user input. Old rotation value is recorded. The
	 * next state ({@link com.blox.maze.controller.MazeController#userRotating
	 * userRotating}) is started after the input start coordinates are sent to
	 * it.
	 * 
	 * @param rotateStartX
	 * @param rotateStartY
	 */
	public void beginUserRotating(float rotateStartX, float rotateStartY) {
		mazeOldRotation = maze.getRotationZ();
		userRotating.setStarts(rotateStartX, rotateStartY);
		setCurrState(userRotating);
	}

	/***
	 * Called by {@link com.blox.maze.controller.MazeController#userRotating
	 * userRotating} state to update the rotation of the maze while user input
	 * is still being received (Slide is not finished).
	 * 
	 * @param userRotation
	 */
	public void userRotated(float userRotation) {
		maze.setRotationZ(mazeOldRotation + userRotation);
	}

	/***
	 * Called by {@link com.blox.maze.controller.MazeController#userRotating
	 * userRotating} state to signal end of user input abortion. Maze rotation
	 * is restored to its old value and FSM is moved back to
	 * {@link com.blox.maze.controller.MazeController#waiting waiting} state.
	 */
	public void userAbortRotation() {
		maze.setRotationZ(mazeOldRotation);
		setCurrState(waiting);
	}

	/**
	 * Values -1 or 1 for maze rotation direction (Right, Left).
	 */
	private int userRotation;
	/**
	 * Angle of rotation that completes user rotation to 90 degrees.
	 */
	private float mazeTempRotation;

	/***
	 * Called by {@link com.blox.maze.controller.MazeController#userRotating
	 * userRotating} state when user input ends. Needed rotation angle to
	 * complete user rotation to 90 degrees is calculated. FSM is moved to the
	 * next state, {@link com.blox.maze.controller.MazeController#mazeRotating
	 * mazeRotating}.
	 * 
	 * @param userRotation
	 * @param userAngle
	 */
	public void startMazeRotate(int userRotation, float userAngle) {
		this.userRotation = userRotation;
		mazeTempRotation = 90 - userRotation * userAngle;
		setCurrState(mazeRotating);
	}

	private float epsilon = 0.1f;

	/***
	 * Called by {@link com.blox.maze.controller.MazeController#mazeRotating
	 * mazeRotating} state on each update cycle to rotate the maze till 90
	 * degrees of rotation is reached. Upon reaching, MazeMover is informed
	 * about new rotation and FSM is advanced to
	 * {@link com.blox.maze.controller.MazeController#lokumFalling lokumFalling}
	 * state.
	 * 
	 * @param increment
	 */
	public void mazeRotate(float increment) {
		maze.getRotation().rotation.z += userRotation * increment;
		mazeTempRotation -= increment; // Keep track of remaining rotation.
		if (mazeTempRotation <= 0 + epsilon) { // MAZE_ROTATE FINISHED
			maze.setRotationZ(mazeOldRotation + userRotation * 90);
			MazeMover.instance.turn(userRotation == 1);

			setCurrState(lokumFalling);
		}
	}

	/***
	 * Called by {@link com.blox.maze.controller.MazeController#lokumFalling
	 * lokumFalling} state when {@link com.blox.maze.model.Lokum Lokum} lands on
	 * a {@link com.blox.maze.model.Block Block}. Lokum is stopped and FSM is
	 * advanced to {@link com.blox.maze.controller.MazeController#waiting
	 * waiting}.
	 * 
	 * @param thisBound
	 * @param thatBound
	 * @param thatObj
	 */
	public void lokumFallOnBlock(IBound thisBound, IBound thatBound, ICollidable thatObj) {
		lokumStopOnBlock(thisBound, thatBound, thatObj);
		setCurrState(waiting);
	}

	/***
	 * Called by {@link com.blox.maze.controller.MazeController#lokumFalling
	 * lokumFalling} state when {@link com.blox.maze.model.Lokum Lokum} lands on
	 * a {@link com.blox.maze.model.Trap Trap}. FSM is advanced to
	 * {@link com.blox.maze.controller.MazeController#lokumOnTrap lokumOnTrap}.
	 * 
	 * @param thisBound
	 * @param thatBound
	 * @param thatObj
	 */
	public void lokumFallOnTrap() {
		lokum.fellOnTrap();
		setCurrState(lokumOnTrap);
	}

	/***
	 * Called by {@link com.blox.maze.controller.MazeController#lokumFalling
	 * lokumFalling} state when {@link com.blox.maze.model.Lokum Lokum} lands on
	 * an {@link com.blox.maze.model.Objective Objective}. FSM is advanced to
	 * {@link com.blox.maze.controller.MazeController#lokumOnObjective
	 * lokumOnObjective}.
	 * 
	 * @param thisBound
	 * @param thatBound
	 * @param thatObj
	 */
	public void lokumFallOnObjective() {
		lokum.fellOnObjective();
		setCurrState(lokumOnObjective);
	}

	private PortalDoor door;
	private PortalDoor doorPair;

	/***
	 * Called by {@link com.blox.maze.controller.MazeController#lokumFalling
	 * lokumFalling} state when {@link com.blox.maze.model.Lokum Lokum} lands on
	 * an {@link com.blox.maze.model.PortalDoor PortalDoor}. The collided
	 * PortalDoor's are recorded. Lokum is unregistered from the
	 * {@link com.blox.framework.v0.impl.DrawManager DrawManager}. FSM is
	 * advanced to the next state,
	 * {@link com.blox.maze.controller.MazeController#lokumOnlokumOnPortal
	 * lokumOnPortal}, which is registered to listen for animation-end of the
	 * collided PortalDoor.
	 * 
	 * @param thisBound
	 * @param thatBound
	 * @param thatObj
	 */
	public void lokumFallOnPortal(PortalDoor door) {
		this.door = door;
		this.doorPair = this.door.getPair();

		maze.collidedPortalDoor(this.door);
		screen.unregisterDrawable(lokum);
		this.door.registerAnimationEndListener(lokumOnPortal);
		setCurrState(lokumOnPortal);
	}

	/***
	 * Repositions {@link com.blox.maze.model.Lokum Lokum} after collision and
	 * stops its movement. Called inside
	 * {@link com.blox.controller.MazeController#lokumFallOnBlock(IBound, IBound, ICollidable)
	 * lokumFallOnBlock}. Also called by the states
	 * {@link com.blox.maze.controller.MazeController#lokumOnTrap lokumOnTrap}
	 * and {@link com.blox.maze.controller.MazeController#lokumOnObjective
	 * lokumOnObjective} <b>AFTER</b> {@link com.blox.maze.model.Lokum Lokum}
	 * lands on a {@link com.blox.maze.model.Trap Trap} or an
	 * {@link com.blox.maze.model.Objective Objective} <b>to handle consequent
	 * collisions with {@link com.bloc.maze.model.Block Block}s</b>
	 * 
	 * @param thisBound
	 * @param thatBound
	 * @param thatObj
	 */
	public void lokumStopOnBlock(IBound thisBound, IBound thatBound, ICollidable thatObj) {
		lokum.fellOnBlock(thisBound, thatBound, thatObj);
		lokum.stopLokum();
	}

	/***
	 * Called by {@link com.blox.maze.controller.MazeController#lokumOnTrap
	 * lokumOnTrap} state (when animation ends) to restart the current map.
	 */
	public void resetMap() {
		// TODO: reset map and lokum
		MazeMover.instance.resetRotation();
		lokum.reset();
		maze.reset();
		setCurrState(waiting);
	}

	/***
	 * Called by
	 * {@link com.blox.maze.controller.MazeController#lokumOnObjective
	 * lokumOnObjective} state (when animation ends) to move on to the next map.
	 * TODO Currently only calls
	 * {@link com.blox.maze.controller.MazeController#resetMap resetMap}.
	 */
	public void finishMap() {
		// TODO Get next map falan
		resetMap();
	}

	/***
	 * Called by
	 * {@link com.blox.maze.controller.MazeController#lokumOnlokumOnPortal
	 * lokumOnPortal} state (when animation ends). Teleports
	 * {@link com.blox.maze.model.Lokum Lokum} to the exit
	 * {@link com.blox.maze.model.PortalDoor PortalDoor}. Lokum is registered
	 * back to the {@link com.blox.framework.v0.impl.DrawManager DrawManager}
	 * and {@link com.blox.maze.controller.MazeController#lokumOnlokumOnPortal
	 * lokumOnPortal} is unregistered from listening to PortalDoor
	 * animation-end. FSM is advanced to
	 * {@link com.blox.maze.controller.MazeController#lokumFalling lokumFalling}
	 * while the exit PortalDoor is removed from
	 * {@link com.blox.maze.controller.MazeController#lokumToPortals
	 * lokumToPortals} and added to
	 * {@link com.blox.maze.controller.MazeController#lokumNotCollide
	 * lokumNotCollide} for
	 * {@link com.blox.maze.controller.MazeController#lokumFalling lokumFalling}
	 * to listen for the collision-end.
	 */
	public void portalFinished() {
		lokum.teleport(this.door.getPair());
		screen.registerDrawable(lokum, 2);
		this.door.unregisterAnimationEndListener(lokumOnPortal);
		maze.finishedPortal(this.door);

		setCurrState(lokumFalling);

		lokumToPortals.unregisterSecond(this.doorPair);
		lokumNotCollide.registerSecond(this.doorPair);
	}

	/***
	 * Called by {@link com.blox.maze.controller.MazeController#lokumFalling
	 * lokumFalling} to re-add exit {link com.blox.maze.model.PortalDoor
	 * PortalDoor} back to the collision group
	 * {@link com.blox.maze.controller.MazeController#lokumToPortals
	 * lokumToPortals} and remove it from
	 * {@link com.blox.maze.controller.MazeController#lokumNotCollide
	 * lokumNotCollide}
	 * 
	 * @param obj
	 */
	public void lokumUncollidedPortalDoor(ICollidable obj) {
		lokumToPortals.registerSecond(obj);
		lokumNotCollide.unregisterSecond(obj);
	}
}
