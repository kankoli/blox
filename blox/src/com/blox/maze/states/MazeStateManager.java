package com.blox.maze.states;

import com.blox.framework.v0.impl.GameObject;
import com.blox.framework.v0.impl.InputState;
import com.blox.framework.v0.impl.Screen;
import com.blox.framework.v0.impl.State;
import com.blox.framework.v0.impl.StateManager;

public class MazeStateManager extends StateManager {

	private InputState waiting;
	private State rotating;
	
	public MazeStateManager(GameObject p) {
		super(p);
	
		Screen screen = parent.getParent();
		waiting = new MazeWaitingState(this);
		screen.registerInputListener(waiting);
		rotating = new MazeRotatingState(this);
		
		currState = waiting;
	}
	
	@Override
	public void advanceState() {
		if (currState.equals(waiting)){
			currState = rotating;
		}
		else if (currState.equals(rotating)) {
			currState = waiting;
		}
	}
}
