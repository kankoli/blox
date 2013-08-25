package com.blox.maze.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.blox.framework.v0.IMovable;
import com.blox.framework.v0.IMover;
import com.blox.framework.v0.impl.DefaultMover;
import com.blox.framework.v0.util.Vector;

public class MazeMover extends DefaultMover {

	public static final MazeMover instance = new MazeMover();

	private static enum State {
		_0, _90, _180, _270
	};

	private final static float gravity = 279.8f;

	private static Map<State, Vector> gravities = new HashMap<MazeMover.State, Vector>();
	static {
		gravities.put(State._0, new Vector(0, -gravity));
		gravities.put(State._90, new Vector(-gravity, 0));
		gravities.put(State._180, new Vector(0, gravity));
		gravities.put(State._270, new Vector(gravity, 0));
	}

	private static State[] stateValues = State.values();
	
	private List<IMovable> list;
	private State currState;

	private MazeMover() {
		list = new ArrayList<IMovable>();
		currState = State._0;
	}

	public void register(IMovable obj) {
		list.add(obj);
		obj.setMover(this);
	}

	public void unregister(IMovable obj) {
		list.remove(obj);
		obj.setMover(IMover.NULL);
	}

	public void turn(boolean clockwise) {
		int increment = clockwise ? 1 : -1;

		currState = stateValues[(currState.ordinal() + increment + stateValues.length) % stateValues.length];

		for (IMovable obj : list) {
			obj.getAcceleration().set(gravities.get(currState));
		}
	}

	public void resetRotation() {
		currState = State._0;
	}
}
