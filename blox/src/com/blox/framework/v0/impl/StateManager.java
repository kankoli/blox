package com.blox.framework.v0.impl;

import java.util.ArrayList;
import java.util.List;

import com.blox.framework.v0.IState;
import com.blox.framework.v0.IStateManager;

public abstract class StateManager implements IStateManager {
	protected List<IState> objects;
	protected IState currState;
	protected GameObject parent;
	
	public StateManager(GameObject parent) {
		this.objects = new ArrayList<IState>();
		this.parent = parent;
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
	
	@Override
	public void advanceState() {
		
	}
	
	public IState getCurrState() {
		return currState;
	}

	public void setCurrState(IState currState) {
		this.currState = currState;
	}

	public boolean isCurrState(IState currState) {
		return this.currState.equals(currState);
	}

	public GameObject getParent() {
		return parent;
	}

}
