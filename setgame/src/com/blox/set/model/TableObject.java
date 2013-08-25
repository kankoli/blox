package com.blox.set.model;

import com.blox.framework.v0.impl.GameObject;
import com.blox.set.controller.SelectedState;
import com.blox.set.controller.WaitingState;

public abstract class TableObject extends GameObject {

	abstract public void registerWaiting(WaitingState waitingState);

	abstract public void unregisterWaiting(WaitingState waitingState);

	abstract public void registerSelected(SelectedState selectedState);

	abstract public void unregisterSelected(SelectedState selectedState);
	
	abstract public void cardSelected(Card card);

	abstract public void cardUnselected(Card card);
	
}
