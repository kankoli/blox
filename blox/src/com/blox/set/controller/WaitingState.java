package com.blox.set.controller;

import com.blox.framework.v0.IGameObject;
import com.blox.framework.v0.ITappedListener;
import com.blox.framework.v0.impl.State;
import com.blox.set.model.Card;

public class WaitingState extends State implements ITappedListener {
	private SetGameController controller;

	public WaitingState(SetGameController parent) {
		this.controller = parent;
	}
	
	@Override
	public void onTapped(IGameObject obj) {
		controller.tapped((Card)obj);
	}
}
