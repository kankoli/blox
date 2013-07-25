package com.blox.maze.states;

import com.blox.framework.v0.impl.InputState;
import com.blox.framework.v0.impl.Screen;
import com.blox.framework.v0.impl.State;
import com.blox.framework.v0.impl.StateManager;
import com.blox.maze.MazeGameObject;

public class MazeStateManager extends StateManager {

	private InputState waiting;
	private State rotating;
	
	public MazeStateManager(MazeGameObject p) {
		super(p);
	
		Screen screen = parent.getScreen();
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
