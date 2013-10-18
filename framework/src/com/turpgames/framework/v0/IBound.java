package com.turpgames.framework.v0;

import com.turpgames.framework.v0.util.Rotation;
import com.turpgames.framework.v0.util.Vector;

public interface IBound {
	public static final int Circle = 0x01;
	public static final int Rectangle = 0x02;

	Vector getLocation();

	Rotation getRotation();

	Vector getOffset();

	Vector getInvOffset();

	int getType();
}
