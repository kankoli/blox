package com.blox.framework.v0.impl;

import com.blox.framework.v0.IBound;
import com.blox.framework.v0.ICollisionDetector;
import com.blox.framework.v0.ICollisionDetectorFactory;

public class CollisionDetectorFactory implements ICollisionDetectorFactory {
	private ICollisionDetectorFactory successor;

	private static final ICollisionDetector circCirc = new CircleCircleCollisionDetector();
	private static final ICollisionDetector rectRect = new RectangleRectangleCollisionDetector();
	private static final ICollisionDetector circRect = new CircleRectangleCollisionDetector();

	@Override
	public void setSuccessor(ICollisionDetectorFactory successor) {
		this.successor = successor;		
	}

	@Override
	public ICollisionDetector getDetector(int boundType1, int boundType2) {
		if (boundType1 == IBound.Circle && boundType2 == IBound.Circle)
			return circCirc;
		if ((boundType1 == IBound.Circle && boundType2 == IBound.Rectangle) || 
			(boundType1 == IBound.Rectangle && boundType2 == IBound.Circle))
			return circRect;
		if (boundType1 == IBound.Rectangle && boundType2 == IBound.Rectangle)
			return rectRect;
		return successor == null ? null : successor.getDetector(boundType1, boundType2);
	}
	
}
