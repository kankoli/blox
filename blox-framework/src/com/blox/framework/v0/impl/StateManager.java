package com.blox.framework.v0.impl;

import java.util.ArrayList;
import java.util.List;

import com.blox.framework.v0.IState;
import com.blox.framework.v0.IStateManager;

public abstract class StateManager implements IStateManager {
	protected List<IState> objects;
	protected IState currState;

	public StateManager() {
		this.objects = new ArrayList<IState>();
	}

	@Override
	public void register(IState obj) {
		objects.add(obj);
	}

	@Override
	public void unregister(IState obj) {
		objects.remove(obj);
	}

	@Override
	public void work() {
		currState.work();
	}
}
