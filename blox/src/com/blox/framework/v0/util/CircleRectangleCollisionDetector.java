package com.blox.framework.v0.util;

import com.blox.framework.v0.IBound;
import com.blox.framework.v0.ICircleBound;
import com.blox.framework.v0.ICollisionDetector;
import com.blox.framework.v0.IRectangleBound;

public class CircleRectangleCollisionDetector implements ICollisionDetector {
	@Override
	public boolean detect(IBound bound1, IBound bound2) {
		ICircleBound circleBound;
		IRectangleBound rectBound;

		if (bound1.getType() == IBound.Circle) {
			circleBound = (ICircleBound) bound1;
			rectBound = (IRectangleBound) bound2;
		} else {
			rectBound = (IRectangleBound) bound1;
			circleBound = (ICircleBound) bound2;
		}

		// TODO: rotation
		Vector c = circleBound.getLocation();
		float r = circleBound.getRadius();

		Vector l = rectBound.getLocation();
		float w_2 = rectBound.getWidth() / 2;
		float h_2 = rectBound.getHeight() / 2;

		float dx = Math.abs(c.x - l.x - w_2);
		float dy = Math.abs(c.y - l.y - h_2);
		
		return dx <= (w_2 + r) && dy <= (h_2 + r);
	}
}
