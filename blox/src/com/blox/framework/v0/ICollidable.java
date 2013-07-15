package com.blox.framework.v0;

import java.util.Iterator;

import com.blox.framework.v0.util.Rotation;

public interface ICollidable {
	Iterator<IBound> getBounds();
	Rotation getRotation();
	void onCollide(IBound thisBound, IBound thatBound, ICollidable obj);
}
