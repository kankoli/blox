package com.blox.framework.v0.impl;

import com.blox.framework.v0.IState;

public class State implements IState {
	protected StateManager parent;
	
	public State(StateManager parent) {
		this.parent = parent;
	}
	
	@Override
	public void work() {
		
	}

}
