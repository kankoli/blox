package com.blox.maze.states;

import com.blox.framework.v0.impl.InputState;
import com.blox.framework.v0.impl.StateManager;

public class MazeWaitingState extends InputState {
	private float rotateStart;
	
	public MazeWaitingState(StateManager parent) {
		super(parent);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		if (parent.isCurrState(this)) {
			rotateStart = x;
		}
		return false;
	}
	
	@Override
	public boolean touchDragged(float x, float y, int pointer) {
		if (parent.isCurrState(this)) {
			parent.getParent().getRotation().addRotationZ(-(rotateStart - x) / 3f);
			rotateStart = x;
		}
		return false;
	}
	
	@Override
	public boolean touchUp(float x, float y, int pointer, int button) {
		if (parent.isCurrState(this))
			parent.advanceState();
		return false;
	}
}
