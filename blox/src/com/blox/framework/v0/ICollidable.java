package com.blox.framework.v0;

import java.util.Iterator;

import com.blox.framework.v0.util.Rotation;
import com.blox.framework.v0.util.Vector;

public interface ICollidable {
	Iterator<IBound> getBounds();

	Vector getLocation();

	Rotation getRotation();

	float getWidth();

	float getHeight();

	boolean isFlipX();

	boolean isFlipY();

}
