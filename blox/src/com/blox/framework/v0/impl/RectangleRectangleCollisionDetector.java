package com.blox.framework.v0.impl;

import com.blox.framework.v0.IBound;
import com.blox.framework.v0.ICollisionDetector;
import com.blox.framework.v0.IRectangleBound;
import com.blox.framework.v0.util.Vector;

public class RectangleRectangleCollisionDetector implements ICollisionDetector {
	@Override
	public boolean detect(IBound bound1, IBound bound2) {
		IRectangleBound b1 = (IRectangleBound) bound1;
		IRectangleBound b2 = (IRectangleBound) bound2;

		Vector l1 = b1.getLocation();
		float w1 = b1.getWidth();
		float h1 = b1.getHeight();

		Vector l2 = b2.getLocation();
		float w2 = b2.getWidth();
		float h2 = b2.getHeight();

//		return ((l1.x + w1) >= l2.x && l1.x <= l2.x) || (l1.x < l2.x + w2 && l1.x + w1 > l2.x + w2) &&
//				((l1.y + h1) >= l2.y && l1.y <= l2.y) || (l1.y < l2.y + h2 && l1.y + h1 > l2.y + h2);
		// TODO: rotation
		return !((l1.y > l2.y + h2) || 
				(l1.x > l2.x + w2) ||
				(l1.y + h1 < l2.y) || 
				(l1.x + w1 < l2.x));
	}
}