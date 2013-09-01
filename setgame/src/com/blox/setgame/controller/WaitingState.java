package com.blox.setgame.controller;

import com.blox.framework.v0.impl.State;
import com.blox.setgame.model.Card;

public class WaitingState extends State implements Card.ICardTapListener {
	private SetGameController controller;

	public WaitingState(SetGameController parent) {
		this.controller = parent;
	}

	@Override
	public void cardTapped(Card card) {
		controller.cardTapped(card);
	}
}
