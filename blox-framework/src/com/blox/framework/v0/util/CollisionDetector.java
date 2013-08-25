package com.blox.framework.v0.util;

import java.util.Iterator;

import com.blox.framework.v0.IBound;
import com.blox.framework.v0.ICollidable;
import com.blox.framework.v0.ICollisionDetector;
import com.blox.framework.v0.ICollisionGroup;

public final class CollisionDetector {
	private CollisionDetector() {

	}

	private static final CollisionEvent event = new CollisionEvent();
	
	public static void detect(ICollisionGroup group, ICollidable obj1, ICollidable obj2) {
		Iterator<IBound> bounds1 = obj1.getBounds();
		while (bounds1.hasNext()) {
			IBound bound1 = bounds1.next();
			Iterator<IBound> bounds2 = obj2.getBounds();
			while (bounds2.hasNext()) {
				IBound bound2 = bounds2.next();
				ICollisionDetector detector = getDetector(bound1.getType(), bound2.getType());
				
				event.setThisObj(obj1);
				event.setThisBound(bound1);
				event.setThatObj(obj2);
				event.setThatBound(bound2);
				
				if (detector.detect(bound1, bound2)) { // colliding
					group.onCollide(event);
				} else { // not colliding
					group.onNotCollide(event);
				}
			}
		}
	}

	private static ICollisionDetector getDetector(int boundType1, int boundType2) {
		return Game.getCollisionDetectorFactory().getDetector(boundType1, boundType2);
	}
}