package com.blox.set.controller;

import com.blox.framework.v0.impl.StateManager;
import com.blox.set.model.Card;
import com.blox.set.model.TableObject;
import com.blox.set.view.SetGameScreen;

public abstract class SetGameController extends StateManager {
	protected SetGameScreen screen; // Parent screen.
	protected TableObject gameTable; // Game table.
	/**
	 * Waiting for user to select a card.
	 */
	protected WaitingState waitingState;
	
	/**
	 * User selected a card.
	 */
	private SelectedState selectedState;
	
	public SetGameController(SetGameScreen parent) {
		super();
		this.screen = parent;
		
		//Initializations
		waitingState = new WaitingState(this);
		selectedState = new SelectedState();
	}
	
	abstract public void tapped(Card card);
	
	public final void registerWaiting() {
		gameTable.registerWaiting(waitingState);
	}
	
	public final void unregisterWaiting() {
		gameTable.unregisterWaiting(waitingState);
	}
	
	public final void registerSelected() {
		gameTable.registerSelected(selectedState);
	}
	
	public final void unregisterSelected() {
		gameTable.unregisterSelected(selectedState);
	}
}
