package com.blox.framework.v0;

public interface ICollisionListener {
	void collide(ICollidable thisObj, IBound thisBound, ICollidable thatObj, IBound thatBound);
}
