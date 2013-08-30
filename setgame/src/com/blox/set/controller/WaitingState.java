package com.blox.set.controller;

import com.blox.framework.v0.IGameObject;
import com.blox.framework.v0.impl.State;
import com.blox.set.model.Card;

public class WaitingState extends State {
	private SetGameController controller;

	public WaitingState(SetGameController parent) {
		this.controller = parent;
	}
	
	@Override
	public void onTap(IGameObject obj, float x, float y, int count, int button) {
		controller.tapped((Card)obj);
	}
}
