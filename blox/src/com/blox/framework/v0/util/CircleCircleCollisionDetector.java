package com.blox.framework.v0.util;

import com.blox.framework.v0.IBound;
import com.blox.framework.v0.ICircleBound;
import com.blox.framework.v0.ICollisionDetector;

public class CircleCircleCollisionDetector implements ICollisionDetector {
	@Override
	public boolean detect(IBound bound1, IBound bound2) {
		ICircleBound b1 = (ICircleBound) bound1;
		ICircleBound b2 = (ICircleBound) bound2;

		// TODO: rotation
		return b1.getLocation().dist(b2.getLocation()) <= b1.getRadius()
				+ b2.getRadius();
	}
}