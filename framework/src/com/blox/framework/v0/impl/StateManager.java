package com.blox.framework.v0.impl;

import com.blox.framework.v0.IState;

public abstract class StateManager extends Manager<IState> {
	protected IState currState;

	@Override
	public void execute() {
		execute(currState);
	}

	@Override
	protected void execute(IState state) {
		state.work();
	}
}
