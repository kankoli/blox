package com.blox.setgame.model;

import com.blox.framework.v0.impl.GameObject;

public abstract class CardGameObject extends GameObject {
	public CardGameObject() {

	}
	
	public void activate() {
		listenInput(true);
	}
	
	public void deactivate() {
		listenInput(false);
	}
}
