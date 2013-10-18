package com.turpgames.framework.v0.util;

import com.turpgames.framework.v0.IBound;
import com.turpgames.framework.v0.ICollidable;

public final class CollisionEvent {
	private ICollidable thisObj;
	private ICollidable thatObj;
	private IBound thisBound;
	private IBound thatBound;

	public CollisionEvent() {
		
	}

	public ICollidable getThisObj() {
		return thisObj;
	}

	public ICollidable getThatObj() {
		return thatObj;
	}

	public IBound getThisBound() {
		return thisBound;
	}

	public IBound getThatBound() {
		return thatBound;
	}

	void setThisObj(ICollidable thisObj) {
		this.thisObj = thisObj;
	}

	void setThatObj(ICollidable thatObj) {
		this.thatObj = thatObj;
	}

	void setThisBound(IBound thisBound) {
		this.thisBound = thisBound;
	}

	void setThatBound(IBound thatBound) {
		this.thatBound = thatBound;
	}
}
